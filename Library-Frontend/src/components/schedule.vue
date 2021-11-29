<template>
  <div id="schedule">
    <b-navbar toggleable="md" type="light" variant="light">
      <b-navbar variant="faded" type="light" left>
        <b-navbar-brand>
          <img
            src="https://i.ibb.co/L5shFK6/LMS-removebg-preview.png"
            alt="Logo"
            height="50"
            width="60"
          />
        </b-navbar-brand>
      </b-navbar>
      <b-nav-item href="#">
        <a class="text-my-own-color ">
          Find movies, books, and movies
        </a>
      </b-nav-item>
      <b-nav-item href="#" class="unstyled">
        <a class="text-my-own-color ">
          View Active Loans
        </a>
      </b-nav-item>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <b-nav-form>
          <b-input-group prepend="What are you looking for today?">
            <b-form-input size="md" class="mr-sm-2"></b-form-input>
          </b-input-group>
        </b-nav-form>

        <b-nav-item-dropdown right>
          <!-- Using 'button-content' slot -->
          <template #button-content>
            <img
              src="https://icons-for-free.com/iconfiles/png/512/person+user+icon-1320166085409390336.png"
              alt="Logo"
              height="30"
              width="30"
            />
          </template>
          <b-dropdown-item @click="profile()" href="#">Profile</b-dropdown-item>
          <b-dropdown-item href="#">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-navbar>

    <div id="header">
      <h3>Modify Schedules</h3>
    </div>
    <div id="librarian_table">
      <table id="row">
        <h2>Create Shifts</h2>
        <tr>
          <th id="entry">Librarian Id</th>
          <th id="entry">Day</th>
          <th id="entry">Start</th>
          <th id="entry">End</th>
          <th id="entry">Head Librarian?</th>
        </tr>
        <tr>
          <td id="data">
            <input v-model="librarian_Id" type="text"/>
          </td>
          <td id="data">
            <input v-model="dayOf_Week" type="text"/>
          </td>
          <td id="data">
            <input v-model="start_time" type="time"/>
          </td>
          <td id="data">
            <input v-model="end_time" type="time"/>
          </td>
          <td id="data1">
            <select v-model="bool">
              <option value="Yes">Yes</option>
              <option value="No">No</option>
            </select>
          </td>
          <td>
            <button
              @click="
                createShift(
                  librarian_Id,
                  start_time,
                  end_time,
                  dayOf_Week,
                  bool
                )
              "
            >
              Create
            </button>
          </td>
        </tr>
        <hr />
      </table>
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
            <th id="entry">{{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <input type="text" v-model="shift.dayOfWeek" />
          </td>
          <td id="data">
            <input type="time" v-model="shift.startTime" />
          </td>
          <td id="data"><input type="time" v-model="shift.endTime" /></td>
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
        <tr v-if="Monday && Monday.length != 0">
          <th id="entry">Librarian Name</th>
          <th id="entry">Librarian Id</th>
          <th id="entry">Day</th>
          <th id="entry">Start</th>
          <th id="entry">End</th>
          <th id="entry">Update</th>
          <th id="entry">Delete</th>
        </tr>
        <tr v-for="shift in Monday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">{{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <input type="text" v-model="shift.dayOfWeek" />
          </td>
          <td id="data">
            <input type="time" v-model="shift.startTime" />
          </td>
          <td id="data"><input type="time" v-model="shift.endTime" /></td>
          <td id="data">
            <button
              v-on:click="
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
            </button>
          </td>
          <td id="data">
            <button v-on:click="deleteShift(shift.id)">Delete</button>
          </td>
        </tr>
        <hr />
      </table>
      <table id="row" v-if="Tuesday && Tuesday.length != 0">
        <h2 v-if="Tuesday && Tuesday.length != 0">Tuesday</h2>
        <tr v-if="Tuesday && Tuesday.length != 0">
          <th id="entry">Librarian Name</th>
          <th id="entry">Librarian Id</th>
          <th id="entry">Day</th>
          <th id="entry">Start</th>
          <th id="entry">End</th>
          <th id="entry">Update</th>
          <th id="entry">Delete</th>
        </tr>
        <tr v-for="shift in Tuesday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">{{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <input type="text" v-model="shift.dayOfWeek" />
          </td>
          <td id="data">
            <input type="time" v-model="shift.startTime" />
          </td>
          <td id="data"><input type="time" v-model="shift.endTime" /></td>
          <td id="data">
            <button
              v-on:click="
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
            </button>
          </td>
          <td id="data">
            <button v-on:click="deleteShift(shift.id)">Delete</button>
          </td>
        </tr>
        <hr />
      </table>
      <table id="row" v-if="Wednesday && Wednesday.length != 0">
        <h2 v-if="Wednesday && Wednesday.length != 0">Wednesday</h2>
        <tr v-if="Wednesday && Wednesday.length != 0">
          <th id="entry">Librarian Name</th>
          <th id="entry">Librarian Id</th>
          <th id="entry">Day</th>
          <th id="entry">Start</th>
          <th id="entry">End</th>
          <th id="entry">Update</th>
          <th id="entry">Delete</th>
        </tr>
        <tr v-for="shift in Wednesday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">{{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <input type="text" v-model="shift.dayOfWeek" />
          </td>
          <td id="data">
            <input type="time" v-model="shift.startTime" />
          </td>
          <td id="data"><input type="time" v-model="shift.endTime" /></td>
          <td id="data">
            <button
              v-on:click="
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
            </button>
          </td>
          <td id="data">
            <button v-on:click="deleteShift(shift.id)">Delete</button>
          </td>
        </tr>
        <hr />
      </table>

      <table id="row" v-if="Thursday && Thursday.length != 0">
        <h2 v-if="Thursday && Thursday.length != 0">Thursday</h2>
        <tr v-if="Thursday && Thursday.length != 0">
          <th id="entry">Librarian Name</th>
          <th id="entry">Librarian Id</th>
          <th id="entry">Day</th>
          <th id="entry">Start</th>
          <th id="entry">End</th>
          <th id="entry">Update</th>
          <th id="entry">Delete</th>
        </tr>
        <tr v-for="shift in Thursday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">{{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <input type="text" v-model="shift.dayOfWeek" />
          </td>
          <td id="data">
            <input type="time" v-model="shift.startTime" />
          </td>
          <td id="data"><input type="time" v-model="shift.endTime" /></td>
          <td id="data">
            <button
              v-on:click="
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
            </button>
          </td>
          <td id="data">
            <button v-on:click="deleteShift(shift.id)">Delete</button>
          </td>
        </tr>
        <hr />
      </table>

      <table id="row" v-if="Friday && Friday.length != 0">
        <h2 v-if="Friday && Friday.length != 0">Friday</h2>
        <tr v-if="Friday && Friday.length != 0">
          <th id="entry">Librarian Name</th>
          <th id="entry">Librarian Id</th>
          <th id="entry">Day</th>
          <th id="entry">Start</th>
          <th id="entry">End</th>
          <th id="entry">Update</th>
          <th id="entry">Delete</th>
        </tr>
        <tr v-for="shift in Friday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">{{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <input type="text" v-model="shift.dayOfWeek" />
          </td>
          <td id="data">
            <input type="time" v-model="shift.startTime" />
          </td>
          <td id="data"><input type="time" v-model="shift.endTime" /></td>
          <td id="data">
            <button
              v-on:click="
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
            </button>
          </td>
          <td id="data">
            <button v-on:click="deleteShift(shift.id)">Delete</button>
          </td>
        </tr>
        <hr />
      </table>

      <table id="row" v-if="Saturday && Saturday.length != 0">
        <h2 v-if="Saturday && Saturday.length != 0">Saturday</h2>
        <tr v-if="Saturday && Saturday.length != 0">
          <th id="entry">Librarian Name</th>
          <th id="entry">Librarian Id</th>
          <th id="entry">Day</th>
          <th id="entry">Start</th>
          <th id="entry">End</th>
          <th id="entry">Update</th>
          <th id="entry">Delete</th>
        </tr>
        <tr v-for="shift in Saturday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">{{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <input type="text" v-model="shift.dayOfWeek" />
          </td>
          <td id="data">
            <input type="time" v-model="shift.startTime" />
          </td>
          <td id="data"><input type="time" v-model="shift.endTime" /></td>
          <td id="data">
            <button
              v-on:click="
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
            </button>
          </td>
          <td id="data">
            <button v-on:click="deleteShift(shift.id)">Delete</button>
          </td>
        </tr>
        <hr />
      </table>

      <table id="row" v-if="Sunday && Sunday.length != 0">
        <h2 v-if="Sunday && Sunday.length != 0">Sunday</h2>
        <tr v-if="Sunday && Sunday.length != 0">
          <th id="entry">Librarian Name</th>
          <th id="entry">Librarian Id</th>
          <th id="entry">Day</th>
          <th id="entry">Start</th>
          <th id="entry">End</th>
          <th id="entry">Update</th>
          <th id="entry">Delete</th>
        </tr>
        <tr v-for="shift in Sunday" :key="shift.id">
          <td id="data">
            <th id="entry">{{shift.librarian.person.name}}</th>
          </td>
          <td id="data">
            <th id="entry">{{shift.librarian.id}}</th>
          </td>
          <td id="data">
            <input type="text" v-model="shift.dayOfWeek" />
          </td>
          <td id="data">
            <input type="time" v-model="shift.startTime" />
          </td>
          <td id="data"><input type="time" v-model="shift.endTime" /></td>
          <td id="data">
            <button
              v-on:click="
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
            </button>
          </td>
          <td id="data">
            <button v-on:click="deleteShift(shift.id)">Delete</button>
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
      <span v-if="errorPerson" id="error-box" style="color:red">
        {{ errorPerson }}
      </span>
    </p>
    <hr />
  </div>
</template>
<script src="./schedule.js"></script>
<style>
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
  margin-top: 60px;
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
