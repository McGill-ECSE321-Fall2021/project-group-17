<template>
  <div id="schedule">
    <hr />
    <h1>Modify Schedule</h1>
    <hr />
    <div id="librarian_table">
      <tr v-for="librarian in librarians" :key="librarian.id">
        <h2>{{ librarian.person.name }}, id: {{ librarian.id }}</h2>
        <p v-if="!librarian.shifts || librarian.shifts.length == 0">
          No shifts to display.
        </p>
        <table id="row">
          <tr v-if="librarian.shifts && librarian.shifts.length != 0">
            <th id="entry">Day</th>
            <th id="entry">Start</th>
            <th id="entry">End</th>
            <th id="entry">Update</th>
            <th id="entry">Delete</th>
          </tr>
          <tr v-for="shift in librarian.shifts" :key="shift.id">
            <td id="data"><input type="text" v-model="shift.dayOfWeek" /></td>
            <td id="data"><input type="time" v-model="shift.startTime" /></td>
            <td id="data"><input type="time" v-model="shift.endTime" /></td>
            <td id="data">
              <button
                v-on:click="
                  updateShift(
                    shift.id,
                    librarian.id,
                    shift.startTime,
                    shift.endTime,
                    shift.dayOfWeek
                  )
                "
              >
                Update
              </button>
            </td>
            <td id="data">
              <button v-on:click="deleteShift(shift.id)">Delete</button>
            </td>
          </tr>

          <hr />
        </table>
      </tr>
    </div>

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
