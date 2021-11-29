<template>
  <div id="app">
 <b-navbar toggleable="md" type="light" variant="light" class="list-unstyled">
      <b-navbar variant="faded" type="light" left>
    <b-navbar-brand >
      <img src="https://i.ibb.co/L5shFK6/LMS-removebg-preview.png" alt="Logo" height="50" width="60">
    </b-navbar-brand>
    </b-navbar>
    <b-nav-item @click="iteminstance()">
     <a class="text-my-own-color ">
      Find movies, books, and music
    </a>

      </b-nav-item>
          <b-nav-item href="#" class="unstyled">
         <a class="text-my-own-color ">
     View Active Loans
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
          <b-dropdown-item @click="logout()">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>
         </b-navbar-nav>
     
  </b-navbar>
  <nav class="navbar fixed-bottom navbar-light" style="background-color: #76323F;">
<button class="left" @click="homepage()">
    <img src="https://cdn-icons-png.flaticon.com/512/25/25694.png" alt="Logo" height="30" width="30">
    </button>
</nav>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
  methods: {
    logout: function () {
      this.$cookie.delete('customerId');
      this.$router.push({name: 'login'});
    },
    profile(){
        this.$router.push({name:'Profile'});
      },
    iteminstance(){
        this.$router.push({name:'Item Instance'});
      },
    login(){
        this.$router.push({name:'login'});
      },
         homepage(){
        this.$router.push({name: 'HomePage'});
         }
  }
}

</script>

<style>


@import url("https://fonts.googleapis.com/css?family=Material+Icons");



.text-my-own-color {
  font-size:18px;
  font-family:monospace;
    color: black;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background-color: #ebe3dd;

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
.button {
  margin-right: 20px;
  align-items: center;
  background: #D7CEC7;
  font-weight: bold;
  height: 40px;
  margin-top: 10px;
}

.md-button {
  margin-top: 10px;
}

</style>
