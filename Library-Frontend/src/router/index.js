import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import reservation from "@/components/reservation";
import shifts from "@/components/shifts";

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
      name: "LibrarianShifts",
      component: shifts
    }
  ]
});
