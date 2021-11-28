import Vue from 'vue'
<template class="main">
<div>
 <b-navbar toggleable="md" type="light" variant="light">
      <b-navbar variant="faded" type="light" left>
    <b-navbar-brand >
      <img src="https://i.ibb.co/L5shFK6/LMS-removebg-preview.png" alt="Logo" height="50" width="60">
    </b-navbar-brand>
    </b-navbar>
    <b-nav-item href="#">
     <a class="text-my-own-color ">
      Find movies, books, and movies
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
          <b-dropdown-item href="#">Profile</b-dropdown-item>
          <b-dropdown-item href="#">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>
         </b-navbar-nav>
     
  </b-navbar>
  <div class="middle">
  <b-card no-body class="overflow-hidden" style="max-width: 840px;" >
    <b-row no-gutters>
      <b-col md="6">
        <b-card-img src="https://i.ibb.co/tzPwhww/Untitled-design-2.png" alt="Image" class="rounded-0"></b-card-img>
      </b-col>
      <b-col md="6">
        <b-card-body title="Profile Information">
         <b-list-group flush>
        <b-list-group-item>Username:</b-list-group-item>
        <b-list-group-item>Password:</b-list-group-item>
        <b-list-group-item>Address:</b-list-group-item>
        <b-list-group-item>LibraryCard:</b-list-group-item>
        <b-list-group-item></b-list-group-item>
         </b-list-group>
       <b-button @click="updateAccount()" pill variant="outline-secondary">Update Account</b-button>
        </b-card-body>
      </b-col>
    </b-row>
  </b-card>
</div>
<nav class="navbar fixed-bottom navbar-light" style="background-color: #76323F;">
<button class="left" @click="homepage()">
    <img src="https://cdn-icons-png.flaticon.com/512/25/25694.png" alt="Logo" height="30" width="30">
    </button>
</nav>
  </div>
</template>
<script>
export default {
    methods: {
  updateAccount(){
   this.$router.push({name: 'UpdateAccount'});
    },
  homepage(){
   this.$router.push({name: 'HomePage'});
    }
    }
  }

</script>
<style>
#eventregistration {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
  background: #f2ece8;
}
.text-my-own-color {
  font-size:18px;
  font-family:monospace;
    color: black;
}
.middle {
  position: fixed; 
  top: 25%;
  left: 20%;
}
.main {
  background-color: #D7CEC7;
}
.left {
  position: left;
}
</style>