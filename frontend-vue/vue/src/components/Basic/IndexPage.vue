<template>
  <div>
    <p class="font-weight-bold" v-resize-text="resizeText">
      당신을 위한 '거기'
    </p>
    <v-divider></v-divider>
    <masonry :cols="mansonryCols" :gutters="mansonryGutters">
      <div v-for="(photo, idx) in photos" :key="idx" id="photos">
        <!-- photo image, if click it, show detail -->
        <v-img
          :src="photo.psource"
          class="grey lighten-2"
          @click="selectPhoto(photo)"
        >
          <template v-slot:placeholder>
            <v-row class="fill-height ma-0" align="center" justify="center">
              <v-progress-circular
                indeterminate
                color="grey lighten-5"
              ></v-progress-circular>
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
    ...mapGetters(["photos"])
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
