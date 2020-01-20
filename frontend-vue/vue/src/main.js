import Vue from 'vue';
import router from './router';
import vuex from 'vuex';
import App from './App.vue'
import vuetify from 'vuetify'

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  vuex,
  render: h => h(App),
}).$mount('#app')
