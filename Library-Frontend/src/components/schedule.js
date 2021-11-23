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
  //   headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
  name: "schedule",
  data() {
    return {
      librarians: [],
      librarianIds: [],
      events: [],
      newPerson: "",
      librarian: {
        shifts: []
      },
      newEvent: {
        name: "",
        eventDate: "2017-12-08",
        startTime: "09:00",
        endTime: "11:00"
      },
      selectedPerson: "",
      selectedEvent: "",
      errorPerson: "",
      errorEvent: "",
      errorRegistration: "",
      response: []
    };
  },
  created: function() {
    // Get librarians from backend
    AXIOS.get("/librarians")
      .then(response => {
        // JSON responses are automatically parsed.
        this.librarians = response.data;
        this.librarians.forEach((x, i) => this.librarianIds.push(x.id));
      })
      .catch(e => {
        this.errorPerson = e;
      });
    // Getting librarian shifts
    this.librarianIds.forEach((x, i) =>
      AXIOS.get("/shift/librarian/".concat(x))
        .then(response => {
          // JSON responses are automatically parsed.
          response.data.forEach(
            (shift, j) => (this.librarians[i].shift = shift)
          );
        })
        .catch(e => {
          this.errorPerson = e;
        })
    );
  },
  methods: {}
};
