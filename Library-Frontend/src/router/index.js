import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import reservation from "@/components/reservation";
//import returns from '@/components/returns'

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
      path: "/returns",
      name: "Returns",
      component: require("@/components/returns.vue").default
    },
    {
      path: "/holdings",
      name: "Holdings",
      component: require("@/components/holdings.vue").default
    },
    {
      path: "/viewItems",
      name: "ViewItems",
      component: require("@/components/viewItems.vue").default
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
    },
    {
      path: "/UpdateAccount",
      name: "UpdateAccount",
      component: require("@/components/UpdateAccount.vue").default
    },
    {
      path: "/homepage",
      name: "HomePage",
      component: require("@/components/HomePage.vue").default
    },
    {
      path: "/Profile",
      name: "Profile",
      component: require("@/components/Profile.vue").default
    }
  ]
});
