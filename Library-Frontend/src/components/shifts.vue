<template>
  <div id="shifts">
    <hr />
    <h2>My Schedule</h2>
    <table>
      <!-- <td>{{ librarian.person.name }}</td> -->
      <tr>
        <th>Day</th>
        <th>Start</th>
        <th>End</th>
        <!--<th>Edit</th>-->
      </tr>
      <tr v-for="shift in shifts" :key="shift.id">
        <td>{{ shift.dayOfWeek }}</td>
        <td>{{ shift.startTime }}</td>
        <td>{{ shift.endTime }}</td>
      </tr>
    </table>

    <p>
      <span v-if="errorPerson" style="color:red"
        >Error: {{ errorPerson }}
      </span>
    </p>
    <hr />
  </div>
</template>
<script src="./schedule.js"></script>
<style>
#shifts {
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
