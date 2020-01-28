import axios from 'axios'

const resourceHost = 'http://localhost:9090'
const resourceUrl = 'api/login'

const enhanceAccessToeken = () => {
  const {accessToken} = localStorage
  if (!accessToken) return
  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
};

const state = {
  accessToken: null
};

const getters = {};

const actions = {
  LOGIN ({commit}, {id, password}) {
    return axios
      .post(`${resourceHost}/${resourceUrl}`, {id, password})
      .then(({data}) => {
        commit('LOGIN', data)
        enhanceAccessToeken()
      })
  },
  LOGOUT ({commit}) {
    // HTTP 요청 헤더값 제거
    axios.defaults.headers.common['Authorization'] = undefined
    commit('LOGOUT')
  }
};

const mutations = {
  LOGIN (state, {accessToken}) {
    // 스토어에 액세스 토큰 저장
    state.accessToken = accessToken
    localStorage.accessToken = accessToken
  },
  LOGOUT (state) {
    // 토큰 정보 삭제
    state.accessToken = null
    delete localStorage.accessToken
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};