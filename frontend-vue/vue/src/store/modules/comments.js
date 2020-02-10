import axios from "axios";

const url = process.env.VUE_APP_SPRING_URL;

const state = {
  comments: []
};

const mutations = {
  getComments(state, ccode) {
    axios.get(`${url}/api/comment/selectPhotoList/${ccode}`).then(r => {
      if (JSON.stringify(r.data) !== "{}") {
        state.comments = r.data.resvalue;
      }
    });
  },
  deleteComment(state, ccode) {
    state.comments = state.comments.filter(
      comment => comment.ccode !== Number(ccode)
    );
  }
};

const actions = {
  getCommentsAction(options, ccode) {
    options.commit("getComments", ccode);
  },
  deleteCommentAction(options, ccode) {
    options.commit("deleteComment", ccode);
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
