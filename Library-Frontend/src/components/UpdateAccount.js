import axios from 'axios'
var config = require('../../config')

var backendConfigurer = function() {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
    case "production":
      return (
        "https://" + config.build.backendHost + ":" + config.build.backendPort
      );
  }
};

var frontendConfigurer = function() {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.host + ":" + config.dev.port;
    case "production":
      return (
        "http://" + config.build.host + ":" + config.build.port
      );
  }
};

var backendUrl = backendConfigurer();
var frontendUrl = frontendConfigurer();

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
    name: 'updateAccount',
    data () {
     return {
        user:'',
        password:'',
        updateAccountError: '',
        email: '',
        streetNum: '',
        streetName: '',
        city: '',
        Country: '',
        Address: '',
        libCard:'',
      }
    },
    created: function () {
      AXIOS.get("/customer/"+this.$cookie.get('customerId')).then(response=>{
        this.user=response.data.account.username;
        this.password=response.data.account.password;
        this.email=response.data.account.email;
        this.streetNum=response.data.address.streetNumber;
        this.streetName=response.data.address.street;
        this.city=response.data.address.city;
        this.Country=response.data.address.country;
        this.Address= this.streetNum + " " + this.streetName + " " + this.city + " " + this.Country;
        this.libCard=response.data.personRole.libraryCard.id;
        console.log(response.data)
      })
          .catch(e =>{
            this.errorLoan = e
            console.log(e.response.data.message)
          })
      },
    methods:{
        updateAccount: function () {
            AXIOS.put('/UpdateAccount/' + this.user + '/' +this.password + '/' + this.email + '/' + this.streetNum + '/' + this.streetName + '/' + this.city + '/' + this.Country)
            .then(response => {
                console.log("response:")
                console.log(response.data);
                this.user =response.data.username;
                this.password=response.data.password;
                this.email=response.data.email;
                this.streetNum=response.data.personRole.address.streetNumber;
                this.streetName=response.data.personRole.address.street;
                this.city=response.data.personRole.address.city;
                this.Country=response.data.personRole.address.country;
                this.Address= this.streetNum + " " + this.streetName + " " + this.city + " " + this.Country;

                if (this.libCard != null) {
                this.libCard=response.data.personRole.libraryCard.id;
                }
                this.$router.push({name:'Profile'});
            })
              .catch(e =>{
                console.log("response1:")
                console.log(e.response.data.message)
              })
          },
          profile(){
            this.$router.push({name:'Profile'});
          },
       homepage(){
        this.$router.push({name: 'HomePage'});
         }
    }
}
