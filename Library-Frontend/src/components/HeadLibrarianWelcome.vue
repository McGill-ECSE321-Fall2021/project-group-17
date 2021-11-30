import Vue from 'vue'
import Cookie from 'cookie'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default-dark.css'
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'

Vue.use(md-datepicker)

<template>

  <div>
    <b-navbar toggleable="md" type="light" variant="light">
      <b-navbar variant="faded" type="light" left>
        <b-navbar-brand >
          <img src="https://i.ibb.co/L5shFK6/LMS-removebg-preview.png" alt="Logo" height="50" width="60">
        </b-navbar-brand>
      </b-navbar>
      <b-nav-item href="#/viewitems">
        <a class="text-my-own-color ">
          Find movies and books
        </a>

      </b-nav-item>
      <b-nav-item href="#/schedule" class="unstyled">
        <a class="text-my-own-color ">
          Update Schedule
        </a>
      </b-nav-item >
      <b-nav-item href="#/shifts" class="unstyled">
        <a class="text-my-own-color ">
          View Shifts
        </a>
      </b-nav-item >

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
          <b-dropdown-item @click="profile()">Profile</b-dropdown-item>
          <b-dropdown-item>Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>

    </b-navbar>
    <div id="carousel">
      <v-row align="center" justify="center">
        <div id="welcomeMSG">
          <h1 id="welcome">
            Welcome
            <h2 id="HeadLibrarian">Head Librarian</h2>
          </h1>
        </div>
      </v-row>
      <b-carousel
        id="carousel-1"
        :interval="4000"
        controls
        indicators
        background="#d7cec7"
        style="text-shadow: 1px 1px 2px #333;"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
      >
        <b-carousel-slide>

          <template #img>
            <img
              width="840"
              height="600"
              src="https://i.ibb.co/NWyhxkx/WELCOME-TO-MONTREALS-BIGGEST-PUBLIC-LIBRARY-2.gif"
              alt="image slot"
            >
          </template>
        </b-carousel-slide>

        <b-carousel-slide >
          <template #img>
            <img
              width="840"
              height="600"
              src="https://media.timeout.com/images/105464457/750/562/image.jpg"
              alt="image slot"
            >
          </template>
        </b-carousel-slide>

        <b-carousel-slide >
          <template #img>
            <img
              width="840"
              height="600"
              src="https://media2.ledevoir.com/images_galerie/nwd_746098_584235/image.jpg"
              alt="image slot"
            >
          </template>
        </b-carousel-slide>

        <b-carousel-slide >
          <template #img>
            <img
              width="840"
              height="600"
              src="https://newtechinstallation.com/wp-content/uploads/2013/05/newtech_photos_27-1.jpg"
              alt="image slot"
            >
          </template>
        </b-carousel-slide>
      </b-carousel>
      <div id="wrapper" class="overlay">
        <h2 class="layer1">UPCOMING EVENTS</h2>
      </div>
    </div>
    <div>
      <div>
        <b-card-group deck>

          <b-card
            no-body
            style="max-width: 20rem;"
            img-src="https://i.ibb.co/C6bmGBZ/Untitled-design.png"
            img-height=200
            img-alt="Image"
            img-top
          >
            <template #header>
              <h4 class="mb-0">DECEMBER 14TH </h4>
            </template>

            <b-card-body>
              <b-card-title>Reading with Santa</b-card-title>
              <b-card-text>
                Join us at our main branch for a holiday themed reading circle with Santa Clause
              </b-card-text>
            </b-card-body>

            <b-list-group flush>
              <b-list-group-item>TIME: 11:00 am</b-list-group-item>
              <b-list-group-item>AGES: 3-12</b-list-group-item>
            </b-list-group>

            <b-card-body>
              <a href="#" class="card-link">Learn More</a>
            </b-card-body>

          </b-card>


          <b-card
            no-body
            style="max-width: 20rem;"
            img-src="https://i.ibb.co/C6bmGBZ/Untitled-design.png"
            img-height=200
            img-alt="Image"
            img-top
          >
            <template #header>
              <h4 class="mb-0">DECEMBER 22ND</h4>
            </template>

            <b-card-body>
              <b-card-title>Holiday Bakesale</b-card-title>
              <b-card-text>
                Out of time to make bake this holiday season, we got you covered! Join us for a wide selection of holiday goods
              </b-card-text>
            </b-card-body>

            <b-list-group flush>
              <b-list-group-item>TIME: 10:00AM to 3:00PM</b-list-group-item>
              <b-list-group-item>ALL ARE WELCOME</b-list-group-item>
            </b-list-group>

            <b-card-body>
              <a href="#" class="card-link">Learn More</a>
            </b-card-body>
          </b-card>

          <b-card
            no-body
            style="max-width: 20rem;"
            img-src="https://i.ibb.co/C6bmGBZ/Untitled-design.png"
            img-height=200
            img-alt="Image"
            img-top
          >
            <template #header>
              <h4 class="mb-0">JANUARY 5TH</h4>
            </template>

            <b-card-body>
              <b-card-title>New Year Resolution Writing</b-card-title>
              <b-card-text>
                A very special guest will be guiding our community through the best practices for setting resolutions that you can achieve!
              </b-card-text>
            </b-card-body>

            <b-list-group flush>
              <b-list-group-item>TIME: 1:00PM</b-list-group-item>
              <b-list-group-item>AGES: 16+</b-list-group-item>
            </b-list-group>

            <b-card-body>
              <a href="#" class="card-link">Learn more</a>
            </b-card-body>
          </b-card>
          <b-card
            no-body
            style="max-width: 20rem;"
            img-src="https://i.ibb.co/C6bmGBZ/Untitled-design.png"
            img-height=200
            img-alt="Image"
            img-top
          >
            <template #header>
              <h4 class="mb-0">JANUARY 18TH</h4>
            </template>

            <b-card-body>
              <b-card-title>Brit Bennett Book Signing</b-card-title>
              <b-card-text>
                Author of NYT best selling book 'The Vanishing Half' will be signing copies at our main branch.
              </b-card-text>
            </b-card-body>

            <b-list-group flush>
              <b-list-group-item>TIME: 5:00PM</b-list-group-item>
              <b-list-group-item>ALL ARE WELCOME</b-list-group-item>
            </b-list-group>

            <b-card-body>
              <a href="#" class="card-link">Learn more</a>
            </b-card-body>
          </b-card>
        </b-card-group>
      </div>
    </div>
    <div id="footer" class="overlay">
      <b-container>
        <b-row>
          <b-col> <b>LIBRARY INFORMATION</b> </b-col>
          <b-col ><b>CONTACT US</b></b-col>
          <b-col><b>ABOUT US</b></b-col>
          <b-col><b>SIGN UP FOR OUR NEWSLETTER</b></b-col>
        </b-row>
        <b-row>
          <b-col>ADDRESS:</b-col>
          <b-col>EMAIL:</b-col>
          <b-col><b-link href="#foo"><a class="text-my-own-color">Mission Statement</a></b-link></b-col>
          <b-col><b-form-input v-model="text" placeholder="Enter your email"></b-form-input></b-col>
        </b-row>
        <b-row>
          <b-col>WEEKLY HOURS</b-col>
          <b-col>PHONE NUMBER:</b-col>
          <b-col><b-link href="#foo"><a class="text-my-own-color">Our History</a></b-link></b-col>
          <b-col></b-col>
        </b-row>
        <b-row>
          <b-col></b-col>
          <b-col></b-col>
          <b-col><b-link href="#foo"><a class="text-my-own-color">Support the Library</a></b-link></b-col>
          <b-col><img src="https://upload.wikimedia.org/wikipedia/commons/0/0c/Facebook%2BIcon%2BBlack.png" alt="Logo" height="20" width="20" left>
            <img src="https://i.ibb.co/Z6bPFc0/5ecec78673e4440004f09e77.png" alt="Logo" height="20" width="20" >

            <img src="https://icon-library.com/images/twitter-icon-png-white/twitter-icon-png-white-9.jpg" alt="Logo" height="25" width="25" >
          </b-col>
        </b-row>
      </b-container>

    </div>

  </div>
