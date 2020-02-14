import vue from "vue";
import router from "vue-router";

import index from "./components/Basic/IndexPage";
import photo from "./components/Photo/Photo";
import recommand from "./components/Recommand/Recommand";

import admin from "./components/Admin/Admin";
import chatroom from "./components/views/ChatRoom.vue";
import signin from "./components/Basic/SignIn";
import signup from "./components/Basic/SignUp";
import signup1 from "./components/Basic/SignUp_1";
import signup2 from "./components/Basic/SignUp_2";
import naversignin from "./components/Basic/NaverSignIn";
import error from "./components/Error/errorpage";
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
      alias: "/index",
      component: index
    },
    {
      name: "photo",
      path: "/photo/:pcode",
      component: photo,
      props: true
    },
    {
      name: "recommand",
      path: "/photo/:pcode/recommands",
      component: recommand,
      props: true
    },
    {
      name: "admin",
      path: "/admin",
      component: admin
    },
    {
      name: "signin",
      path: "/signin",
      component: signin
    },
    {
      name: "signup",
      path: "/signup",
      component: signup
    },
    {
      name: "signup1",
      path: "/signup1/:email",
      component: signup1,
      props: true
    },
    {
      name: "signup2",
      path: "/signup2",
      component: signup2,
      props: true
    },
    {
      name: "naversignin",
      path: "/naversignin",
      component: naversignin
    },
    {
      name: "myfavorite",
      path: "/myfavorite",
      component: myfavorite
    },
    {
      name: "mycomment",
      path: "/mycomment",
      component: mycomment
    },
    {
      name: "mypage",
      path: "/mypage",
      component: mypage
    },
    {
      name: "chatroom",
      path: "/char-room/:username",
      component: chatroom
    },
    {
      name: "error",
      path: "*",
      component: error
    }
  ]
});
