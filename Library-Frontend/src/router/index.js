import itemInstance from '@/components/iteminstance.vue'
import login from '@/components/login.vue'
import signup from '@/components/signup.vue'
import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import reservation from "@/components/reservation";
import LibrarianWelcome from "../components/LibrarianWelcome";
import active from '@/components/active-items.vue'
//import returns from '@/components/returns'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/iteminstance',
      name: 'Item Instance',
      component: itemInstance
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/signup',
      name: 'signup',
      component: signup
    },
    {
      path:'/LibrarianWelcome',
      name:'LibrarianWelcome',
      component: require("@/components/LibrarianWelcome.vue").default
    },
    {
      path:'/HeadLibrarianWelcome',
      name:'HeadLibrarianWelcome',
      component: require("@/components/HeadLibrarianWelcome.vue").default
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
      component: require("@/components/homepage.vue").default
    },
    {
      path: "/Profile",
      name: "Profile",
      component: require("@/components/Profile.vue").default
    },
    {
      path: "/active",
      name: "active",
      component: active
    }

  ]
});
