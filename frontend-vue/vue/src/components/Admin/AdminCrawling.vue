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
      <AdminItemList :src="pagedSrc" @onInsert="filterItems" />
    </div>

    <!-- pagination -->
    <div v-if="src">
      <AdminPagination :page="page" :pageLength="pageLength" @onPageChange="onPageChange" />
    </div>
  </v-col>
</template>

<script>
import axios from "axios";
import AdminItemList from "./AdminItemList.vue";
import AdminLoadingCircle from "./AdminLoadingCircle.vue";
import AdminPagination from "./AdminPagination";

export default {
  components: {
    AdminItemList: AdminItemList,
    AdminLoadingCircle: AdminLoadingCircle,
    AdminPagination: AdminPagination
  },
  data: () => ({
    btnNames: {
      1: {
        name: "travelholic",
        url: "instagram",
        target: "tour",
        length: 100
      },
      2: {
        name: "greedeat",
        url: "instagram",
        target: "eat",
        length: 100
      }
    },
    src: {},
    pagedSrc: {},
    loading: false,
    page: 1,
    pageLength: 1
  }),
  methods: {
    pagination() {
      this.pageLength = 1;
      this.pagedSrc = {};
      const entries = Object.entries(this.src);
      let idx = 0;
      for (const [key, val] of entries) {
        if (this.page - 1 <= idx % 10 && idx % 10 < this.page) {
          this.pagedSrc[key] = val;
        }
        idx++;
        if (idx % 10 === 0) {
          this.pageLength++;
        }
      }
    },
    ax(name) {
      this.page = 1;
      this.pageLength = 1;
      this.loading = true;

      const basicUrl = "http://127.0.0.1:8000/";
      const basicLength = "/?length=" + name.length;
      const basicTarget = "&target=" + name.target;
      const url = basicUrl + name.url + basicLength + basicTarget;
      axios
        .get(url)
        .then(r => {
          this.src = r.data;
          const keys = Object.keys(this.src);
          for (const key of keys) {
            if (key % 10 === 0) {
              this.pageLength++;
            }
          }

          this.pagination();
          this.loading = false;
        })
        .catch(() => {
          this.loading = false;
        });
    },
    filterItems(item) {
      let filterredSrc = {};
      const entries = Object.entries(this.src);
      for (const [key, val] of entries) {
        const intKey = parseInt(key, 10);
        if (intKey !== item.pcode) {
          filterredSrc[intKey] = val;
        }
      }
      this.src = filterredSrc;
      this.pagination();
    },
    onPageChange(changedPage) {
      this.page = changedPage;
      this.pagination();
    }
  }
};
</script>

<style></style>
