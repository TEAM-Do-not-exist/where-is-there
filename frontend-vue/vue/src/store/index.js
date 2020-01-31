import Vue from "vue";
import Vuex from "vuex";
import photos from "./modules/photos";
import photo from "./modules/photo";
import socket from "./modules/socket";
import user from "./modules/user";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    photos,
    photo,
    socket,
    user
  }
});
