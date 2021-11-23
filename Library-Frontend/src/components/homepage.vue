import Vue from 'vue'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default-dark.css'

Vue.use(md-datepicker)

<template>

<div>
  <b-navbar toggleable="lg" type="light" variant="light">
      <b-navbar variant="faded" type="light" left>
    <b-navbar-brand >
      <img src="https://www.clipartqueen.com/image-files/open-red-book-clipart.png" alt="Logo" height="30" width="30">
    </b-navbar-brand>
    </b-navbar>
       <b-nav-item-dropdown left>
          <template #button-content>
            <em>Library Services</em>
          </template>
          <b-dropdown-item href="#">Find items</b-dropdown-item>
          <b-dropdown-item href="#">View loans</b-dropdown-item>
          <b-dropdown-item href="#">Make Reservations</b-dropdown-item>
        </b-nav-item-dropdown>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    

      <!-- Right aligned nav items -->
   <b-navbar-nav class="ml-auto">
        <b-nav-form>
          <b-input-group prepend="What are you looking for today?">
          <b-form-input size="md" class="mr-sm-2" ></b-form-input>
          </b-input-group>
        </b-nav-form>


        <b-nav-item-dropdown right>
          <!-- Using 'button-content' slot -->
          <template #button-content>
            <img src="https://icons-for-free.com/iconfiles/png/512/person+user+icon-1320166085409390336.png" alt="Logo" height="30" width="30">
          </template>
          <b-dropdown-item href="#">Profile</b-dropdown-item>
          <b-dropdown-item href="#">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>
         </b-navbar-nav>
     
  </b-navbar>

    <div>
    <b-carousel
      id="carousel-1"
      :interval="4000"
      controls
      indicators
      background="#ffffff"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <b-carousel-slide>
      
           <template #img>
          <img
            width="800"
            height="400"
            src="https://i.ibb.co/NWyhxkx/WELCOME-TO-MONTREALS-BIGGEST-PUBLIC-LIBRARY-2.gif"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
      <b-carousel-slide >
         <template #img>
          <img
            width="400"
            height="400"
            src="https://media.timeout.com/images/105464457/750/562/image.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

           <b-carousel-slide >
         <template #img>
          <img
            width="400"
            height="400"
            src="https://media.timeout.com/images/105464457/750/562/image.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

         <b-carousel-slide >
         <template #img>
          <img
            width="400"
            height="400"
            src="https://media.timeout.com/images/105464457/750/562/image.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
      <b-carousel-slide >
         <template #img>
          <img
            width="400"
            height="400"
            src="https://media.timeout.com/images/105464457/750/562/image.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>
    </b-carousel>
  <div id="wrapper" class="overlay">
    <div id="homepage">
      <h2 class="layer1">Welcome User!</h2>
      <div id="options" class="layer2">
        <div id="Item" class="div-element">
          <h3 >Item Instance serial num:</h3>
          <md-field >
            <md-input v-model="itemId" placeholder="item instance">
            </md-input>
          </md-field>
        </div>
        <div id="Pickup day" class="div-element">
          <h3 >Pickup date:</h3>
          <md-datepicker v-model="selectedDate" md-immediately />
        </div>

        <div id="make reservation">
          <button @click="createReservation()">Make Reservation</button>
        </div>
        <div id="error">
          <md-dialog-alert :md-active.sync="error" :md-content="errorReservation" md-confirm-text="OK!" />
          </div>
        </div>
      </div>
    </div>
    </div>
    </div>
</template>
<script>
export default {
    data() {
      return {
        slide: 0,
        sliding: null
      }
    },
    methods: {
      onSlideStart(slide) {
        this.sliding = true
      },
      onSlideEnd(slide) {
        this.sliding = false
      }
    }
  }
</script>
<style>
  .md-content {
    max-width: 400px;
    max-height: 200px;
    overflow: auto;
  }
#homepage {
  font-family: monospace;
  font-size: 24px;
  outline: dashed 1px black;
  background-color: #b9b4b477;
  /* Center child horizontally*/
  justify-content: center;
  flex-grow : 1;
  display: grid
}
.title {
  font-family: monospace;
  font-size: 50px;
  color: black;

}
#wrapper {
  background-color: #565656;
    display: flex;
  align-items: center;
  justify-content:center;
  height: 50%;
  padding: 3% 7.5% 10%;
}
.layer1{
  grid-column: 1;
  grid-row: 1
}
.layer2{
  grid-column: 1;
  grid-row: 2;
}
.div-element{
  display: flex;
  justify-content: center;
}
</style>