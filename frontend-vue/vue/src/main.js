import Vue from 'vue';
import router from './router';
import vuex from 'vuex';
import App from './App.vue'
import vuetify from './plugins/vuetify';
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import '@mdi/font/css/materialdesignicons.css'

Vue.config.productionTip = false
export const $EventBus = new Vue();

new Vue({
  router,
  vuex,
  vuetify,
  render: h => h(App)
}).$mount('#app')
