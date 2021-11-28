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
  name: 'registration',
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
      errorReservation: '',
      reservations: [],
      selectedDate: null,
      error: false,
    }
  },
  methods: {
    createReservation: function () {
      let body = {itemInstanceId: this.itemInstanceId, customerId: this.$cookie.get('customerId'), dateReserved:new Date().toISOString().slice(0, 10), pickupDay: this.selectedDate }
      AXIOS.post('/reservation/',body,{}).then(response => {
        this.reservations.push(response.data)
        this.errorReservation = ''
        this.close()
      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorReservation = errorMsg
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
