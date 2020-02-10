import jwtDecode from "jwt-decode";

const isAdmin = () => {
  const token = sessionStorage.getItem("token");
  if (token !== "null") {
    const user = jwtDecode(token).member.email;
    if (user === "admin") {
      return true;
    }
  }
  return false;
};

const state = {
  admin: isAdmin() ? true : false
};

const mutations = {};

const actions = {};

const getters = {
  getAdmin(state) {
    return state.admin;
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
