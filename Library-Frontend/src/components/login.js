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
  name: 'login',
  data () {
    return {
      userLogin: null,
      passwordLogin: null,
      error: false
    }
  },
  created: function () {

  },
  methods: {
    logIn: function () {
      console.log(this.userLogin)
      AXIOS.put('/login/'+this.userLogin+'/'+this.passwordLogin,{}).then(response => {
        this.errorLogin = ''
        this.$cookie.set("username", this.userLogin)
        this.$router.push({name: 'homepage'});

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
