import Vue from "vue";
import Vuex from "vuex";
import reports from "./modules/reports";
import photos from "./modules/photos";
import photo from "./modules/photo";
import comments from "./modules/comments";
import socket from "./modules/socket";
import user from "./modules/user";
import auth from "./modules/auth";
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
    comments,
    socket,
    user,
    auth
  }
});
