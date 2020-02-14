<template>
  <div id="app">
    <v-app id="inspire">
      <v-container>
        <!-- logo  -->
        <v-card app>
          <v-img
            class="white--text align-end"
            width="100%"
            aspect-ratio="5"
            src="../../../public/logo.png"
          ></v-img>
          <!-- My Information -->
          <div>
            <table class="pb-0">
              <tr class="pb-1">
                <td>아이디 :</td>
                <td></td>
                <td>{{ item.email }}</td>
              </tr>
              <tr class="pb-1">
                <td>닉네임 :</td>
                <td></td>
                <td>{{ item.nickname }}</td>
              </tr>
              <tr class="pb-1">
                <td>전화번호 :</td>
                <td></td>
                <td>{{ item.phone }}</td>
              </tr>
            </table>
          </div>
          <!-- Update My Information -->
          <v-card-actions>
            <v-btn text>
              <MyInfoUpdateModal :item="item" />
            </v-btn>
          </v-card-actions>
        </v-card>
        <!-- My Favorite Page || My Comment Page-->
        <v-card app>
          <v-card-actions>
            <v-btn
              color="yellow"
              text
              @click="
                isLike = !isLike;
                isComment = false;
              "
              >내 좋아요</v-btn
            >
            <v-btn
              color="yellow"
              text
              @click="
                isComment = !isComment;
                isLike = false;
              "
              >내 댓글</v-btn
            >
          </v-card-actions>
          <MyFavorite v-if="isLike" app style="overflow-x:auto"></MyFavorite>
          <MyComment v-if="isComment" app style="overflow-x:auto"></MyComment>
        </v-card>
      </v-container>
    </v-app>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import MyComment from "./MyComment";
import MyFavorite from "./MyFavorite";
import MyInfoUpdateModal from "./MyInfoUpdateModal";

export default {
  name: "MyPage",
  components: {
    MyFavorite,
    MyComment,
    MyInfoUpdateModal
  },
  data() {
    return {
      isLike: false,
      isComment: false,
      item: {}
    };
  },
  methods: {
    showmyinfo() {
      const basicUrl = process.env.VUE_APP_SPRING_URL;
      const addUrl = "/api/member/selectOneToken/";
      const token = this.token;
      axios
        .get(basicUrl + addUrl + token)
        .then(response => (this.item = response.data["resvalue"]))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    updateinfo() {
      this.$emit("updateInfo");
    }
  },
  computed: {
    ...mapGetters(["token"]),
    ...mapGetters(["nickname"])
  },
  mounted() {
    this.showmyinfo();
  }
};
</script>

<style scoped>
.pb-0 {
  font-size: 18px;
  color: rgba(255, 255, 255, 0.7);
  margin: 20px;
}
.pb-1 {
  height: 50px;
}
</style>
