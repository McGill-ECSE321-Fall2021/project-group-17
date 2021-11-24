<template>
  <div id="app">
    <div id="LMS">
      <h1>Library Management System</h1>
      <md-button to="/" class="md-icon-button">
        <md-icon>home</md-icon>
      </md-button>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style lang="scss">


@import url("https://fonts.googleapis.com/css?family=Material+Icons");

@import "~vue-material/dist/theme/engine.scss";

@include md-register-theme("default", (
  primary: #76323F, // The primary color of your application
  accent: #C09F80 // The accent or secondary color
));

@import "~vue-material/dist/theme/all";



#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background-color: #C09F80;

}
#LMS {
  display:flex;
  justify-content:space-between;
  outline: 2px #76323F;
  color: #565656;
}
.overlay {
  position: fixed;
  width: 100%;
  height: 100%;
  left: 0;
  z-index: 10;
}
.create-button {
  background-color: #76323F;
  color: white !important;

}
</style>
