import Vue from 'vue'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default-dark.css'

<template>
  <div id="webpage">
    <div id="LibrarianWelcome">
      <md-toolbar class="md-medium">
        <div class="md-toolbar-section-start">
          <md-button class="md-icon-button md-raised">
            <image src="../../../images/LMS.jpg" id="logo"></image>
          </md-button>
        </div>
        <div class="md-toolbar-section-end">
          <md-button class="md-raised" id="Refresh">Refresh</md-button>
          <md-button class="md-raised" id="Create">Create</md-button>
        </div>
      </md-toolbar>
    </div>
    <v-row align="center" justify="center">
      <div id="welcomeMSG">
        <h1 id="welcome">
          Welcome
          <h2 id="HeadLibrarian"></h2>
        </h1>
      </div>
    </v-row>
    <div class="md-layout" :class="`md-alignment-bottom-left`" id="Schedule">
      <md-card md-with-hover class="md-rounded" id="ScheduleCard">
        <md-ripple>
          <md-card-header>
            <div class="md-title md-toolbar-section-start">Schedule
              <div class="md-toolbar-section-end">
                <md-card-actions>
                  <md-button>Update Schedule</md-button>
                </md-card-actions>
              </div>
            </div>
            <div class="md-subhead md-toolbar-section-start">Your Upcoming Shifts</div>
            <md-divider class="md-inset"></md-divider>
          </md-card-header>
          <md-card-content>
            <md-list class="md-dense">
              <md-list-item>
                <span class="title">Monday: </span>
                <span class="md-toolbar-section-start">past shift here</span>
              </md-list-item>

              <md-list-item>
                <span class="title">Tuesday: </span>
                <span class="md-toolbar-section-start">past shift here</span>
              </md-list-item>

              <md-list-item>
                <span class="title">Wednesday: </span>
                <span class="md-toolbar-section-start">past shift here</span>
              </md-list-item>

              <md-list-item>
                <span class="title">Thursday: </span>
                <span class="md-toolbar-section-start">past shift here</span>
              </md-list-item>

              <md-list-item>
                <span class="title">Friday: </span>
                <span class="md-toolbar-section-start">past shift here</span>
              </md-list-item>
            </md-list>
          </md-card-content>
        </md-ripple>
      </md-card>
    </div>
  </div>
</template>

<script src="./LibrarianWelcome.js">
/*export default {
  name: "librarianWelcome"
}*/
</script>

<style scoped>
#LibrarianWelcome {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
  background: #f2ece8;
}
#Schedule {
  width: 320px;
  margin: 4px;
}
#ScheduleCard {
  background: #c09f80;
  border-radius: 8px;
}
#welcomeMSG {
  font-family: Apple Chancery, cursive;
  position: relative;
  right: 325px;
  top: 30px;
}
#welcome {
  position: relative;
  right: 300px;
  top: 30px;
  font-size: xxx-large;
  margin-bottom: 120px;
  margin-left: 40px;
}
#HeadLibrarian {
  position: relative;
  font-size: xx-large;
  left: 50px;
  margin-top: 10px;
}
#webpage {
  background: #d7cec7;
  vertical-align: top;
}
#Refresh {
  background: #76323f;
  border-radius: 2px;
  opacity: 0.95;
  color: #ffffff;
}
#Create {
  background: #76323f;
  border-radius: 2px;
  opacity: 0.95;
  color: #ffffff;
}
#logo {

}
</style>
