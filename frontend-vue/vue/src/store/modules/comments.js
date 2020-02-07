import axios from "axios";

const url = `${process.env.VUE_APP_SPRING_URL}/api/comment/selectPhotoList`;

const state = {
  comments: []
};

const mutations = {
  getComments(state, ccode) {
    axios.get(`${url}/${ccode}`).then(r => {
      if (r.data !== {}) {
        state.comments = r.data.resvalue;
      }
    });
  }
};

const actions = {
  getCommentsAction(options, ccode) {
    options.commit("getComments", ccode);
  }
};

const getters = {
  comments(state) {
    return state.comments;
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
