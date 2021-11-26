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
      <p v-if="(!librarians || librarians.length == 0) && !errorPerson">
        No schedules to display.
      </p>
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
            <th id="entry">Delete!</th>
          </tr>
          <tr v-for="shift in librarian.shifts" :key="shift.id">
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

    <span v-if="errorPerson" id="error-box" style="color:red">
      {{ errorPerson }}
    </span>
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
