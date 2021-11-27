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
        bookSelected: [],
        movieSelected: [],
        musicSelected: [],
        newspaperSelected: [],
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
        this.bookSelected = book
        this.title = book.name
        this.author = book.author
        this.bookDatePublished = book.datePublished
        this.genre = book.genre
        this.publisher = book.publisher
      },
      updateMovieFields: function(movie) {
        this.movieSelected = movie
        this.movieTitle = movie.name
        this.director = movie.director
        this.movieReleaseDate = movie.datePublished
        this.runningTime = movie.runningTime
        this.rating = movie.rating
        this.filmDistributor = movie.filmDistributor
      },
      updateMusicFields: function (music) {
        this.musicSelected = music
        this.musicName = music.name
        this.musician = music.musician
        this.recordLabel = music.recordLabel
        this.musicReleaseDate = music.datePublished
      },
      updateNewspaperFields: function (newspaper) {
        this.newspaperSelected = newspaper
        this.newspaper = newspaper.name
        this.headline = newspaper.headline
        this.newspaperDatePublished = newspaper.datePublished
      },
      addBook: function () {
        AXIOS.post('/item/book/2/' + this.title + '/' + this.bookDatePublished + '/' + this.author + '/' + this.publisher + '/' + this.genre).then(response => {
          console.log(response.data)
        })
          .catch(e =>{
            console.log(e.response.data.message)
          })

        AXIOS.get('/book').then(response => {
          this.books = response.data
        })
          .catch(e =>{
            console.log(e.response.data.message)
          })

          AXIOS.get('/book').then(response => {
            this.books = response.data
          })
            .catch(e =>{
              console.log(e.response.data.message)
            })
      },
      addMovie: function () {
        AXIOS.post('/item/movie/2/' + this.movieTitle + '/' + this.movieReleaseDate + '/' + this.director + '/' + this.runningTime + '/' + this.rating + '/' + this.filmDistributor).then(response => {
          console.log(response.data)
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
      },
      addMusic: function () {
        AXIOS.post('/item/music/2/' + this.musicName + '/' + this.musicReleaseDate + '/' + this.musician + '/' + this.recordLabel).then(response => {
          console.log(response.data)
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
      },
      addNewspaper: function () {
        AXIOS.post('/item/newspaper/2/' + this.newspaper + '/' + this.headline + '/' + this.newspaperDatePublished).then(response => {
          console.log(response.data)
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
      deleteBook: function () {
        AXIOS.delete('/item/book/2/' + this.bookSelected.id).then(response => {

        })
          .catch(e =>{
            console.log(e.response.data.message)
          })

        AXIOS.get('/book').then(response => {
          this.books = response.data
        })
          .catch(e =>{
            console.log(e.response.data.message)
          })
      },
      deleteMovie: function () {
        AXIOS.delete('/item/movie/2/' + this.movieSelected.id).then(response => {
          
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
      },
      deleteMusic: function () {
        AXIOS.delete('/item/music/2/' + this.musicSelected.id).then(response => {
          
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
      },
      deleteNewspaper: function () {
        AXIOS.delete('/item/newspaper/2/' + this.newspaperSelected.id).then(response => {
          
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
      }
    }
}