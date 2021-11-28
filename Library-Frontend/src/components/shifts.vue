<template>
  <div id="shifts">
    <hr />
    <h1>My Schedule</h1>
    <hr />
    <table id="librarian_table">
      <!-- <td>{{ librarian.person.name }}</td> -->
      <tr>
        <th id="entry">Day</th>
        <th id="entry">Start</th>
        <th id="entry">End</th>
        <!--<th>Edit</th>-->
      </tr>
      <tr v-for="shift in shifts" :key="shift.id">
        <td id="data">{{ shift.dayOfWeek }}</td>
        <td id="data">{{ shift.startTime }}</td>
        <td id="data">{{ shift.endTime }}</td>
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
<script src="./shifts.js"></script>
<style>
#shifts {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
  background: #f2ece8;
  margin-top: 60px;
}
#librarian_table {
  margin-left: 40px;
}
#row {
  margin-bottom: 20px;
}
#entry {
  padding-left: 8px;
  padding-right: 8px;
}
#data {
  padding: 8px;
}
h1 {
  font-weight: normal;
  margin-left: 40px;
}
h2 {
  font-weight: normal;
  padding-left: 6px;
}
p {
  font-weight: normal;
  padding-left: 8px;
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
