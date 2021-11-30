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
      return "http://" + config.dev.host + ":" + config.dev.port;
    case "production":
      return (
        "http://" + config.build.host + ":" + config.build.port
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
        this.errorReservation = 'Reservation created successfully'
        this.error = true
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
