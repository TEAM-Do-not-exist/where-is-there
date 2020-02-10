import jwtDecode from "jwt-decode";

const whoIsThis = () => {
  const token = sessionStorage.getItem("token");
  if (token !== null && token !== "null") {
    const user = jwtDecode(token).member.email;
    if (user) {
      return user;
    }
  }
  return false;
};

const state = {
  user: whoIsThis()
};

const mutations = {
  getUser(state) {
    state.user = whoIsThis() ? whoIsThis() : false;
  }
};

const actions = {
  getUserAction(options) {
    options.commit("getUser");
  }
};

const getters = {
  getUser(state) {
    return state.user;
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
