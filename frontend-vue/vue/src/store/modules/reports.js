import axios from "axios";
import jwtDecode from "jwt-decode";

const url = `${process.env.VUE_APP_SPRING_URL}/api/report/selectReportList`;
const isAdmin = () => {
  const token = sessionStorage.getItem("token");
  if (token !== null && token !== "null") {
    const user = jwtDecode(token).member.email;
    if (user === process.env.VUE_APP_ADMIN_EMAIL) {
      return true;
    }
  }
  return false;
};

const state = {
  reports: []
};

const mutations = {
  getReports(state) {
    if (isAdmin() === true) {
      axios.get(url).then(r => {
        const { resvalue } = r.data;
        state.reports = resvalue;
      });
    }
  }
};

const actions = {
  getReportsAction(options) {
    options.commit("getReports");
  }
};

const getters = {
  reports(state) {
    return state.reports;
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
