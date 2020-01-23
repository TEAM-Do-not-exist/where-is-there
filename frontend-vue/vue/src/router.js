import vue from "vue";
import router from "vue-router";
import index from "./components/index";
import AdminPage from "./components/Admin/AdminPage.vue";
import Login from "./views/Login.vue";
import ChatRoom from "./views/ChatRoom.vue";
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
      path: "/test",
      component: AdminPage
    },
    {
      path: "/chat",
      name: "Login",
      component: Login
    },
    {
      path: "/char-room/:username",
      name: "ChatRoom",
      component: ChatRoom
    }
  ]
});
