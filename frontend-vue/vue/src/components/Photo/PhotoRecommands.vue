<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="6" sm="12">여기는 추천 정보를 보여줄 페이지</v-col>
      <v-col cols="12" md="6" sm="12">여기는 지도를 보여줄 페이지</v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "PhotoRecommands",
  props: {
    pcode: String
  },
  methods: {
    loadPlaces() {
      const place = this.photo[0].pplace;
      const tourUrl = `http://localhost:8000/tour-api/?keyword=${place}`;
      const mangoUrl = `http://localhost:8000/mango/?keyword=${place}`;
      axios
        .get(tourUrl)
        .then(r => {
          console.log(r);
        })
        .catch();
      axios
        .get(mangoUrl)
        .then(r => {
          console.log(r);
        })
        .catch();
    }
  },
  computed: {
    ...mapGetters(["photo"])
  },
  mounted() {
    this.$store.dispatch("getPhotoAction", this.pcode);
    setTimeout(this.loadPlaces, 500);
  }
};
</script>

<style></style>
