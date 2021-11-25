import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import reservation from '@/components/reservation'
import LibrarianWelcome from '@/components/LibrarianWelcome.vue';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path:'/LibrarianWelcomePage',
      name:'Librarian Welcome Page',
      component: LibrarianWelcome
    },
    {
      path: '/reservation',
      name: 'Reservation',
      component: reservation
    }
  ]
})
