import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import reservation from "@/components/reservation";
import shifts from "@/components/shifts";
import schedule from "@/components/schedule";

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
      name: "EventRegistration",
      component: shifts
    },
    {
      path: "/schedule",
      name: "EventRegistration",
      component: schedule
    }
  ]
});
