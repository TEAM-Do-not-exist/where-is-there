import Vue from 'vue';
import router from './router';
import vuex from 'vuex';
import App from './App.vue'
import vuetify from './plugins/vuetify';
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import '@mdi/font/css/materialdesignicons.css'
import './plugins/socketPlugin';
import Directives from './plugins/directives';
import store from './store';

Vue.config.productionTip = false
Vue.use(Directives);

new Vue({
  router,
  vuex,
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
