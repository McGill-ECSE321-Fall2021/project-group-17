import Vue from 'vue'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default-dark.css'


<template>
  <div id="wrapper" class="overlay">
    <div id="reservation">
      <div id="table" class="layer2">
        <md-table v-model="searched" md-card md-fixed-header md-sort-order="asc" @md-selected="onSelect">
          <md-table-toolbar>
            <h1 class="md-title">Item Instances</h1>

            <md-field md-clearable class="md-toolbar-section-end">
              <md-input placeholder="Search by name..." v-model="search" @input="searchOnTable" />
            </md-field>
          </md-table-toolbar>

          <md-table-empty-state
            md-label="No Item Instances found"
            :md-description="`No item instance found for this search query. Try a different search term.`">
          </md-table-empty-state>


          <md-table-row slot="md-table-row" slot-scope="{ item }" class="md-primary" md-selectable="single">
            <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.checkableItem.id }}</md-table-cell>
            <md-table-cell md-label="Name" md-sort-by="name">{{ item.checkableItem.name }}</md-table-cell>
            <md-table-cell md-label="Date Published" md-sort-by="datePublished">{{ item.checkableItem.datePublished }}</md-table-cell>
            <md-table-cell md-label="Creator" md-sort-by="creator">{{ item.checkableItem.creator }}</md-table-cell>
            <md-table-cell md-label="Type" md-sort-by="type">{{ item.checkableItem.type }}</md-table-cell>
          </md-table-row>
        </md-table>
        <md-button :disabled="buttonEnabled" class="create-button" @click="openDialog()">Create Reservation</md-button>
        <reservation :dialog.sync="dialog" :itemInstanceId=selected.serialNum ></reservation>
      </div>
    </div>
  </div>
</template>
<script src="./iteminstance.js">
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
