<template>
  <v-container>
    <!-- photo vue -->
    <v-parallax dark :src="photo[0].psource"></v-parallax>
    <v-divider :dark="true" class="my-3"></v-divider>

    <v-row>
      <v-col cols="12" md="6" xs="12">
        <!-- info vue -->
        <PhotoInfo />
        <!-- like buttons -->
        <div class="d-inline-flex">
          <PhotoLikeButton :pcode="pcode" />
          <PhotoReportDialog :pcode="pcode" />
        </div>
        <!-- only access for admin -->
        <div class="my-5 d-flex" v-if="isAdmin">
          <PhotoDeleteButton />
          <PhotoUpdateDialog />
        </div>
      </v-col>
      <PhotoKakaoMap v-if="isSearched" />
      <v-col v-else cols="12" md="6" xs="12">
        <v-card width="100%" height="300" class="d-flex justify-center align-center">
          <v-card-subtitle class="subtitle-1 font-weight-thin">정확한 검색 정보를 찾을 수 없습니다.</v-card-subtitle>
        </v-card>
      </v-col>
    </v-row>
    <div style="height: 100px"></div>

    <!-- comment vue -->
    <v-divider :dark="true" class="my-3"></v-divider>
    <Comments :ccode="pcode" @onModifyComment="onModifyComment" @deleteComment="deleteComment" />
  </v-container>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import PhotoInfo from "./PhotoInfo";
import PhotoLikeButton from "./PhotoLikeButton";
import PhotoReportDialog from "./PhotoReportDialog";
import PhotoDeleteButton from "./PhotoDeleteButton";
import PhotoUpdateDialog from "./PhotoUpdateDialog";
import PhotoKakaoMap from "./PhotoKakaoMap";
import Comments from "../Comment/Comments";

export default {
  name: "Photo",
  components: {
    PhotoInfo: PhotoInfo,
    PhotoLikeButton: PhotoLikeButton,
    PhotoReportDialog: PhotoReportDialog,
    PhotoDeleteButton: PhotoDeleteButton,
    PhotoUpdateDialog: PhotoUpdateDialog,
    PhotoKakaoMap: PhotoKakaoMap,
    Comments: Comments
  },
  props: {
    pcode: String
  },
  methods: {
    onModifyComment(code, content) {
      const url = `${process.env.VUE_APP_SPRING_URL}/api/comment/update`;
      const data = {
        ccode: code,
        cid: this.getUser,
        content: content
      };
      axios.put(url, data);
    },
    deleteComment(code) {
      const url = `${process.env.VUE_APP_SPRING_URL}/api/comment/delete/${code}/${this.getUser}`;
      axios.delete(url).then(() => {
        this.onInput();
      });
    }
  },
  computed: {
    ...mapGetters(["photo", "getUser"]),
    isAdmin() {
      return this.getUser === process.env.VUE_APP_ADMIN_EMAIL ? true : false;
    },
    isSearched() {
      return JSON.stringify(this.photo[1]) !==
        JSON.stringify({ lat: 33.450701, lng: 126.570667 })
        ? true
        : false;
    }
  },
  mounted() {
    this.$store.dispatch("getPhotoAction", this.pcode);
    this.$store.dispatch("getUserAction");
  }
};
</script>

<style>
.caption {
  text-decoration: none;
}
</style>
