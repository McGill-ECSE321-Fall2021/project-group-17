import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import reservation from '@/components/reservation.vue'
import itemInstance from '@/components/iteminstance.vue'

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
      name: 'Reservation',
      component: reservation
    },
    {
      path: '/iteminstance',
      name: 'Item Instance',
      component: itemInstance
    }
  ]
})
