<template>
  <v-row>
    <v-col cols="12" md="6" sm="12">
      <v-img :src="checkUrl(photo[1].purl)" class="grey lighten-2" max-height="300">
        <template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
          </v-row>
        </template>
      </v-img>
    </v-col>

    <v-col cols="12" md="6" sm="12">
      <p class="title font-weight-bold my-2">#거기어디니:</p>
      <p class="display-1 font-weight-bold" @click="onOpen">{{ photo[1].pplace_pname[1] }}</p>
      <p class="subtitle-1 my-2">#어디에있니:</p>
      <p class="title">{{ place }}</p>
      <p class="title">{{ address }}</p>
      <v-divider />
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "RecommandList",
  props: {
    photo: Array,
    place: String,
    address: String
  },
  methods: {
    onOpen() {
      let query = "";
      if (this.address === undefined) {
        query = this.photo[1].pplace_pname[1];
      } else {
        query = this.address;
      }
      const url = "https://map.naver.com/v5/search/";
      window.open(url + query, "_blank");
    },
    checkUrl(url) {
      const error =
        "https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80";
      if (
        url.startsWith("https://") === true ||
        url.startsWith("http://") === true
      ) {
        return url;
      } else {
        return error;
      }
    }
  }
};
</script>

<style scoped>
v-img {
  width: 100%;
  height: 100%;
}
</style>
