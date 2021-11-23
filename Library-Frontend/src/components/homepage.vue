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
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="800"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Text slides with image -->
      <b-carousel-slide
        img-src="https://picsum.photos/1024/480/?image=52"
      ></b-carousel-slide>

      <!-- Slides with custom text -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
        <h1>Hello world!</h1>
      </b-carousel-slide>

      <!-- Slides with image only -->
      <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="800"
            height="480"
            src="https://picsum.photos/1024/480/?image=55"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis, tincidunt
          a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
        </p>
      </b-carousel-slide>
    </b-carousel>

    <p class="mt-4">
      Slide #: {{ slide }}<br>
      Sliding: {{ sliding }}
    </p>
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
  background-color: #D7CeC7;
  /* Center child horizontally*/
  justify-content: center;
  flex-grow : 1;
  display: grid
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