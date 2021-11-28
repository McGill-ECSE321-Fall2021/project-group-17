import Vue from 'vue'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default-dark.css'


<template>
  <md-dialog :md-active.sync="dialog" >
    <md-dialog-title>
      <span>Make Reservation</span>
      <md-button id="close" class="md-icon-button" @click="close()">
        <md-icon>close</md-icon>
      </md-button>
    </md-dialog-title>

    <div id="options" class="wrapper">
      <div id="Pickup day" class="dialog" >

        <label class="label" >Pickup </label>
        <label class="label" >Date</label>
        <md-datepicker v-model="selectedDate" md-immediately class="date" />
        <!--<md-field>
          <md-input v-model="selectedDate">
            placeholder="date"
          </md-input>
        </md-field>-->
      </div>

      <div id="make reservation" class="dialog">
        <md-button @click="createReservation()" class="create-button">Make Reservation</md-button>
      </div>
      <div id="error">
        <md-dialog-alert :md-active.sync="error" :md-content="errorReservation" md-confirm-text="OK!" />
      </div>
    </div>
  </md-dialog>
</template>
<script src="./reservation.js">
/*export default {
  name: 'BasicDatepicker',
  data: () => ({
    selectedDate: null
  })
}*/
</script>
<style>
#reservation {
  font-family: monospace;
  font-size: 24px;
  outline:  20px black;
  background-color: #D7CeC7;
  /* Center child horizontally*/
  justify-content: center;
  flex-grow : 1;
  display: grid
}
.wrapper {
  background-color: #C09F80;
  display: flex;
  flex-flow: column;
  height: 100%;
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
#close{
  float: right;
}
.dialog{
  border: black 2px;
  font-family: monospace;
  font-size: 20px;
  display: flex;
  flex-flow: row;
  justify-content: center;
  margin-left: 5px;
  margin-right: 10px;
}

.label{
  justify-content: left;
  margin-top: 27px;
  margin-right: 15px;
}

.date {
  margin-left: 10px;
}


</style>
