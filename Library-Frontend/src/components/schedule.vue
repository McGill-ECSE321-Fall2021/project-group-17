<template>
  <div id="schedule">
    <div id="header">
      <h3 style="color:White;padding-top:17.5px;padding-bottom:17.5px">Modify Schedules</h3>
    </div>
    <div>
            <md-table md-card class="table1">
        <h2>Create:</h2>
          <md-table-row>
            <md-table-cell class="th">Librarian ID</md-table-cell>
            <md-table-cell class="th">Day of Week</md-table-cell>
            <md-table-cell class="th">Start Time</md-table-cell>
            <md-table-cell class="th">End Time</md-table-cell>
          </md-table-row>
        <md-table-row class="tr" md-selectable="single" >
          <md-table-cell class="inputLine">
              <md-field>
                  <label>Librarian Id</label>
                  <md-input v-model="librarian_Id" placeholder="1234"></md-input>
              </md-field>
          </md-table-cell>
          <md-table-cell class="inputLine">
              <md-field class="inputLine">
                  <label>Day of Week</label>
                  <md-input v-model="dayOf_Week" placeholder="Monday"></md-input>
              </md-field>
          </md-table-cell>
          <md-table-cell class="inputLine">
              <md-field class="inputLine">
                  <label>Start time</label>
                  <md-input v-model="start_time" placeholder="00:00"></md-input>
              </md-field>
          </md-table-cell>
          <md-table-cell class="inputLine">
              <md-field class="inputLine">
                  <label>End time</label>
                  <md-input v-model="end_time" placeholder="00:00"></md-input>
              </md-field>
          </md-table-cell>
          <md-table-cell>
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="
                createShift(
                  librarian_Id,
                  start_time,
                  end_time,
                  dayOf_Week,
                  bool
                )
              ">Create</b-button>
          </md-table-cell>
        </md-table-row>
        <hr />
      </md-table>
      <hr />
      <p v-if="(!librarians || librarians.length == 0) && !errorPerson">
        No schedules to display.
      </p>
      <table id="row" v-if="headLibrarianShifts && headLibrarianShifts.length != 0">
        <h2 v-if="headLibrarianShifts && headLibrarianShifts.length != 0">Your Shifts</h2>
        <tr v-if="headLibrarianShifts && headLibrarianShifts.length != 0">
          <th id="entry">Librarian Name</th>
          <th id="entry">Librarian Id</th>
          <th id="entry">Day</th>
          <th id="entry">Start</th>
          <th id="entry">End</th>
          <th id="entry">Update</th>
          <th id="entry">Delete</th>
        </tr>
        <tr v-for="shift in headLibrarianShifts" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">ID: {{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Day Of Week</label>
                        <md-input v-model="shift.dayOfWeek" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Start time</label>
                        <md-input v-model="shift.startTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>End time</label>
                        <md-input v-model="shift.endTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <button
              v-on:click="
                updateShiftHeadLibrarian(
                  shift.id,
                  shift.librarian.id,
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
      <table id="row" v-if="Monday && Monday.length != 0">
        <h2 v-if="Monday && Monday.length != 0">Monday</h2>
        <tr v-for="shift in Monday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">ID: {{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Day of Week</label>
                        <md-input v-model="shift.dayOfWeek" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Start Time</label>
                        <md-input v-model="shift.startTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>End Time</label>
                        <md-input v-model="shift.endTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="
                updateShift(
                  shift.id,
                  shift.librarian.id,
                  shift.startTime,
                  shift.endTime,
                  shift.dayOfWeek
                )
              "
            >
              Update
            </b-button>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="deleteShift(shift.id)">Delete</b-button>
          </td>
        </tr>
        <hr />
      </table>
      <table id="row" v-if="Tuesday && Tuesday.length != 0">
        <h2 v-if="Tuesday && Tuesday.length != 0">Tuesday</h2>
        <tr v-for="shift in Tuesday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">ID: {{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Day of Week</label>
                        <md-input v-model="shift.dayOfWeek" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Start time</label>
                        <md-input v-model="shift.startTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>End time</label>
                        <md-input v-model="shift.endTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="
                updateShift(
                  shift.id,
                  shift.librarian.id,
                  shift.startTime,
                  shift.endTime,
                  shift.dayOfWeek
                )
              "
            >
              Update
            </b-button>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="deleteShift(shift.id)">Delete</b-button>
          </td>
        </tr>
        <hr />
      </table>
      <table id="row" v-if="Wednesday && Wednesday.length != 0">
        <h2 v-if="Wednesday && Wednesday.length != 0">Wednesday</h2>
        <tr v-for="shift in Wednesday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">ID: {{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Day of Week</label>
                        <md-input v-model="shift.dayOfWeek" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Start Time</label>
                        <md-input v-model="shift.startTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>End Time</label>
                        <md-input v-model="shift.endTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="
                updateShift(
                  shift.id,
                  shift.librarian.id,
                  shift.startTime,
                  shift.endTime,
                  shift.dayOfWeek
                )
              "
            >
              Update
            </b-button>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="deleteShift(shift.id)">Delete</b-button>
          </td>
        </tr>
        <hr />
      </table>

      <table id="row" v-if="Thursday && Thursday.length != 0">
        <h2 v-if="Thursday && Thursday.length != 0">Thursday</h2>
        <tr v-for="shift in Thursday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">ID: {{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Day of Week</label>
                        <md-input v-model="shift.dayOfWeek" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Start Time</label>
                        <md-input v-model="shift.startTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>End Time</label>
                        <md-input v-model="shift.endTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="
                updateShift(
                  shift.id,
                  shift.librarian.id,
                  shift.startTime,
                  shift.endTime,
                  shift.dayOfWeek
                )
              "
            >
              Update
            </b-button>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="deleteShift(shift.id)">Delete</b-button>
          </td>
        </tr>
        <hr />
      </table>

      <table id="row" v-if="Friday && Friday.length != 0">
        <h2 v-if="Friday && Friday.length != 0">Friday</h2>
        <tr v-for="shift in Friday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">ID: {{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Day of Week</label>
                        <md-input v-model="shift.dayOfWeek" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Start Time</label>
                        <md-input v-model="shift.startTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>End Time</label>
                        <md-input v-model="shift.endTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="
                updateShift(
                  shift.id,
                  shift.librarian.id,
                  shift.startTime,
                  shift.endTime,
                  shift.dayOfWeek
                )
              "
            >
              Update
            </b-button>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="deleteShift(shift.id)">Delete</b-button>
          </td>
        </tr>
        <hr />
      </table>

      <table id="row" v-if="Saturday && Saturday.length != 0">
        <h2 v-if="Saturday && Saturday.length != 0">Saturday</h2>
        <tr v-for="shift in Saturday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">ID: {{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Day of Week</label>
                        <md-input v-model="shift.dayOfWeek" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Start Time</label>
                        <md-input v-model="shift.startTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>End Time</label>
                        <md-input v-model="shift.endTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="
                updateShift(
                  shift.id,
                  shift.librarian.id,
                  shift.startTime,
                  shift.endTime,
                  shift.dayOfWeek
                )
              "
            >
              Update
            </b-button>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="deleteShift(shift.id)">Delete</b-button>
          </td>
        </tr>
        <hr />
      </table>

      <table id="row" v-if="Sunday && Sunday.length != 0">
        <h2 v-if="Sunday && Sunday.length != 0">Sunday</h2>
        <tr v-for="shift in Sunday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">ID: {{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Day of Week</label>
                        <md-input v-model="shift.dayOfWeek" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>Start Time</label>
                        <md-input v-model="shift.startTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <div class="inputLine">
                    <md-field>
                        <label>End Time</label>
                        <md-input v-model="shift.endTime" placeholder=""></md-input>
                    </md-field>
            </div>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="
                updateShift(
                  shift.id,
                  shift.librarian.id,
                  shift.startTime,
                  shift.endTime,
                  shift.dayOfWeek
                )
              "
            >
              Update
            </b-button>
          </td>
          <td id="data">
            <b-button pill variant="outline-secondary" class="buttons" v-on:click="deleteShift(shift.id)">Delete</b-button>
          </td>
        </tr>
        <hr />
      </table>
    </div>

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
      <span v-if="errorPerson" id="error-box" style="color:black">
        {{ errorPerson }}
      </span>
    </p>
    <hr />
  </div>
</template>
<script src="./schedule.js"></script>
<style>
#md-primary {
    margin-left: auto;
    margin-right: auto;
    width: 75%;
    background-color: #d7cec7;
}
#inputLine {
    display: inline-block;
}
#th {
    background-color: #c09f80;
}
#table1 {
    margin-left: auto;
    margin-right: auto;
    width: 90%;
}
#table {
    margin-left: auto;
    margin-right: auto;
    width: 60%;
}
#error-box {
  padding-left: 48px;
}
#header {
  background-color: #76323f;
  height: 150%;
  text-align: center;
}
#schedule {
  font-family: monospace;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
  background: #f2ece8;
}
#bookDate {
    width: 120%;
}

#bookGenre {
    position: relative;
    left: 60%;
}

#bookPublisher {
    position: relative;
    left: 50%;
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
#data1 {
  padding-left: 8px;
  padding-top: 8px;
  padding-bottom: 8px;
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
  padding-left: 16px;
}
p {
  font-weight: normal;
  padding-left: 8px;
}
a {
  color: #42b983;
}
</style>
