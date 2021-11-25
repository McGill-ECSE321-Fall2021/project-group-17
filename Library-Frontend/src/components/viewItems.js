import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'returns',
    mounted: function() {
      this.$material.theming.theme = "colors"; 
    },
    data () {
      return {
        itemSelected: '',
        books: [],
        movies: [],
        music: [],
        newspapers: [],
        title: '',
        author: '',
        bookDatePublished: '',
        genre: '',
        publisher: '',
      }
    },
    created: function () {
      AXIOS.get('/book').then(response => {
        this.books = response.data
      })
        .catch(e =>{
          console.log(e.response.data.message)
        })

      AXIOS.get('/movie').then(response => {
        this.movies = response.data
      })
        .catch(e =>{
          console.log(e.response.data.message)
        })

      AXIOS.get('/music').then(response => {
        this.music = response.data
      })
        .catch(e =>{
          console.log(e.response.data.message)
        })

      AXIOS.get('/newspaper').then(response => {
        this.newspapers = response.data
      })
        .catch(e =>{
          console.log(e.response.data.message)
        })
    },
    methods: {
      updateBookFields: function (book) {
        this.title = book.name
        this.author = book.author
        this.bookDatePublished = book.datePublished
        this.genre = book.genre
        this.publisher = book.publisher
      }
    }
}