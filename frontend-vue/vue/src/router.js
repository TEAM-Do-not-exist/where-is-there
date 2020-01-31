import vue from "vue";
import router from "vue-router";
import Index from "./components/Basic/Index";

import Admin from "./components/Admin/Admin";
import Login from "./views/Login.vue";
import ChatRoom from "./views/ChatRoom.vue";
import signin from "./components/Basic/SignIn";
import signup from "./components/Basic/SignUp";
import error from "./components/Error/errorpage";

vue.use(router);

export default new router({
  mode: "history",
  routes: [
    {
      name: "index",
      path: "/",
      component: Index,
      alias: "/index"
    },
    {
      path: "/admin",
      component: Admin
    },
    {
      path: "/chat",
      name: "Login",
      component: Login
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
      path: "/char-room/:username",
      name: "ChatRoom",
      component: ChatRoom
    },
    {
      path: "*",
      component: error
    }
  ]
});
