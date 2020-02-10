import axios from "axios";

const url = `${process.env.VUE_APP_SPRING_URL}/api/report/selectReportList`;

const state = {
  reports: []
};

const mutations = {
  getReports(state) {
    axios.get(url).then(r => {
      const { resvalue } = r.data;
      state.reports = resvalue;
    });
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
