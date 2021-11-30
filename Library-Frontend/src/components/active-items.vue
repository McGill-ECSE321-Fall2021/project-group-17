import Vue from 'vue'
<template>
  <div id="wrapper">
      <div id="table" class="layer2">
          <md-tabs class="md-primary" md-alignment="centered">
            <md-tab id="tab-home" md-label="Active Reservations">
              <md-table v-model="searchedReservations" md-card md-fixed-header md-sort-order="asc"  @md-selected="onSelectReservation">
                <md-table-toolbar>
                  <md-field md-clearable class="md-toolbar-section-end">
                    <md-input placeholder="Search by name..." v-model="search" @input="searchOnTableReservation" />
                  </md-field>
                </md-table-toolbar>

                <md-table-empty-state
                  md-label="No Reservations found"
                  :md-description="`No reservations found for this search query. Try a different search term.`">
                </md-table-empty-state>

                <md-table-row slot="md-table-row" slot-scope="{ item }" class="md-primary" md-selectable="single">
                  <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
                  <md-table-cell md-label="Date Reserved" md-sort-by="datePublished">{{ item.dateReserved }}</md-table-cell>
                  <md-table-cell md-label="Pickup Date" md-sort-by="name">{{ item.pickupDay }}</md-table-cell>
                  <md-table-cell md-label="Name of Item" md-sort-by="creator">{{ item.itemInstance.checkableItem.name }}</md-table-cell>
                </md-table-row>
              </md-table>
              <md-button class="create-button" @click="deleteReservation">Delete Reservation</md-button>
            </md-tab>
            <md-tab id="tab-pages" md-label="Active Loans">
              <md-table v-model="searchedLoans" md-card md-fixed-header md-sort-order="asc" @md-selected="onSelectLoan">
                <md-table-toolbar>
                  <md-field md-clearable class="md-toolbar-section-end">
                    <md-input placeholder="Search by name..." v-model="search" @input="searchOnTableLoan" />
                  </md-field>
                </md-table-toolbar>

                <md-table-empty-state
                  md-label="No Loans found"
                  :md-description="`No loans found for this search query. Try a different search term.`">
                </md-table-empty-state>

                <md-table-row slot="md-table-row" slot-scope="{ item }" class="md-primary" md-selectable="single">
                  <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
                  <md-table-cell md-label="Checked Out Date" md-sort-by="datePublished">{{ item.checkedOut }}</md-table-cell>
                  <md-table-cell md-label="Return Date" md-sort-by="name">{{ item.returnDate }}</md-table-cell>
                  <md-table-cell md-label="Name of Item" md-sort-by="creator">{{ item.itemInstance.checkableItem.name }}</md-table-cell>
                </md-table-row>
              </md-table>
              <md-button class="create-button" @click="deleteLoan">Delete Loan</md-button>
            </md-tab>
          </md-tabs>
      </div>
  </div>
</template>


<script src="./active-items.js">
</script>
<style>

p {
  line-height: 1rem;
}

.acct {
  line-height: 1rem;
}

.card {
  padding: 0px;
  font-family: monospace;
  color: #76323F;
  background: #D7CEC7;
  align-items: center;
  font-size: 0.8rem;

}


.md-primary {
  padding: 20px;
  font-family: monospace;
  color: #76323F;
  background: #D7CEC7;
  font-size: 0.8rem;

}
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
.layer2{
  grid-column: 1;
  grid-row: 2;
}


</style>

