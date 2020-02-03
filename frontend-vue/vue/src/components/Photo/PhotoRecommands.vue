<template>
  <v-container>
    <!-- 지도 데이터 -->
    <v-col cols="12" sm="0">
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
    </v-col>

    <!-- 추천 데이터 -->
    <v-col cols="12">
      <!-- TourAPI -->
      <div v-if="tours.length !== 0">
        <v-row v-for="(tour, idx) in tours" :key="idx" :id="`tour-${idx}`">
          <!-- 사진 뷰 -->
          <v-col cols="12" md="6" sm="12">
            <v-img :src="tour[1].purl" class="grey lighten-2" max-height="300">
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                </v-row>
              </template>
            </v-img>
          </v-col>

          <!-- 설명 뷰 -->
          <v-col cols="12" md="6" sm="12">
            <p class="title font-weight-bold my-2">#거기어디니:</p>
            <p
              class="display-1 font-weight-bold"
              @click="onOpen(tour[1].pplace_pname[1])"
            >{{ tour[1].pplace_pname[1] }}</p>
            <p class="subtitle-1 my-2">#어디에있니:</p>
            <p class="title">{{ photo[0].pplace }}</p>
            <v-divider />
          </v-col>
        </v-row>
      </div>

      <!-- mango -->
      <div v-if="mangos.length !== 0">
        <v-row v-for="(mango, idx) in mangos" :key="idx" :id="`mango-${idx}`">
          <!-- 사진 뷰 -->
          <v-col cols="12" md="6" sm="12">
            <v-img :src="mango[1].psource" class="grey lighten-2" max-height="300">
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                </v-row>
              </template>
            </v-img>
          </v-col>

          <!-- 설명 뷰 -->
          <v-col cols="12" md="6" sm="12">
            <p class="title font-weight-bold my-2">#거기어디니:</p>
            <p
              class="display-1 font-weight-bold"
              @click="onOpen(mango[1].pplace_pname[1])"
            >{{ mango[1].pplace_pname[1] }}</p>
            <p class="subtitle-1 my-2">#어디에있니:</p>
            <p class="title">{{ photo[0].pplace }}</p>
            <v-divider />
          </v-col>
        </v-row>
      </div>

      <!-- 결과가 없다면 -->
      <div v-else>
        <p class="title font-weight-bold my-2">관련 데이터가 없습니다.</p>
      </div>
    </v-col>
  </v-container>
</template>

<script>
import axios from "axios";
import VueDaumMap from "vue-daum-map";
import { mapGetters } from "vuex";

export default {
  name: "PhotoRecommands",
  components: {
    VueDaumMap: VueDaumMap
  },
  data: () => {
    return {
      tours: new Map(),
      mangos: new Map(),
      appKey: process.env.VUE_APP_KAKAO_API_KEY,
      level: 6,
      mapTypeId: VueDaumMap.MapTypeId.NORMAL,
      center: {},
      map: null
    };
  },
  props: {
    pcode: String
  },
  methods: {
    loadPlaces() {
      const place = this.photo[0].pplace;
      const tourUrl = `http://localhost:8000/tour-api/?keyword=${place}`;
      const mangoUrl = `http://localhost:8000/mango/?keyword=${place}`;

      // TourAPI 3.0
      axios
        .get(tourUrl)
        .then(r => {
          this.tours = new Map(Object.entries(r.data));
          this.onMarkers("tour");
        })
        .catch();

      // mangoplate
      axios
        .get(mangoUrl)
        .then(r => {
          this.mangos = new Map(Object.entries(r.data));
          this.onMarkers("mango");
        })
        .catch();
    },
    onLoad(map) {
      this.map = map;
    },
    onMarkers(target) {
      const kakaoUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
      let encoded = "";
      if (target === "tour" && this.tours.length !== 0) {
        for (const tour of this.tours) {
          encoded = encodeURI(tour[1].pplace_pname[1]);
          axios
            .get(kakaoUrl + `?query=${encoded}`, {
              headers: {
                Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_API_KEY}`
              }
            })
            .then(r => {
              const kakao = window.kakao;
              const { data } = r;
              const documents = data.documents;
              if (documents.length !== 0) {
                const position = new kakao.maps.LatLng(
                  documents[0].y,
                  documents[0].x
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
                  // 위치 이동하기 달기
                  // document.location.href = `tour-${tour[0]}`;
                });
              } else {
                this.tours.delete(tour[0]);
              }
            })
            .catch();
        }
      } else if (target === "mango" && this.mangos.length !== 0) {
        for (const mango of this.mangos) {
          encoded = encodeURI(mango[1].pplace_pname[1]);
          axios
            .get(kakaoUrl + `?query=${encoded}`, {
              headers: {
                Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_API_KEY}`
              }
            })
            .then(r => {
              const kakao = window.kakao;
              const { data } = r;
              const documents = data.documents;
              if (documents.length !== 0) {
                const position = new kakao.maps.LatLng(
                  documents[0].y,
                  documents[0].x
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
              } else {
                this.tours.delete(mango[0]);
              }
            })
            .catch();
        }
      } else {
        return;
      }
    },
    onOpen(query) {
      const url = "https://map.naver.com/v5/search/";
      window.open(url + query, "_blank");
    }
  },
  computed: {
    ...mapGetters(["photo"])
  },
  mounted() {
    this.$store.dispatch("getPhotoAction", this.pcode);
    setTimeout(this.loadPlaces, 300);
    this.center = this.photo[1];
  }
};
</script>

<style></style>
