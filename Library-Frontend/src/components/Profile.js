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
        this.libCard=response.data.personRole.libraryCard.id;
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