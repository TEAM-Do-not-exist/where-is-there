<template>
  <v-app>
    <v-container>
      <h1>You missed own way!</h1>
      <a @click="goback">How about click here...</a>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "ErrorPage",
  methods: {
    goback() {
      this.$router.replace("/");
    }
  }
};
</script>

<style></style>
