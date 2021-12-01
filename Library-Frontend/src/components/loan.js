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

function ReservationDTO (){}
export default {
  name: 'loan',
  props: {
    dialog: {
      default: true
    },
    itemInstanceId: {
      default: 0
    }
  },
  data () {
    return {
      errorLoan: '',
      loans: [],
      selectedEndDate: null,
      selectedStartDate: null,
      error: false,
    }
  },
  methods: {
    createLoan: function () {
      let body = {itemId: this.itemInstanceId, customerId: this.$cookie.get('customerId'), checkedOut: this.selectedStartDate, returnDate: this.selectedEndDate }
      AXIOS.post('/loan/',body,{}).then(response => {
        this.loans.push(response.data)
        this.errorLoan = "Loan created successfully"
        this.error = true
      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorLoan = errorMsg
          this.error = true
        })
    },
    close() {
      this.$emit('update:dialog', false)
    }
  },
  computed: {
    propModel: {
      get () {
        return this.dialog
      },
      set (value) { this.$emit('input', value) },
    }
  }
}
