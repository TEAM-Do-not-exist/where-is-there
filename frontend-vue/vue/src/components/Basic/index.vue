<template>
<v-container>
  <masonry :cols="mansonryCols" :gutters="mansonryGutters">
    <div v-for="(photo, idx) in photos" :key="idx" class="contentsitems">
      <v-img :src="photo.psource" class="grey lighten-2" @click="showDetail(photo)">
        <template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
          </v-row>
        </template>
      </v-img>
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
    mansonryGutters: { default: "30px", 700: "15px" },
    openModal: false
  }),
  methods: {
    showDetail(photo) {
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
.contentsitems {
  margin: 5px 3px !important;
}
</style>
