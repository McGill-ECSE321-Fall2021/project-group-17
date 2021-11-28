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
    return items.filter(item => toLower(item.id).includes(toLower(term)))
  }

  return items
}

export default {
  name: 'signup',
  data () {
    return {

      persons: [],
      errorSignup: false,
      userName: null,
      userSignup: null,
      selected: {},
      passwordSignup: null,
      email: null,
      streetNum: null,
      streetName: null,
      city: null,
      country: null,
      search: null,
      error: false,
      searchDialog: false,
      tableSearch: null,
      searched: [],
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
    onSelected(id){
      this.searchDialog = false
      this.selected = id
      console.log(this.selected)
    },

    createPerson: function(){
      let body = {userName: this.userName, personRole: "customer"}
      AXIOS.post('/person/',body,{}).then(response => {
        this.persons.push(response.data)
        this.errorSignup = ''
      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorSignup = errorMsg
          this.error = true
        })
    },

    createOnlineAccountCustomer: function () {
      let body = {userName: this.userName, passwordSignup:this.passwordSignup, customerId: 60, email: this.email }
      AXIOS.post('/onlineaccount/customer/'+ this.userSignup + '/'+ this.passwordSignup,body,{}).then(response => {
        this.errorSignup = ''
      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorSignup = errorMsg
          this.error = true
        })
    },

    logIn: function () {
      console.log(this.userLogin)
      AXIOS.put('/login/'+this.userLogin+'/'+this.passwordLogin,{}).then(response => {
        this.errorLogin = ''
        this.$cookie.set("username", this.userLogin)

      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorLogin = errorMsg
          this.error = true
        })
    }

  }
}
