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
      return "http://" + config.dev.host
    case "production":
      return (
        "https://" + config.build.host
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
        error: '',
        libCard: ''
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
        this.libCard = reservation.customer.libCard.id
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
        var serialNum = this.selectedReservation.itemInstance.serialNum
        AXIOS.post('/loan/libraryCard/' +  this.libCard + '/' + parseInt(serialNum) + '/' + this.returnDate)
        .then(response => {
          this.libCard = '';
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
