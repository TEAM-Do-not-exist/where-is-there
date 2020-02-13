<template>
  <div>
    <!-- store and place name -->
    <v-card-title
      class="headline font-weight-bold mb-4 text-wrap"
      @click="onOpen"
    >#거기어디니: {{ photo[0].pname }}</v-card-title>
    <v-card-subtitle class="subtitle-1 font-weight-thin">#어디에있니: {{ photo[0].pplace }}</v-card-subtitle>
    <v-card-text>더 자세한 정보를 확인하려면 장소 이름을 클릭해주세요.</v-card-text>
    <v-divider class="my-4"></v-divider>
    <!-- original link -->
    <v-chip class="ma-2">
      <a
        :href="photo[0].purl"
        target="_blank"
        class="caption font-weight-thin white--text"
      >{{ photo[0].purl }}</a>
    </v-chip>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "PhotoInfo",
  methods: {
    onOpen() {
      const query = this.photo[0].pplace + this.photo[0].pname;
      const url = "https://map.naver.com/v5/search/";
      window.open(url + query, "_blank");
    }
  },
  computed: {
    ...mapGetters(["photo"])
  }
};
</script>

<style></style>
