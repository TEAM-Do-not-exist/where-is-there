<template>
  <v-container>
    <!-- top vue -->
    <v-parallax dark :src="photo[0].psource">
      <v-row align="center" justify="center"></v-row>
    </v-parallax>

    <v-divider :dark="true" class="my-3"></v-divider>

    <!-- information vue -->
    <v-row>
      <v-col cols="12" md="6" xs="12">
        <v-card-title class="headline font-weight-bold mb-4 text-wrap">#거기어디니: {{ photo[0].pname }}</v-card-title>
        <v-card-subtitle class="subtitle-1 font-weight-thin">#어디에있니: {{ photo[0].pplace }}</v-card-subtitle>
        <v-chip class="ma-2">
          <a
            :href="photo[0].purl"
            class="caption font-weight-thin white--text"
            target="_blank"
          >{{ photo[0].purl }}</a>
        </v-chip>
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

<style>
.caption {
  text-decoration: none;
}
</style>
