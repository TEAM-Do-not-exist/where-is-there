<template>
  <div id="app">
    <v-app id="inspire">
      <v-card app>
        <v-container>
          <v-row dense>
            <v-col v-for="(item, i) in list" :key="i" cols="12">
                <v-card dark>
                <div class="d-flex flex-no-wrap">
                  <v-avatar class="ma-3" size="100" tile>
                    <v-img :src= item.psource></v-img>
                  </v-avatar>
                  <div>
                    <v-card-text>{{item.ctime}}</v-card-text>
                    <v-card-subtitle v-text="item.content"></v-card-subtitle>
                  </div>
                </div>
              </v-card>
            </v-col>

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
    list:[]
  }),

  methods:{
     showcomment() {
      const basicUrl = "http://127.0.0.1:8090/";
      const addUrl = "api/comment/selectMyList/";
      // const cid = this.cid;
      const cid = "123@123"; // 현재 아이디 박아놓은 상태.
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
