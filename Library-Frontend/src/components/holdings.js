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
        reservations: [],
        errorReservation: '',
        selectedReservation: [],
        item: '',
        customer: '',
        returnDate: '',
        selectedItem: '',
        selectedCustomer: '',
        returnItemError: '',
        error: ''
      }
    },
    created: function () {
      AXIOS.get('/reservation/active').then(response => {
        this.reservations = response.data
      })
        .catch(e =>{
          this.error = e.response.data.message
          console.log(e.response.data.message)
        })
    },
    methods: {
      selectReservation: function (reservation) {
        this.selectedReservation = reservation
        this.selectedItem = reservation.itemInstance.checkableItem.name
        this.selectedCustomer = reservation.customer.person.name
      },

      deleteReservation: function () {
        this.error = ''
        var id = this.selectedReservation.id
        var customerId = this.selectedReservation.customer.id
        AXIOS.delete('/reservation/' + parseInt(id), { params: { customerId:  parseInt(customerId)} }).then(response => {
          this.selectedreservation = []
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })

        document.location.reload(true)
      },

      createLoan: function () {
        this.error = ''
        var libCard = this.selectedReservation.customer.libraryCard.id
        var serialNum = this.selectedReservation.itemInstance.serialNum
        AXIOS.post('/loan/libraryCard/' +  parseInt(libCard) + '/' + parseInt(serialNum) + '/' + this.returnDate)
        .then(response => {
          this.libraryCardNum = '';
          this.serialNum = '';
          this.returnDate = '';
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })

        var id = this.selectedReservation.id
        var customerId = this.selectedReservation.customer.id

        AXIOS.delete('/reservation/' + parseInt(id), { params: { customerId:  parseInt(customerId)} }).then(response => {
        this.selectedreservation = []
        })
        .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
        })

        document.location.reload(true)
      }
    }
}