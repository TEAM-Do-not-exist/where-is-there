<template>
  <v-col cols="12" md="6" xs="12">
    <v-card height="400">
      <VueDaumMap
        :appKey="appKey"
        :center.sync="foundCenter"
        :level.sync="level"
        :mapTypeId="mapTypeId"
        :libraries="libraries"
        @load="onLoad"
        @click="test"
        style="width: 100%; height: 100%;"
      />
    </v-card>
  </v-col>
</template>

<script>
// 해야할 일: 비동기 작업으로 인해 제대로 된 좌표를 가져오지 못하는 점 보완하기
import axios from "axios";
import { mapGetters } from "vuex";
import VueDaumMap from "vue-daum-map";

export default {
  name: "PhotoKakaoMap",
  components: { VueDaumMap: VueDaumMap },
  data: () => ({
    appKey: process.env.VUE_APP_KAKAO_API_KEY,
    center: { lat: 33.450701, lng: 126.570667 },
    level: 3,
    mapTypeId: VueDaumMap.MapTypeId.NORMAL,
    libraries: [],
    map: null
  }),
  methods: {
    onLoad(map) {
      this.map = map;
    },
    test() {
      const kakaoUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
      const encoded = encodeURI(this.photo.pname);
      axios
        .get(kakaoUrl + `?query=${encoded}`, {
          headers: {
            Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_API_KEY}`
          }
        })
        .then(r => {
          const { data } = r;
          const documents = data.documents;
          if (documents.length !== 0) {
            console.log(data);
          } else {
            // console.log(this.photo);
          }
        })
        .catch();
    }
  },
  computed: {
    ...mapGetters(["photo"]),
    foundCenter: () => {
      const kakaoUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
      const encoded = encodeURI(this.photo.pname);
      const center = { lat: 33.450701, lng: 126.570667 };
      axios
        .get(kakaoUrl + `?query=${encoded}`, {
          headers: {
            Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_API_KEY}`
          }
        })
        .then(r => {
          const { data } = r;
          const documents = data.documents;
          if (documents.length !== 0) {
            center.lng = documents[0].x;
            center.lat = documents[0].y;
          }
        })
        .catch();
      return center;
    }
  }
};
</script>

<style></style>
