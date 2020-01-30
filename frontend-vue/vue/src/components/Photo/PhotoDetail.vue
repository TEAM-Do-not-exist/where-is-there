<template>
  <v-container>
    <!-- top vue -->
    <v-parallax dark :src="photo.psource">
      <v-row align="center" justify="center"></v-row>
    </v-parallax>

    <v-divider :dark="true" class="my-3"></v-divider>

    <!-- information vue -->
    <!-- {{ photo }} -->
    <v-row>
      <v-col cols="12" md="6" xs="12">
        <v-card-title class="headline font-weight-bold mb-4 text-wrap">#거기어디니: {{ photo.pname }}</v-card-title>
        <v-card-subtitle class="subheading">#어디에있니: {{ photo.pplace }}</v-card-subtitle>
      </v-col>
      <PhotoDetailKakaoMap />
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import PhotoDetailKakaoMap from "./PhotoDetailKakaoMap";

export default {
  name: "PhotoDetail",
  components: { PhotoDetailKakaoMap: PhotoDetailKakaoMap },
  data: () => ({}),
  props: {
    pcode: String
  },
  methods: {},
  computed: {
    ...mapGetters(["photo"])
  },
  mounted() {
    this.$store.dispatch("getPhotoAction", this.pcode);
  }
};
</script>

<style></style>
