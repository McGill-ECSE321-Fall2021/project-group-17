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
const searchById = (items, term) => {
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
      addressId: null,
      id: null,
      personRoleId: null
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
      })
  },
  methods: {
    onSearchClick() {
      this.searchDialog = true
    },
    searchOnTable(){
      this.searched = searchById(this.users, this.tableSearch)
    },
    onSelected(id){
      this.selected = id
      this.searchDialog = false
      //console.log(this.selected)
    },

    createPerson: function(){
      let body = {name: this.userName, personRole: "customer"}
      AXIOS.post('/person/',body,{}).then(response => {
        this.persons.push(response.data)
        this.id = response.data.id
        this.createAddress()
        this.errorSignup = ''
      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorSignup = errorMsg
          this.error = true
        })
    },

    createOnlineAccountCustomer: function (customerId) {
      console.log(customerId)
      AXIOS.post('/onlineaccount/customer/'+ this.userSignup + '/'+ this.passwordSignup + '/' + this.email + "?personRoleId="+customerId, {} ,{}).then(response => {
        this.$cookie.set("customerId", response.data.personRole.id)
        this.$router.push({name: 'Item Instance'});
        this.errorSignup = ''
      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorSignup = errorMsg
          this.error = true
        })
    },

    createCustomer: function (){
      let body = {personId: this.id, penalty: 0, addressId: this.addressId}
      console.log(body)
      AXIOS.post('/customer/1' ,body,{}).then(response => {
        console.log(response.data.id)
        this.createOnlineAccountCustomer(response.data.id)
        this.errorSignup = ''
      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorSignup = errorMsg
          this.error = true
        })
    },

    createAddress: function (){
      AXIOS.post('/address/1/' + this.streetNum + '/' + this.streetName + '/' + this.city + '/' + this.country,{},{}).then(response => {
        console.log(response.data.id)
        this.addressId = response.data.id
        this.createCustomer()
        this.errorSignup = ''
      })
        .catch(e => {
          let errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorSignup = errorMsg
          this.error = true
        })
    },

    personRoleFunction: function () {
      console.log(this.selected.id)
      this.createOnlineAccountCustomer(this.selected.id)
    }

  }
}
