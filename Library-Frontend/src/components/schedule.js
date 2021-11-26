import axios from "axios";
import { update } from "lodash";
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

var backendUrl = backendConfigurer();

var AXIOS = axios.create({
  baseURL: backendUrl
});

export default {
  name: "schedule",
  data() {
    return {
      renderCount: 0,
      currentUser: "",
      librarians: [],
      errorPerson: "",
      response: []
    };
  },
  update() {
    this.renderCount++;
  },
  created: function() {
    AXIOS.get("/librarians")
      .then(response => {
        this.librarians = [];
        for (let i = 0; i < response.data.length; i++) {
          this.librarians.push(response.data[i]);
        }
        // Getting librarian shifts
        for (let i = 0; i < this.librarians.length; i++) {
          AXIOS.get("/shift/librarian/".concat(this.librarians[i].id))
            .then(response => {
              this.librarians[i]["shifts"] = [];
              for (let j = 0; j < response.data.length; j++) {
                response.data[j].dayOfWeek =
                  response.data[j].dayOfWeek[0] +
                  response.data[j].dayOfWeek.slice(1).toLowerCase();
                this.librarians[i]["shifts"].push(response.data[j]);
              }
            })
            .catch(e => {
              this.errorPerson = e;
            });
        }
        console.log(this.librarians);
      })
      .catch(e => {
        this.errorPerson = e;
      });
    this.update();
  },

  methods: {
    update() {
      this.renderCount++;
    },
    deleteShift: function(shiftId) {
      AXIOS.delete(
        "/shift/".concat(shiftId),
        {},
        {
          params: {
            username: "bob344"
          }
        }
      ).catch(e => {
        var errorMsg = e.response.data.message;
        console.log(errorMsg);
        this.errorPerson = errorMsg;
      });
      this.update();
    },
    updateShift: function(shiftId, librarianId, startTime, endTime, dayOfWeek) {
      const json = JSON.stringify({
        startTime: startTime,
        endTime: endTime,
        dayOfWeek: dayOfWeek.toUpperCase(),
        librarianId: librarianId
      });
      if (
        dayOfWeek.toLowerCase() != "monday" ||
        "tuesday" ||
        "wednesday" ||
        "thursday" ||
        "friday"
      ) {
        this.errorPerson = "Day string not formatted correctly!";
        return;
      }
      AXIOS.put("/shift/librarian/".concat(shiftId), json, {
        params: {
          username: "bob344"
        }
      }).catch(e => {
        var errorMsg = e.response.data.message;
        console.log(errorMsg);
        this.errorPerson = errorMsg;
      });
      this.update();
    }
  }
};
