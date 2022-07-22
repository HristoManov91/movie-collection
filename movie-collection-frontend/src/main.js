import Vue from 'vue'
import App from './App.vue'
import VModal from 'vue-js-modal'
import Vuelidate from 'vuelidate'
import store from './store'
import router from './router'

import {library} from "@fortawesome/fontawesome-svg-core"
import {fas} from '@fortawesome/free-solid-svg-icons'
import {faR} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

library.add(fas , faR);
Vue.component('font-awesome-icon' , FontAwesomeIcon)

Vue.use(VModal)
Vue.use(Vuelidate)

Vue.config.productionTip = false

// router.beforeEach((to, from, next) => {
//     const publicPages = ['login', 'register', 'home'];
//     const authRequired = !publicPages.includes(to.name);
//     const loggedIn = localStorage.getItem('user');
//     // trying to access a restricted page + not logged in
//     // redirect to login page
//     if (authRequired && !loggedIn) {
//         next('/login');
//     } else {
//         next();
//     }
// });

new Vue({
  render: h => h(App),
  store,
  router
}).$mount('#app')