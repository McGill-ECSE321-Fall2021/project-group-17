import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

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
      let body = {itemId: this.itemInstanceId, customerId: this.$cookie.get('customerId'), dateReserved: this.selectedStartDate, pickupDay: this.selectedEndDate }
      AXIOS.post('/loan/',body,{}).then(response => {
        this.loans.push(response.data)
        this.errorLoan = ''
        this.close()
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
