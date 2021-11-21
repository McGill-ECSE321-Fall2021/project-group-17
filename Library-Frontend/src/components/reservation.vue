import Vue from 'vue'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default-dark.css'

Vue.use(md-datepicker)

<template>
  <div id="reservation">
    <h2>Make Reservation</h2>
    <div id="options">
      <div id="Item">
        <md-field>
          <md-input v-model="itemId">
             placeholder="item instance"
          </md-input>
        </md-field>
      </div>
      <div id="Pickup day">
        <!--<md-datepicker v-model="selectedDate" md-immediately />-->
        <md-field>
          <md-input v-model="selectedDate">
            placeholder="date"
          </md-input>
        </md-field>
      </div>

      <div id="make reservation">
        <button @click="createReservation()">Make Reservation</button>
      </div>
      <div id="error">
        <md-dialog-alert :md-active.sync="error" :md-content="errorReservation" md-confirm-text="OK!" />
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
  font-family: arial;
  font-size: 24px;
  margin: 25px;
  outline: dashed 1px black;
  /* Center child horizontally*/
  display: flex;
  justify-content: center;
}
</style>
