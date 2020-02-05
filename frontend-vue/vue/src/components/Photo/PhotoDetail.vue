<template>
  <v-container>
    <!-- top vue -->
    <v-parallax dark :src="photo[0].psource">
      <v-row align="center" justify="center"></v-row>
    </v-parallax>

    <v-divider :dark="true" class="my-3"></v-divider>

    <!-- information vue -->
    <v-row>
      <v-col cols="12" md="6" xs="12">
        <v-card-title class="headline font-weight-bold mb-4 text-wrap">#거기어디니: {{ photo[0].pname }}</v-card-title>
        <v-card-subtitle class="subtitle-1 font-weight-thin">#어디에있니: {{ photo[0].pplace }}</v-card-subtitle>
        <v-chip class="ma-2">
          <a
            :href="photo[0].purl"
            class="caption font-weight-thin white--text"
            target="_blank"
          >{{ photo[0].purl }}</a>
        </v-chip>
      </v-col>
      <PhotoDetailKakaoMap />
      <v-btn class="ma-2" outlined fab color="red darken-1" @click="favorite" v-if="!clicked">
        <v-icon>mdi-heart-outline</v-icon>
      </v-btn>
      <v-btn class="ma-2" outlined fab color="red darken-1" @click="favorite" v-if="clicked">
        <v-icon>mdi-heart</v-icon>
      </v-btn>
      <v-btn class="ma-2" outlined fab color="yellow darken-1" @click="alert">
        <v-icon>mdi-alert-octagon</v-icon>
      </v-btn>

      <!-- comment vue -->
    </v-row>
    <div style="height: 100px"></div>
    <v-divider :dark="true" class="my-3"></v-divider>
    <PhotoDetailInput :photo="photo[0]" @onInput="onInput" />
    <div v-for="(comment, idx) in comments" :key="idx">
      <PhotoDetailComment :comment="comment" />
    </div>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import PhotoDetailKakaoMap from "./PhotoDetailKakaoMap";
import PhotoDetailInput from "./PhotoDetailInput";
import PhotoDetailComment from "./PhotoDetailComment";

export default {
  name: "PhotoDetail",
  components: {
    PhotoDetailKakaoMap: PhotoDetailKakaoMap,
    PhotoDetailComment: PhotoDetailComment,
    PhotoDetailInput: PhotoDetailInput
  },
  data: () => ({
    comments: [],
    clicked: false
  }),
  props: {
    pcode: String
  },
  methods: {
    favorite() {
      const data = { fcode: this.pcode, fid: "admin@admin.com" };
      let url = "http://localhost:8090/api/favorite/";
      if (this.clicked === false) {
        url += "insert";
        axios
          .post(url, data)
          .then(() => {
            this.clicked = !this.clicked;
          })
          .catch();
      } else {
        url += `delete/${this.pcode}/admin@admin.com`;
        axios
          .delete(url)
          .then(() => {
            this.clicked = !this.clicked;
          })
          .catch();
      }
    },
    onInput() {
      const commnetUrl = `http://localhost:8090/api/comment/selectPhotoList/${this.pcode}`;
      axios
        .get(commnetUrl)
        .then(r => {
          const { data } = r;
          this.comments = data.resvalue;
        })
        .catch();
    }
  },
  computed: {
    ...mapGetters(["photo"])
  },
  mounted() {
    this.$store.dispatch("getPhotoAction", this.pcode);
    const checkUrl = `http://localhost:8090/api/favorite/selectMyList/admin@admin.com`;
    axios
      .get(checkUrl)
      .then(r => {
        const { data } = r;
        const check = data.resvalue.filter(
          val => val.fcode === parseInt(this.pcode)
        );
        if (check.length === 1) {
          this.clicked = true;
        }
      })
      .catch();
    const commnetUrl = `http://localhost:8090/api/comment/selectPhotoList/${this.pcode}`;
    axios
      .get(commnetUrl)
      .then(r => {
        const { data } = r;
        this.comments = data.resvalue;
      })
      .catch();
  }
};
</script>

<style>
.caption {
  text-decoration: none;
}
</style>
