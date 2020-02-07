<template>
  <div id="my-favorite">
      <v-card app background-color="#0000">
        <v-container fluid>
          <v-row dense>
            <!-- <v-col v-for="(item, i) in list" :key="i" cols="4"> -->
            <v-col v-for="(photo, i) in list" :key="i" cols="6">
              <v-card>
                <v-img
                  :src = photo.psource
                  class="white--text align-end"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  height="200px"
                  @click="showDetail(photo)"
                >
                </v-img>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-card>

  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  data: () => ({
    list:[],
  }),
  props:{
    token : String
  },
  methods:{
     showfavorite() {
      const basicUrl = "http://127.0.0.1:8090/";
      const addUrl = "api/favorite/selectMyList/";
      // const cid = this.cid;
      const fid = this.token; // 현재 아이디 박아놓은 상태.
      axios
        .get(basicUrl+addUrl+fid)
        .then(response => (this.list = response.data['resvalue']))
        .catch(() => {
               this.errored = true;
         })
        . finally(() => (this.loading = false));
    },
    delfavorite(){
      this.is_show = !this.is_show; // #2, #3
    },
      showDetail(photo) {
      this.$router.push(`/photo/${photo.fcode}`);
    }

  },  
  computed: {
    ...mapGetters(["token"])
  },
  mounted(){
    this.showfavorite();

  }

};
</script>
