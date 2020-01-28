<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawerRight" app right>
      <v-list dense>
        <v-list-item-content>
          <div class="inner-wrap" fluid fill-height inner-wrap>
            <Message-List :msgs="msgDatas" class="msg-list"></Message-List>
            <Message-From v-on:submitMessage="sendMessage" class="msg-form"></Message-From>
          </div>
        </v-list-item-content>
      </v-list>
    </v-navigation-drawer>

    <v-navigation-drawer v-model="drawer" class=".d-flex" temporary app style="z-index:1">
      <v-container>
        <img width="80" @click="to_home"
          src="https://lab.ssafy.com/uploads/-/system/appearance/header_logo/1/ssafy_logo.png" />
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
        <v-list-item @click="to_chat">
          <v-list-item-action>
            <v-icon>mdi-account</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>chat room</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <v-footer absolute bottom>??</v-footer>
    </v-navigation-drawer>


    <v-app-bar app light flat>
      <!-- <v-app-bar-nav-icon @click.stop="drawer = !drawer" /> -->
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-spacer></v-spacer>
      <i class="far fa-comments fa-2x" @click.stop="drawerRight = !drawerRight"></i>
      <!-- <v-app-bar-nav-icon @click.stop="drawerRight = !drawerRight"></v-app-bar-nav-icon> -->
    </v-app-bar>

    <v-content>
      <router-view />
    </v-content>

    <v-footer color="blue" app>
      <span class="white--text">&copy; 2020, Team 404</span>
      <v-fab-transition>
        <v-btn color="blue" dark absolute top right fab @click="$vuetify.goTo(0)">
          <v-icon>mdi-chevron-up</v-icon>
        </v-btn>
      </v-fab-transition>
    </v-footer>
  </v-app>
</template>

<script>
  import * as easings from "vuetify/es5/services/goto/easing-patterns";
  import axios from "axios";
  import {
    mapMutations,
    mapState
  } from "vuex";
  import MessageList from "@/components/Chat/MessageList.vue";
  import MessageForm from "@/components/Chat/MessageForm.vue";
  import Constant from "@/Constant";
  import '@fortawesome/fontawesome-free/css/all.css' // Ensure you are using css-loader

  export default {
    icons: {
      iconfont: 'fa',
    },
    props: {
      source: String
    },
    name: "ChatRoom",

    data: () => ({
      drawer: null,
      drawerRight: null,
      right: false,
      left: false,
      src: null,
      easing: "easeInOutCubic",
      easings: Object.keys(easings),
      enter: true,

    }),
    components: {
      "Message-List": MessageList,
      "Message-From": MessageForm
    },
    mounted() {
      axios
        .get("") //여기에 url이 들어갑니다
        .then(response => {
          this.data.src = response;
        });
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
      },

    },
    created() {
      const $ths = this;
      this.$socket.on("chat", data => {
        this.pushMsgData(data);
        $ths.datas.push(data);
      });},

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
      to_test() {
        this.$router.push("/test");
      },
      to_chat() {
        this.$router.push("/chat");
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
          name: 'mymy',
          msg
        });
      }
    }
  };
</script>

<style>
  .theme--light.v-sheet {
    background-color: #fff0;
  }
</style>
<style>
  .msg-form {
    bottom: -28px;
    position: absolute;
    left: 0;
    right: 0;
  }

  .msg-list {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 60px;
    overflow-x: scroll;
  }
</style>