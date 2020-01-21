<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      class=".d-flex"
      app
    >
      <v-list dense>
        <v-list-item router-link href="/">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item router-link href="/signin">
          <v-list-item-action>
            <v-icon>mdi-account</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Sign in</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      color="blue"
      collapse
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <v-toolbar-title>사이트 이름</v-toolbar-title>
    </v-app-bar>

    <v-content>
      <router-view/>
    </v-content>
    <v-footer
      color="blue"
      app
    >
      <span class="white--text">&copy; 2019 어쩌구 저쩌구 팀 404</span>
    </v-footer>
  </v-app>
</template>

<script>
import axios from 'axios'

export default {
  props: {
    source: String,
  },
  data: () => ({
    drawer: null,
    src: null,
  }),
  mounted(){
    axios
    .get('') //여기에 url이 들어갑니다
    .then(response => {
      this.data.src = response;
    })
  }
};
</script>

<style>
</style>
