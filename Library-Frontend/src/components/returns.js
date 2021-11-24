import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'returns',
    mounted: function() {
      this.$material.theming.theme = "colors"; 
    },
    data () {
      return {
        loans: [],
        errorLoan: '',
        selectedLoan: '',
        checkedOut: new Date(),
        serialNum: 'Input Serial Number',
        libCard: 'Input Library Card Number'
      }
    },
    created: function () {
      AXIOS.get('/loan/active').then(response => {
        this.loans = response.data
        console.log("result is = ")
        console.log(response.data)
      })
        .catch(e =>{
          this.errorLoan = e
          console.log(e.response.data.message)
        })
    },
    methods: {
      selectLoan: function () {
        this.selectedLoan = "Bob"
      }
    }
}