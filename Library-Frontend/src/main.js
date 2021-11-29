// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from "bootstrap-vue"
import App from './App'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
<<<<<<< HEAD
<<<<<<< HEAD
import VueMaterial from 'vue-material'
import VueCookie from 'vue-cookie'
=======

>>>>>>> a1ac5a2f3c47a31157723800edbc3f2527359006
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'

<<<<<<< HEAD
=======
import VueMaterial from 'vue-material'
import VueCookie from 'vue-cookie'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'


>>>>>>> df0c17c9994ed7685f3e715c70140bd48af47a54
Vue.use(BootstrapVue)
Vue.config.productionTip = false
Vue.use(VueMaterial)
Vue.use(VueCookie)
<<<<<<< HEAD
=======

import VueMaterial from 'vue-material'
import VueCookie from 'vue-cookie'

Vue.use(BootstrapVue);
Vue.config.productionTip = false;
Vue.use(VueMaterial);
Vue.use(VueCookie);
>>>>>>> a1ac5a2f3c47a31157723800edbc3f2527359006
=======

>>>>>>> df0c17c9994ed7685f3e715c70140bd48af47a54

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  template: "<App/>",
  components: { App }
});
