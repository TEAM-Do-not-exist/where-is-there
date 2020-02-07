import vue from "vue";
import router from "vue-router";

import index from "./components/Basic/index";
import PhotoDetail from "./components/Photo/PhotoDetail";
import PhotoRecommands from "./components/Photo/PhotoRecommands";

import admin from "./components/Admin/Admin";
import Login from "./views/Login.vue";
import ChatRoom from "./views/ChatRoom.vue";
import signin from "./components/Basic/SignIn";
import signup from "./components/Basic/SignUp";
import naversignin from "./components/Basic/NaverSignIn";
import error from "./components/Error/errorpage";
import main from "./components/Error/main";
import mypage from "./components/Mypage/MyPage";
import myfavorite from "./components/Mypage/MyFavorite";
import mycomment from "./components/Mypage/MyComment";

vue.use(router);

export default new router({
  mode: "history",
  routes: [
    {
      name: "index",
      path: "/",
      component: index,
      alias: "/index"
    },
    {
      name: "Photo",
      path: "/photo/:pcode",
      component: PhotoDetail,
      props: true
    },
    {
      name: "PhotoRecommands",
      path: "/photo/:pcode/recommands",
      component: PhotoRecommands,
      props: true
    },
    {
      name: "admin",
      path: "/admin",
      component: admin
    },
    {
      path: "/signin",
      component: signin
    },
    {
      path: "/signup",
      component: signup
    },
    {
      path: "/naversignin",
      component: naversignin
    },
    {
      path: "/chat",
      name: "Login",
      component: Login
    },
    {
      path: "/myfavorite",
      component: myfavorite
    },
    {
      path: "/mycomment",
      component: mycomment
    },
    {
      path: "/mypage",
      component: mypage
    },
    {
      path: "/char-room/:username",
      name: "ChatRoom",
      component: ChatRoom
    },
    {
      path: "/main?:input",
      component: main,
      props: true
    },
    {
      path: "*",
      component: error
    }
  ]
});
