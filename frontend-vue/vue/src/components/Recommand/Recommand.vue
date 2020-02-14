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
    <div v-if="mangos.size === 0" @click="toBack">
      <p class="title font-weight-thin my-2">관련 데이터가 없습니다.</p>
      <p class="subtitle-1 font-weight-black my-2">클릭하시면 이전 페이지로 돌아갑니다.</p>
    </div>

    <div v-else>
      <div class="justify-space-between my-1">
        <p class="subtitle-1 font-weight-thin my-4">더 자세한 위치는 하단 각 장소의 이름을 클릭해주세요!</p>
        <p class="subtitle-1 font-weight-black my-4" @click="toBack">돌아가려면 클릭해주세요!</p>
      </div>

      <!-- mango -->
      <div v-for="(mango, idx) in mangos" :key="`mango-${idx}`" :id="`mango-${idx}`">
        <RecommandList :photo="mango" :place="photo[0].pplace" :address="mangoAddresses[idx]" />
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
      mangos: new Map(),
      mangoAddresses: [],
      appKey: process.env.VUE_APP_KAKAO_API_KEY,
      level: 6,
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
      const mangoUrl = `${process.env.VUE_APP_PYTHON_URL}/mango/?keyword=${place}`;

      // mangoplate
      axios.get(mangoUrl).then(r => {
        this.mangos = new Map(Object.entries(r.data));
        this.onMarkers();
      });
    },
    onLoad(map) {
      this.map = map;
    },
    onMarkers() {
      this.noData = false;
      const kakaoUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
      const headers = {
        Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_API_KEY}`
      };
      let encoded = "";
      if (this.mangos.length !== 0) {
        for (const mango of this.mangos) {
          encoded = encodeURI(mango[1].pplace_pname[1]);
          axios
            .get(kakaoUrl + `?query=${encoded}`, { headers: headers })
            .then(r => {
              this.onMangoMarker(r, mango);
            });
        }
      } else {
        this.noData = true;
      }
    },
    onMangoMarker(r, part) {
      const kakao = window.kakao;
      const documents = r.data.documents;
      if (documents.length !== 0) {
        const realAddress = part[1].pplace_pname[0].split(" ").slice(1);
        for (const document of documents) {
          const address = document.address_name.split(" ").slice(1);
          if (JSON.stringify(address) === JSON.stringify(realAddress)) {
            const markerAddress = document.address_name;
            this.mangoAddresses.push(markerAddress);
            const position = new kakao.maps.LatLng(document.y, document.x);
            const marker = new kakao.maps.Marker({
              map: this.map,
              position: position
            });
            marker.setMap(this.map);

            kakao.maps.event.addListener(marker, "click", function() {
              const lat = marker.getPosition().Ha;
              const lng = marker.getPosition().Ga;
              marker.getMap().setCenter(new kakao.maps.LatLng(lat, lng));
              window.document.location.href = `#mango-${part[0]}`;
            });
          }
        }
      }
    },
    toBack() {
      this.$router.push({ name: "photo", params: { pcode: this.pcode } });
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
