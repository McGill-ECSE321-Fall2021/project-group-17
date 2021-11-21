import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import reservation from '@/components/reservation.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/reservation',
      name: 'EventRegistration',
      component: reservation
    }
  ]
})
