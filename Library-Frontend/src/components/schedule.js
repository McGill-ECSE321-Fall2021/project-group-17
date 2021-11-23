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

var backendUrl = backendConfigurer();

var AXIOS = axios.create({
  baseURL: backendUrl
});

export default {
  name: "schedule",
  data() {
    return {
      librarians: [],
      errorPerson: "",
      response: []
    };
  },
  created: function() {
    AXIOS.get("/librarians")
      .then(response => {
        for (let i = 0; i < response.data.length; i++) {
          this.librarians.push(response.data[i]);
        }
        // Getting librarian shifts
        for (let i = 0; i < this.librarians.length; i++) {
          AXIOS.get("/shift/librarian/".concat(this.librarians[i].id))
            .then(response => {
              this.librarians[i]["shifts"] = [];
              for (let j = 0; j < response.data.length; j++) {
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
  },
  methods: {
    // updateShift: function(librarianId, dayOfWeek, startTime, endTime) {
    //   AXIOS.post(
    //     "/events/".concat(eventName),
    //     {},
    //     {
    //       params: {
    //         date: dayOfWeek,
    //         startTime: startTime,
    //         endTime: endTime
    //       }
    //     }
    //   )
    //     .then(response => {
    //       // JSON responses are automatically parsed.
    //       this.events.push(response.data);
    //       this.errorEvent = "";
    //       this.newEvent.name = "";
    //     })
    //     .catch(e => {
    //       var errorMsg = e.response.data.message;
    //       console.log(errorMsg);
    //       this.errorEvent = errorMsg;
    //     });
    // }
  }
};
