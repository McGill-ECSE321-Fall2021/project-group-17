import axios from 'axios'
import reservation from "./reservation.vue";
import loan from "./loan.vue";

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
    return items.filter(item => toLower(item.checkableItem.name).includes(toLower(term)))
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
      errorItemInstance: '',
      items: [],
      error: false,
      search: null,
      searched: [],
      selected: {},
      buttonEnabled: true,
      loanDialog: false,
      reservationDialog: false
    }
  },
  created: function () {
    AXIOS.get('/iteminstances/', {}).then(response => {
      let items = response.data
      console.log("items1", items)
      items = items.filter(function (el){
        return el.checkableItem != null;
      })
      items.forEach(function(element,index,array){
        if(element.checkableItem.musician){
          array[index].checkableItem.type = "Music"
          array[index].checkableItem.creator = element.checkableItem.musician
        }
        else if(element.checkableItem.director){
          array[index].checkableItem.type = "Movie"
          array[index].checkableItem.creator = element.checkableItem.director
        }
        else if (element.checkableItem.author){
          array[index].checkableItem.type = "Book"
          array[index].checkableItem.creator = element.checkableItem.author
        }
      })
      this.items = items
      console.log("items2", this.items)
      this.searched = items
    })
      .catch(e =>{
        this.errorItemInstance = e.response
        //console.log(e.response.data.message)
      })
  },
  methods: {
    searchOnTable () {
      this.searched = searchByName(this.items, this.search)
    },
    onSelect (item) {
      this.selected = item
      this.buttonEnabled = false
      console.log(this.selected)
    },
    openLoanDialog () {
      this.loanDialog = true
    },
    openReservationDialog () {
      this.reservationDialog = true
    }
  }
}
