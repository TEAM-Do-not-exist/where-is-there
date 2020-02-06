import axios from "axios";
import router from "../../router";

// const resourceHost = 'http://192.168.100.52:8090'
// const resourceUrl_nomal = 'api/member/login'
// const resourceUrl_naver = 'api/member/login/'
// const resourceUrl_kakao = 'api/member/login/'

// const enhanceToken = () => { 
//   const {token} = localStorage
//   if (!token) return 
//   axios.defaults.headers.common['Authorization'] = `${token}`;
// };

const state = {
  nickname: null,
  token: null
};
const mutations = {

  LOGIN_NOMAL_mutation (state, {id,password}) {
    // 스토어에 액세스 토큰 저장
    axios
      .post(`http://localhost:8090/api/member/login`, {
        email : id,
        pw : password
      })
      .then(r=>{
        if(r.data.resvalue==1){
          state.token = r.data.token
          sessionStorage.setItem('token',r.data.token)
          axios
            .get('http://localhost:8090/api/member/selectOneToken/'+r.data.token)
            .then(r=>{
              state.nickname = r.data.resvalue.nickname
              sessionStorage.setItem('nickname',r.data.resvalue.nickname)
              // alert(r.data.resvalue.nickname)
              router.push('/')
            })
        }else{
          sessionStorage.setItem('token',null)
          sessionStorage.setItem('nickname',null)
          alert('로그인을 실패하였습니다')
          router.push('/signin')
        }
          
      })
  },
  LOGIN_NAVER_mutation (state, access_token) {
    // 스토어에 액세스 토큰 저장
    axios
      .get('http://localhost:8090/api/external/login_naver/'+access_token)
      .then(r=>{
        state.token = r.data.token
        sessionStorage.setItem('token',r.data.token)
        axios
          .get('http://localhost:8090/api/member/selectOneToken/'+r.data.token)
          .then(r=>{
            state.nickname = r.data.resvalue.nickname
            sessionStorage.setItem('nickname',r.data.resvalue.nickname)
            // console.log(sessionStorage.getItem('nickname'))
            alert(sessionStorage.getItem('nickname'))
          })
      })
  },
  LOGIN_KAKAO_mutation (state, access_token) {
    // 스토어에 액세스 토큰 저장
    axios
      .get('http://localhost:8090/api/external/login_kakao/'+access_token)
      .then(r=>{
        state.token = r.data.token
        sessionStorage.setItem('token',r.data.token)
        axios
          .get('http://localhost:8090/api/member/selectOneToken/'+r.data.token)
          .then(r=>{
            state.nickname = r.data.resvalue.nickname
            sessionStorage.setItem('nickname',r.data.resvalue.nickname)
            alert("kakao : "+sessionStorage.getItem('nickname'))
          })
      })
  },
  LOGOUT(state) {
    // 토큰 정보 삭제

    state.token = null
    delete localStorage.token
  },
};

const actions = {
  LOGIN_NOMAL (options, {id, password}) {
    options.commit("LOGIN_NOMAL_mutation",{id, password});
  },
  LOGIN_NAVER (options, access_token) {
    options.commit("LOGIN_NAVER_mutation",access_token);
  },
  LOGIN_KAKAO (options, access_token) {
    options.commit("LOGIN_KAKAO_mutation",access_token);
  },
  LOGOUT({ commit }) {
    // HTTP 요청 헤더값 제거
    axios.defaults.headers.common["Authorization"] = undefined;
    commit("LOGOUT");
  }
};

const getters = {
  token(state) {
    state.token = sessionStorage.getItem('token');
    return state.token;
  },
  nickname(state){
    state.nickname = sessionStorage.getItem('nickname');
    return state.nickname;
  }
};


export default {
  state,
  getters,
  actions,
  mutations
};
