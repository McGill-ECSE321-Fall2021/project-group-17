import Vue from 'vue'
<template class="main">
<div>
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