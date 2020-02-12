<template>
  <div v-if="isUser">
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
      const data = { fcode: this.pcode, fid: this.getUser };
      let url = `${process.env.VUE_APP_SPRING_URL}/api/favorite/`;
      if (this.clicked === false) {
        url += "insert";
        axios.post(url, data).then(() => {
          this.clicked = !this.clicked;
        });
      } else {
        url += `delete/${this.pcode}/${this.getUser}`;
        axios.delete(url).then(() => {
          this.clicked = !this.clicked;
        });
      }
    }
  },
  computed: {
    ...mapGetters(["getUser"]),
    isUser() {
      return this.getUser ? true : false;
    }
  },
  mounted() {
    this.$store.dispatch("getUserAction");
    const url = `${process.env.VUE_APP_SPRING_URL}/api/favorite/selectMyList/`;
    const token = sessionStorage.token;
    axios.get(url + token).then(r => {
      const checked = r.data.resvalue.filter(
        fav => fav.fcode === Number(this.pcode)
      );
      if (checked.length > 0) {
        this.clicked = true;
      }
    });
  }
};
</script>

<style></style>
