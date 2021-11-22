import Vue from 'vue'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default-dark.css'

Vue.use(md-datepicker)

<template>
  <div id="wrapper" class="overlay">
    <div id="reservation">
      <h2 class="layer1">Make Reservation</h2>
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
          <!--<md-field>
            <md-input v-model="selectedDate">
              placeholder="date"
            </md-input>
          </md-field>-->
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
.div-element{
  display: flex;
  justify-content: center;
}
</style>
