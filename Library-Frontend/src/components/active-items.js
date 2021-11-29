import axios from 'axios'
import reservation from "./reservation";
import loan from "./loan";

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ItemInstanceDTO (){}
const toLower = text => {
  return text.toString().toLowerCase()
}
const searchByName = (items, term) => {
  if (term) {
    return items.filter(item => toLower(item.itemInstance.checkableItem.name).includes(toLower(term)))
  }

  return items
}
export default {
  name: 'itemInstance',
  components: {
    reservation,
    loan
  },
  data () {
    return {
      errorText: '',
      reservations: [],
      loans: [],
      searchedReservations: [],
      searchedLoans: [],
      error: false,
      search: null,
      selected: {},
      buttonEnabled: true,
      loanDialog: false,
      reservationDialog: false
    }
  },
  created: function () {
    AXIOS.get('/reservation/',{ params : {customerId: this.$cookie.get("customerId")}}).then(response => {
      this.reservations = response.data
      this.searchedReservations = response.data

      console.get(this.searchedReservations)
    })
      .catch(e =>{
        this.errorText = e.response
        //console.log(e.response.data.message)
      })
    /*AXIOS.get('/loans/'+ this.$cookie.get("customerId") ).then(response => {
      this.loans = response.data
      this.searchedLoans = response.data
    })
      .catch(e =>{
        this.errorItemInstance = e.response
        //console.log(e.response.data.message)
      })*/
  },
  methods: {
    searchOnTableLoan () {
      this.searchedLoans = searchByName(this.loans, this.search)
    },
    searchOnTableReservation () {
      this.searchedReservations = searchByName(this.reservations, this.search)
    },
    onSelectReservation (item) {
      this.selectedReservation = item
      console.log(this.selected)
    },
    openLoanDialog () {
      this.loanDialog = true
    },
    openReservationDialog () {
      this.reservationDialog = true
    },
    deleteReservation (){
      axios.delete('/reservation/'+this.selectedReservation.id,{params: {customerId:this.$cookie.get("customerId")}}).then(response => {
        this.errorText = "Reservation Deleted"
        this.error = true
      })
        .catch(e => {
          this.errorText = e.response
          this.error = true
        })
    }
  }
}
