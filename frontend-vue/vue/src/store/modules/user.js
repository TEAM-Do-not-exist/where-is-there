import axios from "axios";
import router from "../../router";

const state = {
  nickname: null,
  token: null
};

const mutations = {
  LOGIN_NOMAL_mutation(state, { id, password }) {
    // 스토어에 액세스 토큰 저장
    axios
      .post(`${process.env.VUE_APP_SPRING_URL}/api/member/login`, {
        email: id,
        pw: password
      })
      .then(r => {
        if (r.data.resvalue == 1) {
          state.token = r.data.token;
          sessionStorage.setItem("token", r.data.token);
          axios.defaults.headers.common["Authorization"] = r.data.token;
          axios
            .get(
              `${process.env.VUE_APP_SPRING_URL}/api/member/selectOneToken/` +
                r.data.token
            )
            .then(r => {
              state.nickname = r.data.resvalue.nickname;
              sessionStorage.setItem("nickname", r.data.resvalue.nickname);
              alert(sessionStorage.getItem("nickname") + "님 안녕하세요!!!");
              router.push("/");
            });
        } else if (r.data.state == -2) {
          sessionStorage.setItem("token", null);
          sessionStorage.setItem("nickname", null);
          state.token = null;
          state.nickname = null;
          if (r.data.resvalue == 2) {
            alert(
              "로그인을 실패하였습니다. 이 아이디는 네이버로 가입이 되어있습니다."
            );
          } else if (r.data.resvalue == 3) {
            alert(
              "로그인을 실패하였습니다. 이 아이디는 카카오로 가입이 되어있습니다."
            );
          }
          router.push("/signin");
        } else {
          sessionStorage.setItem("token", null);
          sessionStorage.setItem("nickname", null);
          state.token = null;
          state.nickname = null;
          alert("로그인을 실패하였습니다");
          router.push("/signin");
        }
      });
  },
  LOGIN_NAVER_mutation(state, access_token) {
    // 스토어에 액세스 토큰 저장
    axios
      .get(
        `${process.env.VUE_APP_SPRING_URL}/api/external/login_naver/` +
          access_token
      )
      .then(r => {
        state.token = r.data.token;
        sessionStorage.setItem("token", r.data.token);
        axios.defaults.headers.common["Authorization"] = r.data.token;
        if (r.data.state == 1) {
          axios
            .get(
              `${process.env.VUE_APP_SPRING_URL}/api/member/selectOneToken/` +
                r.data.token
            )
            .then(r => {
              state.nickname = r.data.resvalue.nickname;
              sessionStorage.setItem("nickname", r.data.resvalue.nickname);
              alert(sessionStorage.getItem("nickname") + "님 안녕하세요!!!");
              router.push("/");
            });
        } else if (r.data.state == -2) {
          sessionStorage.setItem("token", null);
          sessionStorage.setItem("nickname", null);
          state.token = null;
          state.nickname = null;
          if (r.data.resvalue == 1) {
            alert(
              "로그인을 실패하였습니다. 이 아이디는 일반회원으로 가입이 되어있습니다."
            );
          } else if (r.data.resvalue == 3) {
            alert(
              "로그인을 실패하였습니다. 이 아이디는 카카오로 가입이 되어있습니다."
            );
          }
          router.push("/signin");
        } else if (r.data.state == -3) {
          sessionStorage.setItem("token", null);
          sessionStorage.setItem("nickname", null);
          state.token = null;
          state.nickname = null;
          alert("로그인을 실패하였습니다.");
          router.push("/signin");
        }
      });
  },
  LOGIN_KAKAO_mutation(state, access_token) {
    // 스토어에 액세스 토큰 저장
    axios
      .get(
        `${process.env.VUE_APP_SPRING_URL}/api/external/login_kakao/` +
          access_token
      )
      .then(r => {
        state.token = r.data.token;
        axios.defaults.headers.common["Authorization"] = r.data.token;
        sessionStorage.setItem("token", r.data.token);
        if (r.data.state == 1) {
          axios
            .get(
              `${process.envVUE_APP_SPRING_URL}/api/member/selectOneToken/` +
                r.data.token
            )
            .then(r => {
              state.nickname = r.data.resvalue.nickname;
              sessionStorage.setItem("nickname", r.data.resvalue.nickname);
              alert(sessionStorage.getItem("nickname") + "님 안녕하세요!!!");
              router.push("/");
            });
        } else if (r.data.state == -2) {
          sessionStorage.setItem("token", null);
          sessionStorage.setItem("nickname", null);
          state.token = null;
          state.nickname = null;
          if (r.data.resvalue == 2) {
            alert(
              "로그인을 실패하였습니다. 이 아이디는 네이버로 가입이 되어있습니다."
            );
          } else if (r.data.resvalue == 1) {
            alert(
              "로그인을 실패하였습니다. 이 아이디는 일반회원으로 가입이 되어있습니다."
            );
          }
          router.push("/signin");
        } else if (r.data.state == -3) {
          sessionStorage.setItem("token", null);
          sessionStorage.setItem("nickname", null);
          state.token = null;
          state.nickname = null;
          alert("로그인을 실패하였습니다.");
          router.push("/signin");
        }
      });
  },
  LOGOUT(state) {
    // 토큰 정보 삭제
    sessionStorage.setItem("token", null);
    sessionStorage.setItem("nickname", null);
    state.token = null;
    state.nickname = null;
    axios.defaults.headers.common["Authorization"] = undefined;
    delete localStorage.token;
  },
  SET_HEADER_mutation() {
    axios.defaults.headers.common["Authorization"] = sessionStorage.getItem(
      "token"
    );
  }
};

const actions = {
  LOGIN_NOMAL(options, { id, password }) {
    options.commit("LOGIN_NOMAL_mutation", { id, password });
  },
  LOGIN_NAVER(options, access_token) {
    options.commit("LOGIN_NAVER_mutation", access_token);
  },
  LOGIN_KAKAO(options, access_token) {
    options.commit("LOGIN_KAKAO_mutation", access_token);
  },
  LOGOUT({ commit }) {
    commit("LOGOUT");
  },
  SET_HEADER(options) {
    options.commit("SET_HEADER_mutation");
  }
};

const getters = {
  token(state) {
    state.token = sessionStorage.getItem("token");
    return state.token;
  },
  nickname(state) {
    state.nickname = sessionStorage.getItem("nickname");
    return state.nickname;
  }
};

export default {
  state,
  getters,
  actions,
  mutations
};
