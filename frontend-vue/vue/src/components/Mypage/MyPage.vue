<template>
  <div id="app">
    <v-app id="inspire">
      <!-- <v-card-title  >
              <h1>My Page</h1>
      </v-card-title>-->
      <v-container>
        <v-card app>
          <v-img
            class="white--text align-end"
            width="100%"
            aspect-ratio=5
            src="../../../public/logo.png"
          ></v-img>

          <v-card-text class="pb-0">Name: {{item.name}} </v-card-text>
          <v-card-text class="pb-0">ID: {{item.id}}</v-card-text>
          <v-card-text class="pb-0">PW: </v-card-text>
          <v-card-text class="pb-0">e-mail:{{item.email}}</v-card-text>
          <v-card-text class="pb-0">phone: {{item.phone}}</v-card-text>

          <v-card-actions>
            <v-btn color="orange" text>수정하기</v-btn>
            <MyInfoUpdateModal :item="item" @onInsert="onInsert" />
          </v-card-actions>
        </v-card>

        <v-card app>
          <v-card-actions>
            <v-btn color="yellow" text @click="isLike = !isLike; isComment = false">내 좋아요</v-btn>
            <v-btn color="yellow" text @click="isComment = !isComment; isLike =false">내 댓글</v-btn>
          </v-card-actions>
          <MyFavorite v-if="isLike" app  style="overflow-x:auto"></MyFavorite>
          <MyComment v-if="isComment" app  style="overflow-x:auto"></MyComment>
        </v-card>
      </v-container>
    </v-app>
  </div>
</template>


<script>
  import MyFavorite from "./MyFavorite";
  import MyComment from "./MyComment";
  import axios from "axios";
  import MyInfoUpdateModal from "./MyInfoUpdateModal"; 

export default {
  components: {
    MyFavorite,
    MyComment,
    MyInfoUpdateModal
  },
  data() {
    return {
      isLike: false,
      isComment: false,
      item:{}
    };
  },
  methods:{
    showmyinfo(){
      const basicUrl = "http://127.0.0.1:8090/";
      const addUrl = "api/member/selectOneId/";
      // const cid = this.cid;
      const id = 123123; // 현재 아이디 박아놓은 상태.
      axios
        .get(basicUrl+addUrl+id)
        .then(response => (this.item = response.data['resvalue']))
        .catch(() => {
               this.errored = true;
         })
        . finally(() => (this.loading = false));
    },
    updateinfo(){
      this.$emit("updateInfo");
    }
  },
  mounted(){
    this.showmyinfo(); 
  }
};
</script>
