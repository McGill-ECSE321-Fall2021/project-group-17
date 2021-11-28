import Vue from 'vue'
<template class="main">
<div>
 <b-navbar toggleable="md" type="light" variant="light" class="list-unstyled">
      <b-navbar variant="faded" type="light" left>
    <b-navbar-brand >
      <img src="https://i.ibb.co/L5shFK6/LMS-removebg-preview.png" alt="Logo" height="50" width="60">
    </b-navbar-brand>
    </b-navbar>
    <b-nav-item href="#">
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
          <b-dropdown-item @click="profile()" href="#">Profile</b-dropdown-item>
          <b-dropdown-item href="#">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>
         </b-navbar-nav>
     
  </b-navbar>
  <div class="middle">
  <b-card no-body style="max-width: 840px;"  height="400">
    <b-row no-gutters>
      <b-col md="6">
        <b-card-img src="https://i.ibb.co/tzPwhww/Untitled-design-2.png" alt="Image" class="rounded-0"></b-card-img>
      </b-col>
      <b-col md="6">
        <b-card-body title="Profile Information">
         <b-list-group flush>
        <b-list-group-item><b-input-group prepend="Email">
        <b-form-input v-model="email" placeholder="New email"></b-form-input>
      </b-input-group></b-list-group-item>
        <b-list-group-item><b-input-group prepend="Password">
        <b-form-input v-model="password" placeholder="New Password"></b-form-input>
      </b-input-group></b-list-group-item>
        <b-list-group-item></b-list-group-item>
        <b-list-group-item></b-list-group-item>
        <b-list-group-item></b-list-group-item>
        <b-list-group-item></b-list-group-item>
         </b-list-group>
       <b-button @click= "updateAccount()" pill variant="outline-secondary">Confirm Update</b-button>
        </b-card-body>
      </b-col>
    </b-row>
  </b-card>
</div>
 <div class="middle_bottom">
  <b-card no-body style="max-width: 840px;" height="400" >
    <b-row no-gutters>
      <b-col md="6">
        <b-card-img src="https://i.ibb.co/LRF4wz9/Untitled-design-4.png" alt="Image" class="rounded-0"></b-card-img>
      </b-col>
      <b-col md="6">
        <b-card-body title="Address Information">
         <b-list-group flush>
        <b-list-group-item><b-input-group prepend="Street Number">
        <b-form-input v-model="streetNum" placeholder="New street number"></b-form-input>
      </b-input-group></b-list-group-item>
        <b-list-group-item><b-input-group prepend="Street Name">
        <b-form-input v-model="streetName" placeholder="New street name"></b-form-input>
      </b-input-group></b-list-group-item>
        <b-list-group-item><b-input-group prepend="City">
        <b-form-input v-model="city" placeholder="New City"></b-form-input>
      </b-input-group></b-list-group-item>
        <b-list-group-item><b-input-group prepend="Country">
        <b-form-input v-model="Country" placeholder="New Country"></b-form-input>
      </b-input-group></b-list-group-item>
        <b-list-group-item></b-list-group-item>
         </b-list-group>
       <b-button @click= "updateAccount()" pill variant="outline-secondary">Confirm Update</b-button>
        </b-card-body>
      </b-col>
    </b-row>
  </b-card>
</div>
<nav class="navbar navbar-light" style="background-color: #76323F;">
<button class="left" @click="homepage()" >
    <img src="https://cdn-icons-png.flaticon.com/512/25/25694.png" alt="Logo" height="30" width="30">
    </button>
</nav>
  </div>
</template>
<script src="./UpdateAccount.js">
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
  margin-top:5%;
  position: relative; 
  top: 25%;
  left: 20%;
  margin-bottom:5%;
}
.middle_bottom {
  position: relative; 
  top: 95%;
  left: 20%;
  margin-bottom:10%;
}
.main {
  background-color: #D7CEC7;
}
.left {
  position: left;
}
</style>