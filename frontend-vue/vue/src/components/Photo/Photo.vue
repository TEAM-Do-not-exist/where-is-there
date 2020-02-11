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
        <!-- update needed: check id is admin id -->
        <div class="my-5 d-flex" v-if="getAdmin">
          <PhotoDeleteButton />
          <PhotoUpdateDialog />
        </div>
      </v-col>
      <PhotoKakaoMap />
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
  data: () => ({
    id: "admin"
  }),
  props: {
    pcode: String
  },
  methods: {
    onModifyComment(code, id, content) {
      const url = "http://localhost:8090/api/comment/update";
      const data = {
        ccode: code,
        cid: id,
        content: content
      };
      axios.put(url, data);
    },
    deleteComment(code, id) {
      const url = `${process.env.VUE_APP_SPRING_URL}/api/comment/delete/${code}/${id}`;
      axios.delete(url).then(() => {
        this.onInput();
      });
    }
  },
  computed: {
    ...mapGetters(["photo", "getUser"]),
    getAdmin() {
      return this.getUser === "admin" ? true : false;
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
