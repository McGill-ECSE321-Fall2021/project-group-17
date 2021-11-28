import Vue from 'vue'
import Router from 'vue-router'
import reservation from '@/components/reservation.vue'
import itemInstance from '@/components/iteminstance.vue'
import login from '@/components/login.vue'
import signup from '@/components/signup.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/reservation',
      name: 'Reservation',
      component: reservation
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
    }

  ]
})