</template>
<script>
//import $cookie from "babel-register/lib/cache";
//require("@babel/register");
export default {
  data() {
    return {
      slide: 0,
      sliding: null
    }
  },
  methods: {
    setUsername(){
     //document.getElementById("HeadLibrarian").innerHTML = get username from cookie

    },
    onSlideStart(slide) {
      this.sliding = true
    },
    onSlideEnd(slide) {
      this.sliding = false
    },
    profile(){
      this.$router.push({name:'Profile'});
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
  background-color: #fdebd077;
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
  background-color: #76323F;
  display: flex;
  font-size: 100px;
  font-family: monospace;
  color: black;
  align-items: center;
  justify-content:center;
  height: 25%;
  padding: 3% 3% 3%;
}
#footer {
  background-color: #76323F;
  display: flex;
  font-size: 20px;
  font-family: monospace;
  color: black;
  align-items: center;
  justify-content:center;
  height: 25%;
  padding: 3% 3% 3%;
}
.text-my-own-color {
  font-size:18px;
  font-family:monospace;
  color: black;
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
#welcomeMSG {
  font-family: Apple Chancery, cursive;
  position: absolute;
  right: 880px;
  top: 90px;
  z-index: 3;
  color: #76323f;
}
#welcome {
  position: relative;
  right: 300px;
  top: 30px;
  font-size: xxx-large;
  margin-bottom: 120px;
  margin-left: 40px;
}
</style>
