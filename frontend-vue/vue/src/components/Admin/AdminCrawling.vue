<template>
  <v-col cols="12" md="6" sm="12">
    <v-alert
      border="top"
      colored-border
      color="blue"
      elevation="2"
      class="text-center font-weight-bold"
    >Get crawling images</v-alert>
    <!-- 정보를 요청할 v-btn -->
    <v-row no-gutters>
      <v-col v-for="(name, idx) in btnNames" :key="idx" md="6" sm="12">
        <v-btn dark @click="ax(name)" :block="true" color="blue">
          {{
          name.name
          }}
        </v-btn>
      </v-col>
    </v-row>

    <!-- 로딩 중일 때는 로당바가 표시 -->
    <div v-if="loading" class="text-center my-12">
      <AdminLoadingCircle />
    </div>

    <!-- 정보를 보여주는 공간 -->
    <div v-else>
      <AdminItemList :src="src" @onInsert="filterItems" />
    </div>

    <!-- pagination -->
    <!-- <div class="text-center">
        <v-container>
          <v-row justify="center">
            <v-col cols="8">
              <v-container class="max-width">
                <v-pagination
                  v-model="page"
                  class="my-4"
                  color="blue"
                  :length="length"
                ></v-pagination>
              </v-container>
            </v-col>
          </v-row>
        </v-container>
    </div>-->
  </v-col>
</template>

<script>
import axios from "axios";
import AdminItemList from "./AdminItemList.vue";
import AdminLoadingCircle from "./AdminLoadingCircle.vue";

export default {
  components: {
    AdminItemList: AdminItemList,
    AdminLoadingCircle: AdminLoadingCircle
  },
  data: () => ({
    btnNames: {
      1: {
        name: "travelholic",
        url: "instagram",
        target: "tour",
        length: 10
      },
      2: {
        name: "greedeat",
        url: "instagram",
        target: "eat",
        length: 10
      }
    },
    src: {},
    loading: false
    // pagedSrc: {},
    // page: 0,
    // length: 0
  }),
  methods: {
    ax(name) {
      this.loading = true;

      const basicUrl = "http://127.0.0.1:8000/";
      const basicLength = "/?length=" + name.length;
      const basicTarget = "&target=" + name.target;
      const url = basicUrl + name.url + basicLength + basicTarget;
      axios
        .get(url)
        .then(r => {
          this.src = r.data;
          this.loading = false;
          // this.page = 1;
          // this.length = Object.keys(this.src).length / 10;
        })
        .catch(() => {
          this.loading = false;
        });
    },
    filterItems(item) {
      const filterredSrc = {};
      const entries = Object.entries(this.src);
      for (const [key, val] of entries) {
        const intKey = parseInt(key, 10);
        if (intKey !== item.pcode) {
          filterredSrc[intKey] = val;
        }
      }
      this.src = filterredSrc;
    }
  }
};
</script>

<style></style>
