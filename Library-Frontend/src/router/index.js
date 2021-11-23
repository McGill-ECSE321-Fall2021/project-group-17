import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import reservation from "@/components/reservation";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "Hello",
      component: Hello
    },
    {
      path: "/app",
      name: "EventRegistration",
      component: reservation
    },
    {
      path: "/shifts",
      name: "Shifts",
      component: require("@/components/shifts.vue").default
    },
    {
      path: "/schedule",
      name: "Schedule",
      component: require("@/components/schedule.vue").default
    }
  ]
});
