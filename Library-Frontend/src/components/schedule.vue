<template>
  <div id="schedule">
    <hr />
    <h1>All Schedules</h1>
    <hr />
    <tr v-for="librarian in librarians" :key="librarian.id">
      <h2>{{ librarian.person.name }}</h2>
      <table>
        <tr>
          <th>Day</th>
          <th>Start</th>
          <th>End</th>
          <th>Update</th>
          <th>Delete</th>
        </tr>
        <tr v-for="shift in librarian.shifts" :key="shift.id">
          <td>
            <input
              type="text"
              v-model="shift.dayOfWeek"
              placeholder="Event Name"
            />
          </td>
          <td>
            <input type="time" v-model="shift.startTime" placeholder="HH:mm" />
          </td>
          <td>
            <input type="time" v-model="shift.endTime" placeholder="HH:mm" />
          </td>
          <td>
            <button v-on:click="updateShift(shift.id)">Update</button>
          </td>
          <td>
            <button v-on:click="updateShift(shift.id)">Delete</button>
          </td>
        </tr>
        <hr />
      </table>
    </tr>

    <!-- <span v-if="errorEvent" style="color:red">Error: {{ errorEvent }} </span> -->
    <hr />
  </div>
</template>
<script src="./schedule.js"></script>
<style>
#schedule {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background: #f2ece8;
  margin-top: 60px;
}
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: disc;
  text-align: left;
  padding: 1;
}
li {
  margin: 1 10px;
}
a {
  color: #42b983;
}
</style>
