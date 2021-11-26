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
        movieTitle: '',
        director: '',
        runningTime: '',
        rating: '',
        filmDistributor: '',
        movieReleaseDate: '',
        musicName: '',
        musician: '',
        recordLabel: '',
        musicReleaseDate: '',
        newspaper: '',
        headline: '',
        newspaperDatePublished: ''
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
      },
      updateMovieFields: function(movie) {
        this.movieTitle = movie.name
        this.director = movie.director
        this.movieReleaseDate = movie.datePublished
        this.runningTime = movie.runningTime
        this.rating = movie.rating
        this.filmDistributor = movie.filmDistributor
      },
      updateMusicFields: function (music) {
        this.musicName = music.name
        this.musician = music.musician
        this.recordLabel = music.recordLabel
        this.musicReleaseDate = music.datePublished
      },
      addBook: function () {
        const json = JSON.stringify({
          name: this.title,
          datePublished: this.bookDatePublished,
          author: this.author,
          genre: this.genre,
          publisher: this.publisher
        });

        AXIOS.post('/item/book/1', json).then(response => {

        })
          .catch(e =>{
            console.log(e.response.data.message)
          })
      }
    }
}