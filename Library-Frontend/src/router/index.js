import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import reservation from '@/components/reservation'
import homepage from '@/components/homepage'
import profile from '@/components/Profile'
import updateAccount from '@/components/UpdateAccount'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/app',
      name: 'EventRegistration',
      component: reservation
    },
    {path:'/homepage',
  name: 'HomePage',
component: homepage },
{path:'/Profile',
  name: 'Profile',
component: profile},
{path:'/UpdateAccount',
  name: 'UpdateAccount',
component: updateAccount}

  ]
})
