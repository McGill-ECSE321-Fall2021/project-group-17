import Vue from 'vue'
<template>
  <div>
    <div class="signup-page">
      <div class="col-lg-4 col-md-6 col-sm-8 mx-auto">
      <md-table v-model="users" md-card>
        <md-table-toolbar>
          <h1 class="md-title">Users</h1>
        </md-table-toolbar>

        <md-table-row slot="md-table-row" slot-scope="{ item }">
          <md-table-cell md-label="Name" md-sort-by="name">{{ item.name }}</md-table-cell>
        </md-table-row>
      </md-table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TableTemplate',
  data: () => ({
    users: [
      {
        id: 1,
        name: 'Shawna Dubbin',
        email: 'sdubbin0@geocities.com',
        gender: 'Male',
        title: 'Assistant Media Planner'
      },
      {
        id: 2,
        name: 'Odette Demageard',
        email: 'odemageard1@spotify.com',
        gender: 'Female',
        title: 'Account Coordinator'
      },
      {
        id: 3,
        name: 'Lonnie Izkovitz',
        email: 'lizkovitz3@youtu.be',
        gender: 'Female',
        title: 'Operator'
      },
      {
        id: 4,
        name: 'Thatcher Stave',
        email: 'tstave4@reference.com',
        gender: 'Male',
        title: 'Software Test Engineer III'
      },
      {
        id: 5,
        name: 'Clarinda Marieton',
        email: 'cmarietonh@theatlantic.com',
        gender: 'Female',
        title: 'Paralegal'
      }
    ]
  })
}
</script>

<style scoped>
  .signup-page {
    align-items: center;
    display: flex;
    height: 100vh;
    background: url(https://images.unsplash.com/photo-1507842217343-583bb7270b66?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTB8fHxlbnwwfHx8fA%3D%3D&w=1000&q=80);
  }

</style>

