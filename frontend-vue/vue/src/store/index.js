import Vue from "vue";
import Vuex from "vuex";
import photos from "./modules/photos";
import socket from "./modules/socket";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    photos,
    socket
  }
});
