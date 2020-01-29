<template>
  <v-container>
    <masonry :cols="mansonryCols" :gutters="mansonryGutters">
      <div v-for="(photo, idx) in photos" :key="idx">
        <v-img :src="photo.psource" class="grey lighten-2"></v-img>
      </div>
    </masonry>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "IndexPage",
  components: {},
  data: () => ({
    mansonryCols: { default: 4, 1000: 3, 700: 2, 400: 1 },
    mansonryGutters: { default: "30px", 700: "15px" }
  }),
  methods: {},
  computed: {
    ...mapGetters(["photos"])
  },
  mounted() {
    this.$store.dispatch("getPhotosAction");
  }
};
</script>

<style scoped>
div {
  margin: 5px 2px !important;
}
</style>
