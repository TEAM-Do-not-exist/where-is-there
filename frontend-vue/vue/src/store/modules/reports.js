import axios from "axios";

const url = `${process.env.VUE_APP_SPRING_URL}/api/report/selectReportList`;
const token = sessionStorage.getItem("token");

const state = {
  reports: []
};

const mutations = {
  getReports(state) {
    const headers = {
      Authorization: token
    };
    console.log({ headers });
    axios
      .get(url, { headers })
      .then(r => {
        console.log(r);
        const { resvalue } = r.data;
        state.reports = resvalue;
      })
      .catch(e => console.log(e));
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
