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
  methods: {
    goback() {
      this.$router.go(-1);
    }
  }
};
</script>
