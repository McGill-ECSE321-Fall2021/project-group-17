import Vue from 'vue'
<template>
  <div id="app">
    <div class="signup-page">
      <div class="container">
        <div class="col-lg-4 col-md-6 col-sm-8 mx-auto">
          <div class="card signup">
            <md-tabs class="md-primary" md-alignment="centered">
              <md-tab id="tab-home" md-label="Active Reservations">
                <md-table>
                  <md-table v-model="searchedReservations" md-card md-fixed-header md-sort-order="asc">
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
                </md-table>
              </md-tab>
              <md-tab id="tab-pages" md-label="Active Loans">
                <md-table>
                  <md-table v-model="searchedLoans" md-card md-fixed-header md-sort-order="asc">
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
                </md-table>
              </md-tab>
            </md-tabs>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script src="./active-items.js">
</script>

<style scoped>
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

.signup-input {
  margin-top: 15px;
  margin-left: 10px;
  height: 25px
}

.search{
  margin-right: 20px;
  height: 25px;
  margin-top: 20px;
}
.btn {
  width: 100px;
  height: 25px;
  margin-bottom: 20px;
  margin-top: 20px;
  color: #D7CEC7;
  background: #76323f;
  text-align: center;
  align-items: center;
}

.signup-page {
  align-items: center;
  display: flex;
  height: 100vh;
  background: url(https://images.unsplash.com/photo-1507842217343-583bb7270b66?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTB8fHxlbnwwfHx8fA%3D%3D&w=1000&q=80);
}

h1 {
  margin-bottom: 20px;
}


.address-label {
  margin-top: 30px;
  margin-bottom: -15px;
}


</style>

