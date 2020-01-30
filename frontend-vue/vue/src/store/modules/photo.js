import axios from "axios";

const url = "http://127.0.0.1:8090/api/photo";

const state = {
  photo: {}
};

const mutations = {
  getPhoto(state, pcode) {
    axios
      .get(`${url}/selectOne/${pcode}`)
      .then(r => {
        const { resvalue } = r.data;
        state.photo = resvalue;
      })
      .catch();
  }
};

const actions = {
  getPhotoAction(options, pcode) {
    options.commit("getPhoto", pcode);
  }
};

const getters = {
  photo(state) {
    return state.photo;
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
