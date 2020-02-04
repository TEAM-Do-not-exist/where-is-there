<template>
  <div id="app">
    <v-app id="inspire">
      <v-card app>
        <v-container>
          <v-row dense>
<!-- 
            <v-col v-for="(item, i) in items" :key="i" cols="12">
              <v-card :color="item.color" dark>
                <div class="d-flex flex-no-wrap">
                  <v-avatar class="ma-3" size="50" tile>
                    <v-img :src="item.src"></v-img>
                  </v-avatar>
                  <div>
                    <v-card-subtitle v-text="item.content"></v-card-subtitle>
                  </div>
                </div>
              </v-card>
            </v-col> -->
    
            <!-- <div>{{list}}</div> -->

           <table class="table text-center table-bordered table-hover">
                            <tr v-for="comment in list" :key="comment.ccode">
                                <!-- <td v-html="board.cid"></td> -->
                                <td v-html="comment.ccode"></td>
                                <td v-html="comment.content"></td>
                                 <td v-html="comment.ctime"></td>

                            </tr>

                        </table>
          </v-row>
        </v-container>
      </v-card>
    </v-app>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "comment-list",

  data: () => ({
    list:[],
    // // ccid : this.list.ccid,
    // // ccode : this.list.ccode,
    // // ccontent: this.list.ccontent,
    // // ctime: this.list.ctime,
    // items:[
    //   {
    //   color : "black",
    //   src : "",
    //   title : this.list.ccode,
    //   content : this.list.ccontent
    //   }

    // ]
    // items: [
    //   {
    //     color: "black",
    //     src: "https://cdn.vuetifyjs.com/images/cards/foster.jpg",
    //     // title: '사진 바로가기',
    //     content:
    //       "내가 쓴 댓글이 들어갈거임. 사진이나 댓글 누르면 그 사진페이지로 가도록 합시다다ㅏ아아아아"
    //   },
    //   {
    //     color: "black",
    //     src: "https://cdn.vuetifyjs.com/images/cards/halcyon.png",
    //     // title: '사진 바로가기',
    //     content: "내가 쓴 댓글이 들어갈거임"
    //   },
    //   {
    //     color: "black",
    //     src: "https://cdn.vuetifyjs.com/images/cards/foster.jpg",
    //     // title: '사진 바로가기',
    //     content: "내가 쓴 댓글이 들어갈거임"
    //   },
    //   {
    //     color: "black",
    //     src: "https://cdn.vuetifyjs.com/images/cards/halcyon.png",
    //     // title: '사진 바로가기',
    //     content: "Ellie Goulding"
    //   },
    //   {
    //     color: "black",
    //     src: "https://cdn.vuetifyjs.com/images/cards/foster.jpg",
    //     // title: '사진 바로가기',
    //     content: "Foster the People"
    //   },
    //   {
    //     color: "black",
    //     src: "https://cdn.vuetifyjs.com/images/cards/halcyon.png",
    //     // title: '사진 바로가기',
    //     content: "Ellie Goulding"
    //   }
    // ]

  }),

  methods:{
     showcomment() {
      const basicUrl = "http://127.0.0.1:8090/";
      const addUrl = "api/comment/selectMyList/";
      // const cid = this.cid;
      const cid = 123123; // 현재 아이디 박아놓은 상태.
      axios
        .get(basicUrl+addUrl+cid)
        .then(response => (this.list = response.data['resvalue']))
        .catch(() => {
               this.errored = true;
         })
        . finally(() => (this.loading = false));
    }
  },
  mounted(){
    this.showcomment();
  }
};
</script>
