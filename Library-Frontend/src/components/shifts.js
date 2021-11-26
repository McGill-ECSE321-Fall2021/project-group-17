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
  name: "shifts",
  data() {
    return {
      librarian: null,
      librarianId: 0,
      shifts: [],
      errorPerson: "",
      response: []
    };
  },
  created: function() {
    // Get librarians from backend
    AXIOS.get("/librarian/".concat(7))
      .then(response => {
        // response.data.forEach((shift, j) => this.shifts.push(shift));
        this.librarian = response.data;
      })
      .catch(e => {
        this.errorPerson = e;
      });
    // Getting librarian shifts
    AXIOS.get("/shift/librarian/".concat(7))
      .then(response => {
        // JSON responses are automatically parsed.
        for (let i = 0; i < response.data.length; i++) {
          response.data[i].dayOfWeek =
            response.data[i].dayOfWeek[0] +
            response.data[i].dayOfWeek.slice(1).toLowerCase();
          this.shifts.push(response.data[i]);
        }
      })
      .catch(e => {
        this.errorPerson = e;
      });
  },
  methods: {}
};
