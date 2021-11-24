import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function PersonDto (name) {}

export default {
  name: 'signup',
  data () {
    return {
      userSignup: null,
      passwordSignup: null,
      streetNum: null,
      streetName: null,
      city: null,
      country: null,
      error: false
    }
  },
  created: function () {

  },
  methods: {
    createOnlineAccount: function () {
      console.log(this.userSignup)
      AXIOS.push('/login/'+this.userSignup+'/'+this.passwordSignup,{}).then(response => {
        this.errorLogin = ''
        this.$cookie.set("username", this.userSignup)
      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorLogin = errorMsg
          this.error = true
        })
    }
  }
}
