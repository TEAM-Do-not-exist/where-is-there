import Vue from "vue";
import Vuex from "vuex";
import reports from "./modules/reports";
import photos from "./modules/photos";
import photo from "./modules/photo";
import socket from "./modules/socket";
import user from "./modules/user";
// import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    reports,
    photos,
    photo,
    socket,
    user
  }
});
