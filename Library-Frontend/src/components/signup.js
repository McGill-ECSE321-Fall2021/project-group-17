import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function PersonDTO (name){}
const toLower = text => {
  return text.toString().toLowerCase()
}
const searchByName = (items, term) => {
  if (term) {
    return items.filter(item => toLower(item.name).includes(toLower(term)))
  }

  return items
}

export default {
  name: 'signup',
  data () {
    return {
        users: [
          {
            id: 1,
            name: "Shawna Dubbin",
            email: "sdubbin0@geocities.com",
            gender: "Male",
            title: "Assistant Media Planner"
          },
          {
            id: 2,
            name: "Odette Demageard",
            email: "odemageard1@spotify.com",
            gender: "Female",
            title: "Account Coordinator"
          },
          {
            id: 3,
            name: "Vera Taleworth",
            email: "vtaleworth2@google.ca",
            gender: "Male",
            title: "Community Outreach Specialist"
          }
        ],
      persons: [],
      errorSignup: false,
      userName: null,
      userSignup: null,
      passwordSignup: null,
      streetNum: null,
      streetName: null,
      city: null,
      country: null,
      search: null,
      error: false,
      searchDialog: false,
      tableSearch: null,
      searched: []
    }
  },
  created: function () {
    AXIOS.get('/persons/', {}).then(response => {
      let persons = response.data
      this.users = persons
      this.searched = persons
    })
      .catch(e =>{
        this.errorItemInstance = e.response
        //console.log(e.response.data.message)
      })
  },
  methods: {
    onSearchClick() {
      this.searchDialog = true
    },
    searchOnTable(){
      this.searched = searchByName(this.users, this.tableSearch)
    },
    onSelected(){
      this.searchDialog = false
      //this.person = selectedPerson

    }
  }
}
