<template>
  <v-col cols="12" md="6" xs="12">
    <v-card height="300">
      <v-card-subtitle class="subtitle-1 font-weight-thin"
        >자세한 위치를 알고 싶다면 하단의 지도를 터치해주세요!</v-card-subtitle
      >
      <vue-daum-map
        :appKey="appKey"
        :center.sync="photo[1]"
        :level.sync="level"
        :mapTypeId="mapTypeId"
        @load="onLoad"
        @click="onMarker(map)"
        style="width: 100%; height: 100%;"
      >
      </vue-daum-map>
    </v-card>
  </v-col>
</template>

<script>
import { mapGetters } from "vuex";
import VueDaumMap from "vue-daum-map";

export default {
  name: "PhotoKakaoMap",
  components: { VueDaumMap: VueDaumMap },
  data: () => ({
    appKey: process.env.VUE_APP_KAKAO_API_KEY,
    center: {},
    level: 3,
    mapTypeId: VueDaumMap.MapTypeId.NORMAL,
    map: null,
    marker: false
  }),
  methods: {
    onLoad(map) {
      this.map = map;
    },
    onMarker() {
      if (this.marker === false) {
        const kakao = window.kakao;
        const position = new kakao.maps.LatLng(
          this.photo[1].lat,
          this.photo[1].lng
        );
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position
        });
        marker.setMap(this.map);
        this.marker = true;
      }
    }
  },
  computed: {
    ...mapGetters(["photo"])
  },
  mounted() {
    setTimeout(this.onMarker, 1000);
  }
};
</script>

<style></style>
