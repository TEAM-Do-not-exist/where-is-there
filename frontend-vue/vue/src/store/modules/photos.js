import axios from "axios";

const url = `${process.env.VUE_APP_SPRING_URL}/api/photo`;

const state = {
  photos: []
};

const mutations = {
  getPhotos(state) {
    state.photos = [];
    axios.get(`${url}/selectList`).then(r => {
      const { resvalue } = r.data;
      const suffled = resvalue.slice(0);
      let len = resvalue.length;
      while (len--) {
        const idx = Math.floor((len + 1) * Math.random());
        const change = suffled[idx];
        suffled[idx] = suffled[len];
        suffled[len] = change;
      }
      state.photos = suffled.slice(0, 48);
    });
  }
};

const actions = {
  getPhotosAction(options) {
    options.commit("getPhotos");
  }
};

const getters = {
  photos(state) {
    return state.photos;
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
