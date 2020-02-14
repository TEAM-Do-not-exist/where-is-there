<template>
  <v-col cols="12" md="6" xs="12">
    <v-card height="300">
      <v-card-subtitle class="subtitle-1 font-weight-thin"
        >주변 정보를 알고 싶다면 지도를 터치해주세요!</v-card-subtitle
      >
      <vue-daum-map
        style="width: 100%; height: 100%;"
        :appKey="appKey"
        :center.sync="photo[1]"
        :level.sync="level"
        :mapTypeId="mapTypeId"
        @load="onLoad"
        @click="toRecommand"
      ></vue-daum-map>
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
        kakao.maps.event.addListener(marker, "click", function() {
          const lat = marker.getPosition().Ha;
          const lng = marker.getPosition().Ga;
          marker.getMap().setCenter(new kakao.maps.LatLng(lat, lng));
        });
        this.marker = true;
      }
    },
    toRecommand() {
      this.$router.push(`/photo/${this.photo[0].pcode}/recommands`);
    }
  },
  computed: {
    ...mapGetters(["photo"])
  },
  mounted() {
    setTimeout(this.onMarker, 500);
  }
};
</script>

<style></style>
