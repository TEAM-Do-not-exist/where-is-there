import Vue from "vue";
import Vuex from "vuex";
import socket from "./modules/socket";
import user from "./modules/user";

Vue.use(Vuex);
export default new Vuex.Store({
  modules: {
    socket,
    user
  }
});
