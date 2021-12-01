import axios from "axios";
var config = require("../../config");

var backendConfigurer = function() {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
    case "production":
      return (
        "https://" + config.build.backendHost + ":" + config.build.backendPort
      );
  }
};


var frontendConfigurer = function() {
  switch (process.env.NODE_ENV) {
    case "development":
      return "http://" + config.dev.host
    case "production":
      return (
        "https://" + config.build.host
      );
  }
};

var backendUrl = backendConfigurer();
var frontendUrl = frontendConfigurer();

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "schedule",
  data() {
    return {
      username: this.$cookie.get("username"),
      librarian_Id: "",
      dayOf_Week: "",
      start_time: "",
      end_time: "",
      bool: "",
      currentUser: "",
      librarians: [],
      errorPerson: "",
      response: [],
      Monday: [],
      Tuesday: [],
      Wednesday: [],
      Thursday: [],
      Friday: [],
      Saturday: [],
      Sunday: [],
      headLibrarianShifts: []
    };
  },
  created: function() {
    AXIOS.get("/librarians")
      .then(response => {
        this.Monday = [];
        this.Tuesday = [];
        this.Wednesday = [];
        this.Thursday = [];
        this.Friday = [];
        this.Saturday = [];
        this.Sunday = [];
        this.librarians = [];
        for (let i = 0; i < response.data.length; i++) {
          this.librarians.push(response.data[i]);
        }
        // Getting librarian shifts
        for (let i = 0; i < this.librarians.length; i++) {
          AXIOS.get("/shift/librarian/".concat(this.librarians[i].id))
            .then(response => {
              for (let j = 0; j < response.data.length; j++) {
                response.data[j].dayOfWeek =
                  response.data[j].dayOfWeek[0] +
                  response.data[j].dayOfWeek.slice(1).toLowerCase();
                if (this.librarians[i].id === this.$cookie.get("customerId")) {
                  this.headLibrarianShifts.push(response.data[j]);
                } else if (response.data[j].dayOfWeek === "Monday") {
                  this.Monday.push(response.data[j]);
                } else if (response.data[j].dayOfWeek === "Tuesday") {
                  this.Tuesday.push(response.data[j]);
                } else if (response.data[j].dayOfWeek === "Wednesday") {
                  this.Wednesday.push(response.data[j]);
                } else if (response.data[j].dayOfWeek === "Thursday") {
                  this.Thursday.push(response.data[j]);
                } else if (response.data[j].dayOfWeek === "Friday") {
                  this.Friday.push(response.data[j]);
                } else if (response.data[j].dayOfWeek === "Saturday") {
                  this.Saturday.push(response.data[j]);
                } else if (response.data[j].dayOfWeek === "Sunday") {
                  this.Sunday.push(response.data[j]);
                }
              }
            })
            .catch(e => {
              this.errorPerson = e;
            });
        }
      })
      .catch(e => {
        this.errorPerson = e;
      });
  },

  methods: {
    createShift: function(librarianId, startTime, endTime, dayOfWeek) {
      startTime = startTime.concat(":00");
      endTime = endTime.concat(":00");
      const json = {
        startTime: startTime,
        endTime: endTime,
        dayOfWeek: dayOfWeek.toUpperCase(),
        librarianId: librarianId
      };
      if (
        dayOfWeek.toUpperCase() != "MONDAY" &&
        dayOfWeek.toUpperCase() != "TUESDAY" &&
        dayOfWeek.toUpperCase() != "WEDNESDAY" &&
        dayOfWeek.toUpperCase() != "THURSDAY" &&
        dayOfWeek.toUpperCase() != "FRIDAY" &&
        dayOfWeek.toUpperCase() != "SATURDAY" &&
        dayOfWeek.toUpperCase() != "SUNDAY"
      ) {
        this.errorPerson = "Day string not formatted correctly!";
        return;
      }
      AXIOS.post("/shift/librarian", json, {
        params: { accountusername: this.username }
      }).catch(e => {
        var errorMsg = e.response.data.message;
        console.log(errorMsg);
        this.errorPerson = errorMsg;
      });
      document.location.reload(true);
    },
    deleteShift: function(shiftid) {
      AXIOS.delete("/shift/".concat(shiftid), {
        params: { accountusername: this.username }
      })
        .then(response => {
          console.log(response.data);
        })
        .catch(e => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorPerson = errorMsg;
        });
      document.location.reload(true);
    },
    updateShift: function(shiftid, librarianId, startTime, endTime, dayOfWeek) {
      const json = {
        startTime: startTime,
        endTime: endTime,
        dayOfWeek: dayOfWeek.toUpperCase(),
        librarianId: librarianId
      };
      if (
        dayOfWeek.toUpperCase() != "MONDAY" &&
        dayOfWeek.toUpperCase() != "TUESDAY" &&
        dayOfWeek.toUpperCase() != "WEDNESDAY" &&
        dayOfWeek.toUpperCase() != "THURSDAY" &&
        dayOfWeek.toUpperCase() != "FRIDAY" &&
        dayOfWeek.toUpperCase() != "SATURDAY" &&
        dayOfWeek.toUpperCase() != "SUNDAY"
      ) {
        this.errorPerson = "Day string not formatted correctly!";
        return;
      }
      AXIOS.put("/shift/librarian/".concat(shiftid), json, {
        params: { accountUsername: this.username }
      })
        .then(response => {
          console.log(response);
        })
        .catch(e => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorPerson = errorMsg;
        });
      document.location.reload(true);
    }
  }
};
