import Vue from 'vue'
<template class="main">
<div>
  <div class="middle">
  <b-card no-body class="overflow-hidden" style="max-width: 840px;" >
    <b-row no-gutters>
      <b-col md="6">
        <b-card-img src="https://i.ibb.co/tzPwhww/Untitled-design-2.png" alt="Image" class="rounded-0"></b-card-img>
      </b-col>
      <b-col md="6">
        <b-card-body title="Profile Information">
         <b-list-group flush>
        <b-list-group-item v-model="user">Username: {{user}}</b-list-group-item>
        <b-list-group-item v-model="password">Password: {{password}}</b-list-group-item>
        <b-list-group-item v-model="Address">Address: {{Address}}</b-list-group-item>
        <b-list-group-item v-model="Email">Email: {{email}} </b-list-group-item>
        <b-list-group-item v-model="libCard">LibraryCard: {{libCard}}</b-list-group-item>
        <b-list-group-item></b-list-group-item>
         </b-list-group>
       <b-button @click="updateAccountPage()" pill variant="outline-secondary">Update Account</b-button>
        </b-card-body>
      </b-col>
    </b-row>
  </b-card>
</div>

  </div>
</template>
<script src="./Profile.js">

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