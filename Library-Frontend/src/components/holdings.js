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
        returnItemError: ''
      }
    },
    created: function () {
      AXIOS.get('/reservation/active').then(response => {
        this.reservations = response.data
      })
        .catch(e =>{
          this.errorReservation = e
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
        var id = this.selectedReservation.id
        var customerId = this.selectedReservation.customer.id
        AXIOS.delete('/reservation/' + parseInt(id), { params: { customerId:  parseInt(customerId)} }).then(response => {
          this.selectedreservation = []
        })
          .catch(e =>{
            this.errorReservation = e
            console.log(e.response.data.message)
          })

        document.location.reload(true)
      },

      createLoan: function () {
        var libCard = this.selectedReservation.customer.libraryCard.id
        var serialNum = this.selectedReservation.itemInstance.serialNum
        AXIOS.post('/loan/libraryCard/' +  parseInt(libCard) + '/' + parseInt(serialNum) + '/' + this.returnDate)
        .then(response => {
          this.libraryCardNum = '';
          this.serialNum = '';
          this.returnDate = '';
        })
          .catch(e =>{
            console.log(e.response.data.message)
          })

        var id = this.selectedReservation.id
        var customerId = this.selectedReservation.customer.id

        AXIOS.delete('/reservation/' + parseInt(id), { params: { customerId:  parseInt(customerId)} }).then(response => {
        this.selectedreservation = []
        })
        .catch(e =>{
            this.errorReservation = e
            console.log(e.response.data.message)
        })

        document.location.reload(true)
      }
    }
}