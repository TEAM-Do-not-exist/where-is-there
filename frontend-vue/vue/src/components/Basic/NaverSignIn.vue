<template>
  <v-app id="inspire"></v-app>
</template>

<script>
export default {
  name: "NaverSignIn",
  methods: {
    naverToken() {
      var access_token = window.location.href
        .split("#")[1]
        .split("&")[0]
        .split("=")[1];
      this.$store.dispatch("LOGIN_NAVER", access_token);
    }
  },
  mounted() {
    this.naverToken();
  }
};
</script>

<style></style>
