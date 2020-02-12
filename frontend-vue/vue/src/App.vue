<template>
  <v-app id="inspire" v-if="enter">
    <v-layout align-center justify-center class="wrapper " fluid fill-height>
      <h1 align="center" class="effect-underline effect-shine .d-inline-flex">
        <a @click="enterf" style="color:rgba(255, 255, 255, 0.65);">
          거기
        </a>
        <a @click="enterf" style="color:rgba(255, 255, 255, 0.65);">
          어디니
        </a>
      </h1>
    </v-layout>
  </v-app>

  <v-app id="inspire" v-else>
    <!-- 오른쪽 채팅창 -->
    <v-navigation-drawer
      v-model="drawerRight"
      app
      right
      class=".d-flex"
      style="z-index:2"
    >
      <v-list dense>
        <div class="inner-wrap" fluid fill-height inner-wrap>
          <Message-List :msgs="msgDatas" class="msg-list"> </Message-List>
          <Message-From
            v-on:submitMessage="sendMessage"
            class="msg-form"
          ></Message-From>
        </div>
      </v-list>
    </v-navigation-drawer>

    <!-- 왼쪽 네비게이션바 -->
    <v-navigation-drawer
      v-model="drawer"
      class=".d-flex"
      temporary
      app
      style="z-index:3"
    >
      <v-container>
        <v-card>
          <!-- 맨 위 로고 들어갈 자리 -->
          <div style="text-align:center;" @click="to_home">
            <i class="fas fa-home fa-10x"></i>
          </div>
          <!-- 로그인 되었을 때 -->
          <div
            v-if="token != 'null' && token != undefined"
            style="text-align:center;"
          >
            <br />
            <span v-text="nickname"> </span>
            <span>
              님 환영합니다 :>
            </span>
            <br /><br />
            <span
              v-if="token != 'null' && token != undefined"
              @click="to_mypage"
              style="cursor:pointer; display:inline-block; width:100px; text-decoration:underline;"
            >
              MyPage
            </span>
            <span
              v-if="token != 'null' && token != undefined"
              @click="to_log_out"
              style="text-align:center; cursor:pointer; text-decoration:underline;"
            >
              Logout
            </span>
          </div>
          <!-- 로그인 안되었을 때 -->
          <div v-else style="text-align:center;">
            <br />
            <div style="text-align:center">로그인을 해주세요! :(</div>
            <br />
            <span
              v-if="token == 'null' || token == undefined"
              @click="to_sign_in"
              style="cursor:pointer; text-decoration:underline;"
            >
              로그인
            </span>
          </div>
        </v-card>
      </v-container>

      <v-list dense v-if="isAdmin">
        <v-list-item @click="to_admin">
          <v-list-item-action>
            <i class="fas fa-user-shield"></i>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Administator page</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-footer absolute bottom>
        <span class="white--text">&copy; 2020, Team 404</span>
        <v-spacer />
        <v-img
          @click.stop="drawer = !drawer"
          src="/img/out.png"
          max-height="35"
          max-width="35"
          class="pnt"
        />
      </v-footer>
    </v-navigation-drawer>

    <v-app-bar id="navbar" app flat style="pointer-events: none; z-index:1">
      <v-app-bar-nav-icon
        @click.stop="drawer = !drawer"
        style="pointer-events: auto;"
      ></v-app-bar-nav-icon>
      <v-spacer style="pointer-events: none;"></v-spacer>
      <i
        class="far fa-comments fa-2x"
        @click.stop="drawerRight = !drawerRight"
        style="pointer-events: auto;"
      ></i>
    </v-app-bar>

    <v-content>
      <router-view />
    </v-content>

    <v-footer color="#fff0" style="z-index:1" inset app>
      <p>.</p>
      <v-fab-transition>
        <v-btn outlined dark absolute top right fab @click="$vuetify.goTo(0)">
          <v-icon>mdi-chevron-up</v-icon>
        </v-btn>
      </v-fab-transition>
    </v-footer>
  </v-app>
</template>

<script>
import Vuetify from "vuetify";
import { mapMutations, mapState, mapGetters } from "vuex";
import MessageList from "@/components/Chat/MessageList.vue";
import MessageForm from "@/components/Chat/MessageForm.vue";
import Constant from "@/Constant";
import "@fortawesome/fontawesome-free/css/all.css"; // Ensure you are using css-loader
import * as easings from "vuetify/es5/services/goto/easing-patterns";

export default {
  name: "WhereIsThere",
  vuetify: new Vuetify({
    theme: { dark: true }
  }),
  props: {
    source: String
  },
  data: () => ({
    drawer: null,
    drawerRight: false,
    right: false,
    left: false,
    easing: "easeInOutCubic",
    easings: Object.keys(easings),
    enter: true
  }),
  components: {
    "Message-List": MessageList,
    "Message-From": MessageForm
  },
  computed: {
    ...mapGetters(["nickname", "getUser"]),
    ...mapState({
      msgDatas: state => state.socket.msgDatas,
      nickname1: state => state.user.nickname,
      token: state => state.user.token
    }),
    isAdmin() {
      return this.getUser === process.env.VUE_APP_ADMIN_EMAIL ? true : false;
    },
    options() {
      return {
        duration: 300,
        offset: 0,
        easing: 0
      };
    }
  },
  created() {
    this.$socket.on("chat", data => {
      this.pushMsgData(data);
    });
  },
  mounted() {
    this.$store.dispatch("SET_HEADER");
  },
  methods: {
    to_admin() {
      this.$router.push("/admin");
    },
    to_home() {
      this.$router.push("/");
    },
    to_sign_in() {
      this.$router.push("/signin");
    },
    to_log_out() {
      alert("로그아웃 되었습니다.");
      this.$store.dispatch("LOGOUT");
      this.$router.push("/");
    },
    to_chat() {
      this.$router.push("/chat");
    },
    to_mypage() {
      this.$router.push("/mypage");
    },
    ...mapMutations({
      pushMsgData: Constant.PUSH_MSG_DATA
    }),
    sendMessage(msg) {
      this.pushMsgData({
        from: {
          name: "나:"
        },
        msg
      });
      this.$sendMessage({
        name: this.nickname + ":",
        msg
      });
    },
    enterf() {
      this.enter = false;
    }
  }
};
</script>

<style scoped>
#navbar {
  background-color: #fff0;
}
.v-footer--fixed p {
  visibility: hidden;
}
@font-face {
  font-family: "국립박물관문화재단클래식B";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.0/국립박물관문화재단클래식B.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.pnt {
  cursor: pointer;
}
.wrapper {
  font-family: "국립박물관문화재단클래식B";
}
h1 a {
  color: white;
  font-size: 4em;
  text-decoration: none;
  display: inline-block;
  position: relative;
}
@media screen and (max-width: 800px) {
  h1 a {
    font-size: 2em;
  }
}

/*effect-underline*/
.effect-underline:before,
.effect-underline:after {
  content: "";
  display: inline-block;
  width: 100%;
  border-top: 3px solid;
  opacity: 0;
  -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
  transition: opacity 0.35s, transform 0.35s;
  -webkit-transform: scale(0, 1);
  transform: scale(0, 1);
  padding: 5px;
}
.effect-underline:hover:before,
.effect-underline:hover:after {
  opacity: 1;
  -webkit-transform: scale(1);
  transform: scale(1);
}

/* effect-shine */
.effect-shine:hover {
  -webkit-mask-image: linear-gradient(
    -105deg,
    rgba(123, 123, 123, 0.6) 30%,
    #000 50%,
    rgba(0, 0, 0, 0.6) 70%
  );
  -webkit-mask-size: 200%;
  animation: shine 2s infinite;
}
@-webkit-keyframes shine {
  from {
    -webkit-mask-position: 150%;
  }
  to {
    -webkit-mask-position: -50%;
  }
}

/*effect-box*/
.effect-box:after,
.effect-box:before {
  content: "";
  position: absolute;
  display: inline-block;
  height: 1em;
  width: 100%;
  opacity: 0;
  -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
  transition: opacity 0.35s, transform 0.35s;
}
.effect-box:before {
  border-left: 1px solid;
  border-right: 1px solid;
  -webkit-transform: scale(1, 0);
  transform: scale(1, 0);
}
.effect-box:after {
  border-bottom: 1px solid;
  border-top: 1px solid;
  -webkit-transform: scale(0, 1);
  transform: scale(0, 1);
}
.effect-box:hover:after,
.effect-box:hover:before {
  opacity: 1;
  -webkit-transform: scale(1);
  transform: scale(1);
}
</style>
