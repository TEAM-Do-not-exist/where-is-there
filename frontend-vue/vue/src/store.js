import Vue from "vue"
import Vuex from "vuex"
import router from "./router"
import http from "./http-common";
Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userInfo: {
            email: '',
            nickname: ''
        },
        isLogin: false,
        isLoginError: false,
    },
    mutations: {
        //로그인 성공
        loginSuccess(state, loginInfo){
            state.isLogin = true
            state.isLoginError = false
            state.userInfo.email = loginInfo.email
            state.userInfo.nickname = loginInfo.nickname

            sessionStorage.setItem("userInfo", JSON.stringify(state.userInfo));
        },
        //로그인 실패
        loginError(state){
            state.isLogin = false
            state.isLoginError = true
        },
        logout(state){
            state.isLogin = false
            state.isLoginError = false
            state.userInfo = {
                email: '',
                nickname: ''
            }
            sessionStorage.removeItem("userInfo");
            window.console.log("로그아웃")
        }
    },
    actions: {
        //로그인 시도
        login({commit}, loginObj){
            http
            .get("/member/view/" + loginObj.id)
            .then(response => {
                if(response.data.resvalue.pw == loginObj.pw){
                    commit("loginSuccess", response.data.resvalue)
                    router.push("/").catch(err => {err})
                }else{
                    commit("loginError")
                }
            })
            .catch(() => {
                commit("loginError")
            })
        },
        logout({commit}){
            commit("logout")
            router.push("/").catch(err => {err})
        }
    }
})