<template>
  <div id="shifts">
    <div id="header">
      <h3 style="color:White;">My Schedule</h3>
    </div>
    <table id="librarian_table" v-if="!errorPerson">
      <p v-if="!shifts_to_show && !errorPerson">
        No schedules to display.
      </p>
      <tr v-if="shifts_to_show.length != 0">
        <th id="entry">Day</th>
        <th id="entry">Start</th>
        <th id="entry">End</th>
      </tr>
      <tr v-for="shift in shifts_to_show" :key="shift.id">
        <td id="data">{{ shift.dayOfWeek }}</td>
        <td id="data">{{ shift.startTime }}</td>
        <td id="data">{{ shift.endTime }}</td>
      </tr>
    </table>

    <nav
      class="navbar fixed-bottom navbar-light"
      style="background-color: #76323F;"
    >
      <button class="left">
        <img
          src="https://cdn-icons-png.flaticon.com/512/25/25694.png"
          alt="Logo"
          height="30"
          width="30"
        />
      </button>
    </nav>
    <p>
      <span v-if="errorPerson" id="error-box" style="color:red">
        {{ errorPerson }}
      </span>
    </p>
    <hr />
  </div>
</template>
<script src="./shifts.js"></script>
<style>
#error-box {
  padding-left: 40px;
}
#header {
  background-color: #76323f;
  height: 150%;
  text-align: center;
}
#shifts {
  font-family: monospace;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
  background: #f2ece8;
}
#librarian_table {
  margin-top: 20px;
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
#text-my-own-color {
  font-size: 18px;
  font-family: monospace;
  color: black;
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
