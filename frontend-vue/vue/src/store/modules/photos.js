import axios from "axios";

const url = "http://127.0.0.1:8090/api/photo";

const state = {
  photos: []
};

const mutations = {
  getPhotos(state) {
    axios
      .get(`${url}/selectList`)
      .then(r => {
        const { resvalue } = r.data;
        state.photos = resvalue;

        const suffled = state.photos.slice(0);
        let len = state.photos.length;
        while (len--) {
          const idx = Math.floor((len + 1) * Math.random());
          const change = suffled[idx];
          suffled[idx] = suffled[len];
          suffled[len] = change;
        }
        state.photos = suffled.slice(0);
      })
      .catch();
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
