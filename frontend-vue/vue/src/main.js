import Vue from "vue";
import vuex from "vuex";
import VueMasonry from "vue-masonry-css";
import VueResizeText from "vue-resize-text";

import Directives from "./plugins/directives";
import vuetify from "./plugins/vuetify";
import router from "./router";
import store from "./store";

import App from "./App.vue";
import "roboto-fontface/css/roboto/roboto-fontface.css";
import "@mdi/font/css/materialdesignicons.css";
import "./plugins/socketPlugin";

Vue.config.productionTip = false;
Vue.use(Directives);
Vue.use(VueMasonry);
Vue.use(VueResizeText);

new Vue({
  router,
  vuex,
  vuetify,
  store,
  render: h => h(App)
}).$mount("#app");
