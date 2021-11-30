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
    name: 'Profile',
    data () {
     return {
       id:'',
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
        console.log(response.data)
        this.user=response.data.account.username;
        this.password=response.data.account.password;
        this.email=response.data.account.email;
        this.streetNum=response.data.address.streetNumber;
        this.streetName=response.data.address.street;
        this.city=response.data.address.city;
        this.Country=response.data.address.country;
        this.Address= this.streetNum + " " + this.streetName + " " + this.city + " " + this.Country;
        this.libCard=response.data.libCard.id;
      })
          .catch(e =>{
            this.errorLoan = e
            console.log(e.response.data.message)
          })
      },
    methods:{
      updateAccountPage(){
        this.$router.push({name: 'UpdateAccount'});
         },
       homepage(){
        this.$router.push({name: 'HomePage'});
         }
    }
}
