<template>
  <div v-if="getUser !== undefined">
    <v-btn
      v-if="!clicked"
      color="red darken-1"
      class="ma-2"
      outlined
      @click="favorite"
    >
      <v-icon>mdi-heart-outline</v-icon>
    </v-btn>
    <v-btn
      v-if="clicked"
      color="red darken-1"
      class="ma-2"
      outlined
      @click="favorite"
    >
      <v-icon>mdi-heart</v-icon>
    </v-btn>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "PhotoLikeButton",
  props: {
    pcode: String
  },
  data: () => ({
    clicked: false
  }),
  methods: {
    favorite() {
      // update needed: fid must be user id
      const data = { fcode: this.pcode, fid: "admin" };
      let url = `${process.env.VUE_APP_SPRING_URL}/api/favorite/`;
      if (this.clicked === false) {
        url += "insert";
        axios.post(url, data).then(() => {
          this.clicked = !this.clicked;
        });
      } else {
        url += `delete/${this.pcode}/admin`;
        axios.delete(url).then(() => {
          this.clicked = !this.clicked;
        });
      }
    }
  },
  computed: {
    ...mapGetters(["getUser"])
  },
  mounted() {
    this.$store.dispatch("getUserAction");
  }
};
</script>

<style></style>
