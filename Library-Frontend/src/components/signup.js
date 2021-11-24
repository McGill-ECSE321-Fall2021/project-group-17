import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function PersonDTO (){}
const toLower = text => {
  return text.toString().toLowerCase()
}
const searchByName = (persons, term) => {
  if (term) {
    return persons.filter(person => toLower(person.name).includes(toLower(term)))
  }

  return persons
}

export default {
  name: 'signup',
  data () {
    return {
      persons: [],
      userName: null,
      userSignup: null,
      passwordSignup: null,
      streetNum: null,
      streetName: null,
      city: null,
      country: null,
      search: null,
      error: false
    }
  },
  created: function () {
    AXIOS.get('/signup/', {}).then(response => {
      let persons = response.data
      console.log("persons1", persons)
      persons = persons.filter(function (el){
        return el.name != null;
      })
      persons.forEach(function(element,index,array){
        if(element.name){
          array[index].name = "Music"
        }
      })
      this.items = persons
      console.log("persons2", this.items)
      this.searched = persons
    })
      .catch(e =>{
        this.error = e.response
      })
  },

  methods: {
    data () {
      return {
        showModal: true
      }
    },
    searchOnTable () {
      this.searched = searchByName(this.persons, this.search)
    },
    onSelect (person) {
      this.selected = person
      this.buttonEnabled = false
      console.log(this.selected)
    },
    openDialog () {
      this.dialog = true
    },



    createOnlineAccount: function () {
      console.log(this.userSignup)
      AXIOS.push('/login/'+this.userSignup+'/'+this.passwordSignup,{}).then(response => {
        this.errorLogin = ''
        this.$cookie.set("username", this.userSignup)
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
