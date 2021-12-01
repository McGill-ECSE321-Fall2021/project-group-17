import axios from 'axios'
import reservation from "./reservation";
import loan from "./loan";

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
    this.populateTable()
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
    },
    onSelectLoan (item) {
      this.selectedLoan = item
    },
    openLoanDialog () {
      this.loanDialog = true
    },
    openReservationDialog () {
      this.reservationDialog = true
    },
    deleteReservation (){
      AXIOS.delete('/reservation/'+this.selectedReservation.id,{params: {customerId:this.$cookie.get("customerId")}}).then(response => {
        this.errorText = "Reservation Deleted"
        this.error = true
        this.populateTable()
      })
        .catch(e => {
          this.errorText = e.response
          this.error = true
        })
    },
    deleteLoan (){
      AXIOS.delete('/loan/'+this.selectedLoan.id,{params: {customerId:this.$cookie.get("customerId")}}).then(response => {
        this.errorText = "Loan Deleted"
        this.error = true
        this.populateTable()
      })
        .catch(e => {
          this.errorText = e.response
          this.error = true
        })
    },
    populateTable() {
      AXIOS.get('/reservation/',{ params : {customerId: this.$cookie.get("customerId")}}).then(response => {
        this.reservations = response.data
        this.searchedReservations = response.data

        console.get(this.searchedReservations)
      })
        .catch(e =>{
          this.errorText = e.response
          //console.log(e.response.data.message)
        })
      AXIOS.get('/loans/'+ this.$cookie.get("customerId") ).then(response => {
      this.loans = response.data
      this.searchedLoans = response.data
    })
      .catch(e =>{
        this.errorItemInstance = e.response
        //console.log(e.response.data.message)
      })

    }

  }
}
