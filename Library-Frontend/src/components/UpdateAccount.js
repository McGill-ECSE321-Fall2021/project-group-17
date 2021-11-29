import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
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
        AXIOS.get('/getaccount/Sofia').then(response => {
            console.log(response.data)
          this.user =response.data.username;
          this.password=response.data.password;
          this.email=response.data.email;
          this.streetNum=response.data.personRole.address.streetNumber;
          this.streetName=response.data.personRole.address.street;
          this.city=response.data.personRole.address.city;
          this.Country=response.data.personRole.address.country;
          this.Address= this.streetNum + " " + this.streetName + " " + this.city + " " + this.Country;
          this.libCard=response.data.personRole.libraryCard.id;
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
                this.libCard=response.data.personRole.libraryCard.id;
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