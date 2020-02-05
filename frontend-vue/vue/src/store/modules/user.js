import axios from 'axios'

const resourceHost = 'http://192.168.100.52:8090/'
const resourceUrl_nomal = 'api/member/login/'
// const resourceUrl_naver = 'api/member/login/'
// const resourceUrl_kakao = 'api/member/login/'

const enhanceToken = () => {
  const {token} = localStorage
  if (!token) return
  axios.defaults.headers.common['Authorization'] = `${token}`;
};

const state = {
  nickname: null,
  token: null
};

const getters = {};

const actions = {
  LOGIN_NOMAL ({commit}, {id, password}) {
    return axios
      .post(`${resourceHost}/${resourceUrl_nomal}`, {id, password})
      .then(({data}) => {
        commit('LOGIN', data)
        enhanceToken()
      })
  },
  LOGOUT ({commit}) {
    // HTTP 요청 헤더값 제거
    axios.defaults.headers.common['Authorization'] = undefined
    commit('LOGOUT')
  }
};

const mutations = {
  LOGIN (state, {token}) {
    // 스토어에 액세스 토큰 저장
    state.token = token
    localStorage.token = token
  },
  LOGOUT (state) {
    // 토큰 정보 삭제
    state.token = null
    delete localStorage.token
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};