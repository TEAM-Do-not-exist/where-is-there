<template>
  <v-container>
    <!-- map data -->
    <v-card height="600">
      <vue-daum-map
        :appKey="appKey"
        :center.sync="center"
        :level.sync="level"
        :mapTypeId="mapTypeId"
        @load="onLoad"
        style="width: 100%; height: 100%;"
      ></vue-daum-map>
    </v-card>

    <!-- reco data -->
    <!-- if there is not data -->
    <div v-if="tours.size + mangos.size === 0" @click="toBack">
      <p class="title font-weight-thin my-2">관련 데이터가 없습니다.</p>
      <p class="subtitle-1 font-weight-black my-2">클릭하시면 이전 페이지로 돌아갑니다.</p>
    </div>

    <div v-else>
      <div class="justify-space-between my-1">
        <p class="subtitle-1 font-weight-thin my-4">더 자세한 위치는 하단 각 장소의 이름을 클릭해주세요!</p>
        <p class="subtitle-1 fonr-weight-black my-4" @click="toBack">돌아가려면 클릭해주세요!</p>
      </div>

      <!-- TourAPI -->
      <div v-for="(tour, idx) in tours" :key="`tour-${idx}`" :id="`tour-${idx}`">
        <RecommandList :photo="tour" :place="photo[0].pplace" />
      </div>

      <!-- mango -->
      <div v-for="(mango, idx) in mangos" :key="`mango-${idx}`" :id="`mango-${idx}`">
        <RecommandList :photo="mango" :place="photo[0].pplace" />
      </div>
    </div>
  </v-container>
</template>

<script>
import axios from "axios";
import VueDaumMap from "vue-daum-map";
import { mapGetters } from "vuex";
import RecommandList from "./RecommandList";

export default {
  name: "Recommand",
  components: {
    VueDaumMap: VueDaumMap,
    RecommandList: RecommandList
  },
  data: () => {
    return {
      tours: new Map(),
      mangos: new Map(),
      appKey: process.env.VUE_APP_KAKAO_API_KEY,
      level: 5,
      mapTypeId: VueDaumMap.MapTypeId.NORMAL,
      center: {},
      map: null,
      noData: false
    };
  },
  props: {
    pcode: String
  },
  methods: {
    loadPlaces() {
      const place = this.photo[0].pplace;
      const tourUrl = `${process.env.VUE_APP_PYTHON_URL}/tour-api/?keyword=${place}`;
      const mangoUrl = `${process.env.VUE_APP_PYTHON_URL}/mango/?keyword=${place}`;

      // TourAPI 3.0
      axios.get(tourUrl).then(r => {
        this.tours = new Map(Object.entries(r.data));
        this.onMarkers("tour");
      });

      // mangoplate
      axios.get(mangoUrl).then(r => {
        this.mangos = new Map(Object.entries(r.data));
        this.onMarkers("mango");
      });
    },
    onLoad(map) {
      this.map = map;
    },
    onMarkers(target) {
      this.noData = false;
      const kakaoUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
      const headers = {
        Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_API_KEY}`
      };
      let encoded = "";
      if (target === "tour" && this.tours.length !== 0) {
        for (const tour of this.tours) {
          encoded = encodeURI(tour[1].pplace_pname[1]);
          axios
            .get(kakaoUrl + `?query=${encoded}`, { headers: headers })
            .then(r => {
              this.onMarkerDetail(r, tour, "tour");
            });
        }
      } else if (target === "mango" && this.mangos.length !== 0) {
        for (const mango of this.mangos) {
          encoded = encodeURI(mango[1].pplace_pname[1]);
          axios
            .get(kakaoUrl + `?query=${encoded}`, { headers: headers })
            .then(r => {
              this.onMarkerDetail(r, mango, "mango");
            });
        }
      } else {
        this.noData = true;
      }
    },
    onMarkerDetail(r, part, target) {
      const kakao = window.kakao;
      const { data } = r;
      const documents = data.documents;
      if (documents.length !== 0) {
        const position = new kakao.maps.LatLng(documents[0].y, documents[0].x);
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position
        });
        marker.setMap(this.map);
        kakao.maps.event.addListener(marker, "click", function() {
          const lat = marker.getPosition().Ha;
          const lng = marker.getPosition().Ga;
          marker.getMap().setCenter(new kakao.maps.LatLng(lat, lng));
          if (target === "tour") {
            document.location.href = `#tour-${part[0]}`;
          } else {
            document.location.href = `#mango-${part[0]}`;
          }
        });
      }
    },
    toBack() {
      this.$router.push({ name: "Photo", params: { pcode: this.pcode } });
    }
  },
  computed: {
    ...mapGetters(["photo"])
  },
  mounted() {
    this.$store.dispatch("getPhotoAction", this.pcode);
    setTimeout(this.loadPlaces, 500);
    this.center = this.photo[1];
  }
};
</script>

<style></style>
