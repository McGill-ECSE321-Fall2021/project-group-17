import axios from 'axios'
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
      return "http://" + config.dev.host + ":" + config.dev.port;
    case "production":
      return (
        "http://" + config.build.host + ":" + config.build.port
      );
  }
};

var backendUrl = backendConfigurer();
var frontendUrl = frontendConfigurer();

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

function findIndex (array, item) {
  for (let i = 0; i < array.length; i++) {
    if (array[i] == item) {
      return i
    }
  }

return -1
}

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
        newspaperDatePublished: '',
        error: '',
        serialNum: ''
      }
    },
    created: function () {
      AXIOS.get('/book').then(response => {
        this.books = response.data
        console.log(response.data)
      })
        .catch(e =>{
          this.error = e.response.data.message
          console.log(e.response.data.message)
        })

      AXIOS.get('/movie').then(response => {
        this.movies = response.data
      })
        .catch(e =>{
          this.error = e.response.data.message
          console.log(e.response.data.message)
        })

      AXIOS.get('/music').then(response => {
        this.music = response.data
      })
        .catch(e =>{
          this.error = e.response.data.message
          console.log(e.response.data.message)
        })

      AXIOS.get('/newspaper').then(response => {
        this.newspapers = response.data
      })
        .catch(e =>{
          this.error = e.response.data.message
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
        this.error = ''
        AXIOS.post('/item/book/' + this.$cookie.get('customerId') + '/' + this.title + '/' + this.bookDatePublished + '/' + this.author + '/' + this.publisher + '/' + this.genre).then(response => {
          this.books.push(response.data)
          this.title= ''
          this.author= ''
          this.bookDatePublished= ''
          this.genre= ''
          this.publisher= ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })
      },
      addBookInstance: function () {
        this.error = ''
        AXIOS.post('/iteminstance/' + this.bookSelected.id).then(response => {
          this.title= ''
          this.author= ''
          this.bookDatePublished= ''
          this.genre= ''
          this.publisher= ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })
      },
      addMovie: function () {
        this.error = ''
        AXIOS.post('/item/movie/' + this.$cookie.get('customerId') + '/' + this.movieTitle + '/' + this.movieReleaseDate + '/' + this.director + '/' + this.runningTime + '/' + this.rating + '/' + this.filmDistributor).then(response => {
          this.movies.push(response.data)
          this.movieTitle = ''
          this.director = ''
          this.movieReleaseDate = ''
          this.runningTime = ''
          this.rating = ''
          this.filmDistributor = ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })
      },
      addMovieInstance: function () {
        this.error = ''
        AXIOS.post('/iteminstance/' + this.movieSelected.id).then(response => {
          this.movieTitle = ''
          this.director = ''
          this.movieReleaseDate = ''
          this.runningTime = ''
          this.rating = ''
          this.filmDistributor = ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })
      },
      addMusic: function () {
        this.error = ''
        AXIOS.post('/item/music/' + this.$cookie.get('customerId') + '/' + this.musicName + '/' + this.musicReleaseDate + '/' + this.musician + '/' + this.recordLabel).then(response => {
          this.music.push(response.data)
          this.musicName = ''
          this.musician = ''
          this.recordLabel = ''
          this.musicReleaseDate = ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })
      },
      addMusicInstance: function () {
        this.error = ''
        AXIOS.post('/iteminstance/' + this.musicSelected.id).then(response => {
          this.musicName = ''
          this.musician = ''
          this.recordLabel = ''
          this.musicReleaseDate = ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })
      },
      addNewspaper: function () {
        this.error = ''
        AXIOS.post('/item/newspaper/' + this.$cookie.get('customerId') + '/' + this.newspaper + '/' + this.headline + '/' + this.newspaperDatePublished).then(response => {
          this.newspapers.push(response.data)
          this.newspaper = ''
          this.headline = ''
          this.newspaperDatePublished = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })
      },
      deleteBook: function () {
        AXIOS.delete('/item/book/' + this.$cookie.get('customerId') + '/' + this.bookSelected.id).then(response => {
          this.title= ''
          this.author= ''
          this.bookDatePublished= ''
          this.genre= ''
          this.publisher= ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })

          this.$delete(this.books, findIndex(this.books, this.bookSelected))
      },
      deleteMovie: function () {
        this.error = ''
        AXIOS.delete('/item/movie/' + this.$cookie.get('customerId') + '/' + this.movieSelected.id).then(response => {
          this.movieTitle = ''
          this.director = ''
          this.movieReleaseDate = ''
          this.runningTime = ''
          this.rating = ''
          this.filmDistributor = ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })

          this.$delete(this.movies, findIndex(this.movies, this.movieSelected))
      },
      deleteMusic: function () {
        this.error = ''
        AXIOS.delete('/item/music/' + this.$cookie.get('customerId') + '/' + this.musicSelected.id).then(response => {
          this.musicName = ''
          this.musician = ''
          this.recordLabel = ''
          this.musicReleaseDate = ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })

          this.$delete(this.music, findIndex(this.music, this.musicSelected))
      },
      deleteNewspaper: function () {
        this.error = ''
        AXIOS.delete('/item/newspaper/' + this.$cookie.get('customerId') + '/' + this.newspaperSelected.id).then(response => {
          this.newspaper = ''
          this.headline = ''
          this.newspaperDatePublished = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })

          this.$delete(this.newspapers, findIndex(this.newspapers, this.newspaperSelected))
      },
      deleteBySerialNum: function () {
        this.error = ''
        AXIOS.delete('/iteminstance/' + this.serialNum).then(response => {
          this.title= ''
          this.author= ''
          this.bookDatePublished= ''
          this.genre= ''
          this.publisher= ''
          this.movieTitle = ''
          this.director = ''
          this.movieReleaseDate = ''
          this.runningTime = ''
          this.rating = ''
          this.filmDistributor = ''
          this.musicName = ''
          this.musician = ''
          this.recordLabel = ''
          this.musicReleaseDate = ''
          this.serialNum = ''
        })
          .catch(e =>{
            this.error = e.response.data.message
            console.log(e.response.data.message)
          })

      }
    }
}
