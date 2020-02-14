<template>
  <div>
    <p class="font-weight-black" v-resize-text="resizeText">
      <v-icon :size="40" class="ma-4">mdi-airplane-takeoff</v-icon>당신만을 위한 장소
    </p>
    <v-divider></v-divider>
    <masonry :cols="mansonryCols" :gutters="mansonryGutters">
      <div v-for="(photo, idx) in photos" :key="idx" id="photos">
        <!-- photo image, if click it, show detail -->
        <v-img :src="photo.psource" class="grey lighten-2" @click="selectPhoto(photo)">
          <template v-slot:placeholder>
            <v-row class="fill-height ma-0" align="center" justify="center">
              <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
            </v-row>
          </template>
        </v-img>
      </div>
    </masonry>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "IndexPage",
  data: () => ({
    resizeText: { minFontSize: 10, maxFontSize: 60, delay: 0 },
    mansonryCols: { default: 4, 1000: 3, 700: 2, 400: 1 },
    mansonryGutters: { default: "30px", 700: "15px" },
    openModal: false
  }),
  methods: {
    selectPhoto(photo) {
      this.$router.push(`/photo/${photo.pcode}`);
    }
  },
  computed: {
    ...mapGetters(["photos"]),
    size() {
      const width = window.innerWidth;
      let size = 80;
      if (width < 1000) {
        size = 60;
      } else if (width < 700) {
        size = 40;
      } else {
        size = 20;
      }
      return size;
    }
  },
  mounted() {
    this.$store.dispatch("getPhotosAction");
  }
};
</script>

<style scoped>
#photos {
  margin: 15px 5px !important;
}
</style>
