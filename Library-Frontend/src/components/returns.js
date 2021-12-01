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
        loans: [],
        errorLoan: '',
        selectedLoan: '',
        libCard: '',
        serialNum: '',
        returnDate: '',
        selectedItem: '',
        selectedCustomer: '',
        returnItemError: '',
        error: ''
      }
    },
    created: function () {
      AXIOS.get('/loan/active').then(response => {
        this.loans = response.data
      })
        .catch(e =>{
          this.error = e.response.data.message
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
        this.error = ''
        var id = this.selectedLoan.id
        var customerId = this.selectedLoan.customer.id
        AXIOS.delete('/loan/' + parseInt(id), { params: { customerId:  parseInt(customerId)} }).then(response => {
          this.selectedLoan = []
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })

        document.location.reload(true)
      },

      createLoan: function () {
        this.error = ''
        AXIOS.post('/loan/libraryCard/' +  parseInt(this.libCard) + '/' + parseInt(this.serialNum) + '/' + this.returnDate)
        .then(response => {
          this.libCard = '';
          this.serialNum = '';
          this.returnDate = '';
          this.loans.push(response.data)
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })
      }
    }
}
