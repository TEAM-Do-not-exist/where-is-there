<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer" class=".d-flex" temporary app>
      <v-container>
        <img
          width="80"
          @click="to_home"
          src="https://lab.ssafy.com/uploads/-/system/appearance/header_logo/1/ssafy_logo.png"
        />
        <!-- <h3>
          거기 어디니?
        </h3>-->
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
        <v-list-item @click="to_test">
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
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <!-- <img width="40" src="https://lab.ssafy.com/uploads/-/system/appearance/header_logo/1/ssafy_logo.png">
      <v-toolbar-title>TEAM 404</v-toolbar-title>-->
    </v-app-bar>

    <v-content>
      <router-view />
      <!-- <div>
      <v-btn
        fixed
        top
        left
        class="elevation-0"
        large
        @click.stop="drawer = !drawer"
      >
        <v-icon>mdi-menu</v-icon>
      </v-btn>
      </div>-->
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

export default {
  props: {
    source: String
  },
  data: () => ({
    drawer: null,
    src: null,
    easing: "easeInOutCubic",
    easings: Object.keys(easings),
    enter: true
  }),
  mounted() {
    axios
      .get("") //여기에 url이 들어갑니다
      .then(response => {
        this.data.src = response;
      });
  },
  computed: {
    options() {
      return {
        duration: 300,
        offset: 0,
        easing: 0
      };
    }
  },
  methods: {
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
    }
  }
};
</script>

<style>
/* .theme--light.v-btn:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined){
  background-color : #fff0;
} */
.theme--light.v-sheet {
  background-color: #fff0;
}
</style>
