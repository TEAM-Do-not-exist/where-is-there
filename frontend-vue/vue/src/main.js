import Vue from 'vue';
import router from './router';
import vuex from 'vuex';
import App from './App.vue'
import vuetify from './plugins/vuetify';
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import '@mdi/font/css/materialdesignicons.css'
// import VuexRouterSync from 'vuex-router-sync';

Vue.use(vuex);

// const store = new vuex();

// VuexRouterSync.sync(store, router);

Vue.config.productionTip = false;


new Vue({
  router,
  vuex,
  vuetify,
  render: h => h(App)
}).$mount('#app')
