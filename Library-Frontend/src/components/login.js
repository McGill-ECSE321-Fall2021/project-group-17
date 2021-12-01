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

function PersonDto(name) {}

export default {
  name: "login",
  data() {
    return {
      userLogin: null,
      passwordLogin: null,
      errorLogin: false,
      error: false
    };
  },
  created: function() {
    if (this.$cookie.get("customerId")) {
      console.log("user already logged in");
      this.$router.push({ name: "HomePage" });
    }
  },
  methods: {
    logIn: function() {
      console.log(this.userLogin);
      AXIOS.put("/login/" + this.userLogin + "/" + this.passwordLogin, {})
        .then(response => {
          this.errorLogin = "";
          this.$cookie.set("username", response.data.username);
          this.$cookie.set("customerId", response.data.personRole.id);

          AXIOS.get("/checkPersonRole/" + this.userLogin)
            .then(response => {
              if (response.data == 0) {
                this.$cookie.set("usertype", 0);
                this.$router.push({ name: "HomePage" });
              } else if (response.data == 1) {
                this.$cookie.set("usertype", 1);
                this.$router.push({ name: "LibrarianWelcome" });
              } else if (response.data == 2) {
                this.$cookie.set("usertype", 2);
                this.$router.push({ name: "HeadLibrarianWelcome" });
              }
            })
            .catch(e => {
              let errorMsg = e.response.data.message;
              console.log(errorMsg);
              this.errorLogin = errorMsg;
              this.error = true;
            });
        })
        .catch(e => {
          let errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorLogin = errorMsg;
          this.error = true;
        });
    }
  }
};
