<template>
  <v-app id="inspire" v-if='enter'>
    <v-layout  align-center justify-center class="wrapper " fluid fill-height>
        <h1 align="center"  class="effect-underline effect-shine .d-inline-flex">
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
    <v-navigation-drawer v-model="drawerRight" app right class=".d-flex" style="z-index:2">
      <v-list dense>
        <v-list-item-content>
          <div class="inner-wrap" fluid fill-height inner-wrap>
            <Message-List :msgs="msgDatas" class="msg-list">

            </Message-List>
            <Message-From v-on:submitMessage="sendMessage" class="msg-form"></Message-From>
          </div>
        </v-list-item-content>
      </v-list>
    </v-navigation-drawer>

    <v-navigation-drawer v-model="drawer" class=".d-flex" temporary app style="z-index:3">
      <v-container>
        <img
          width="80"
          @click="to_home"
          src="https://lab.ssafy.com/uploads/-/system/appearance/header_logo/1/ssafy_logo.png"
        />
      </v-container>
      <v-list dense>
        <v-list-item @click="to_home">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item @click="to_sign_in">
          <v-list-item-action>
            <v-icon>mdi-account</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Sign in</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item @click="to_admin">
          <v-list-item-action>
            <v-icon>mdi-account</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Administator page</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item @click="to_mypage">
          <v-list-item-action>
            <v-icon>mdi-account</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>MyPage</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <v-footer absolute bottom>
        <span class="white--text">&copy; 2020, Team 404</span>
        <v-spacer/>
        <v-img @click.stop="drawer = !drawer" src="/img/out.png" max-height="35"
         max-width="35" class="pnt" />
        </v-footer>
    </v-navigation-drawer>

    <v-app-bar id="navbar" app flat style="pointer-events: none; z-index:1">
      <!-- <v-app-bar-nav-icon @click.stop="drawer = !drawer" /> -->
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" style="pointer-events: auto;"></v-app-bar-nav-icon>
      <v-spacer style="pointer-events: none;"></v-spacer>
      <i class="far fa-comments fa-2x" @click.stop="drawerRight = !drawerRight" style="pointer-events: auto;"></i>
      <!-- <v-app-bar-nav-icon @click.stop="drawerRight = !drawerRight"></v-app-bar-nav-icon> -->
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
import * as easings from "vuetify/es5/services/goto/easing-patterns";
import { mapMutations, mapState } from "vuex";
import MessageList from "@/components/Chat/MessageList.vue";
import MessageForm from "@/components/Chat/MessageForm.vue";
import Constant from "@/Constant";
import "@fortawesome/fontawesome-free/css/all.css"; // Ensure you are using css-loader
import Vuetify from "vuetify";

export default {
  icons: {
    iconfont: "fa"
  },
  props: {
    source: String
  },
  name: "ChatRoom",
  vuetify: new Vuetify({
    theme: { dark: true }
  }),
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
    ...mapState({
      msgDatas: state => state.socket.msgDatas
    }),
    options() {
      return {
        duration: 300,
        offset: 0,
        easing: 0
      };
    }
  },
  created() {
    const $ths = this;
    this.$socket.on("chat", data => {
      this.pushMsgData(data);
      $ths.datas.push(data);
    });
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
        // name: this.$route.params.username,
        name: "tmp:",
        msg
      });
    },
    enterf(){
      this.enter = false;
    },
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
</style>
<style scoped>

@font-face { font-family: '국립박물관문화재단클래식B'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.0/국립박물관문화재단클래식B.woff') format('woff'); font-weight: normal; font-style: normal; }

.pnt {cursor:pointer;}

.wrapper {
  font-family: '국립박물관문화재단클래식B';
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
  content: '';
  display: inline-block;
  width: 100%;
  border-top: 3px solid;  
  opacity: 0;
	-webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
	transition: opacity 0.35s, transform 0.35s;
	-webkit-transform: scale(0,1);
  transform: scale(0,1);
  padding:5px;
}

.effect-underline:hover:before,
.effect-underline:hover:after {
  opacity: 1;
	-webkit-transform: scale(1);
    transform: scale(1);
    
}

/* effect-shine */
.effect-shine:hover {
  -webkit-mask-image: linear-gradient(-105deg, rgba(123, 123, 123, 0.6) 30%, #000 50%, rgba(0,0,0,.6) 70%);
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
	content: '';
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
  -webkit-transform: scale(1,0);
	transform: scale(1,0);
}

.effect-box:after {
  border-bottom: 1px solid;
  border-top: 1px solid;
  -webkit-transform: scale(0,1);
	transform: scale(0,1);
}

.effect-box:hover:after,
.effect-box:hover:before {
  opacity: 1;
	-webkit-transform: scale(1);
	transform: scale(1);
}

</style>
