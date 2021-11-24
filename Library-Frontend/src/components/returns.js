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
        returnDate: '',
        serialNum: 'Input Serial Number',
        libCard: 'Input Library Card Number',
        selectedItem: '',
        selectedCustomer: '',
        returnItemError: ''
      }
    },
    created: function () {
      AXIOS.get('/loan/active').then(response => {
        this.loans = response.data
      })
        .catch(e =>{
          this.errorLoan = e
          console.log(e.response.data.message)
        })
    },
    methods: {
      selectLoan: function (loan) {
        this.selectedLoan = loan
        this.selectedItem = loan.itemInstance.checkableItem.name
        this.selectedCustomer = loan.customer.person.name
      },

      returnItem: function () {
        var id = this.selectedLoan.id
        var customerId = this.selectedLoan.customer.id
        AXIOS.delete('/loan/' + parseInt(id), { params: { customerId:  parseInt(customerId)} }).then(response => {
          this.selectedLoan = []
        })
          .catch(e =>{
            this.errorLoan = e
            console.log(e.response.data.message)
          })

        document.location.reload(true)
      },

      createLoan: function () {
        AXIOS.post('/loan/libraryCard/' +  parseInt(this.libCard) + '/' + parseInt(this.serialNum) + '/' + this.returnDate)
        .then(response => {
          this.libraryCardNum = '';
          this.serialNum = '';
          this.returnDate = '';
        })
          .catch(e =>{
            console.log(e.response.data.message)
          })

        document.location.reload(true)
      }
    }
}