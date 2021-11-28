import Vue from 'vue'
import Router from 'vue-router'
import reservation from '@/components/reservation.vue'
import itemInstance from '@/components/iteminstance.vue'
import login from '@/components/login.vue'
import signup from '@/components/signup.vue'

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
      path: "Profile",
      name: "Profile",
      component: require("@/components/Profile.vue").default
    }

  ]
});
