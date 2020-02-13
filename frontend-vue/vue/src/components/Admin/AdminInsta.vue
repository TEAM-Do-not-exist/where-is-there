<template>
  <v-col cols="12" md="6" sm="12">
    <v-alert
      colored-border
      border="top"
      color="blue"
      elevation="2"
      class="text-center font-weight-black"
    >인스타그램 데이터 가져오기</v-alert>

    <!-- silder for dicide crawling size -->
    <v-subheader class="pl-0">데이터 요청량</v-subheader>
    <v-slider
      v-model="slider"
      :disabled="!isAdmin"
      :rules="[v => v > 0 || '요청량은 0보다 커야합니다.']"
      :thumb-size="24"
      :step="10"
      thumb-label
    ></v-slider>

    <!-- info getter btn -->
    <v-row>
      <v-col v-for="(name, idx) in btnNames" :key="idx" md="6" sm="12">
        <v-btn
          dark
          class="font-weight-black"
          color="blue"
          :block="true"
          :disabled="!isAdmin || loading || slider < 10"
          @click="getInstaItems(name)"
        >{{ name.name }}</v-btn>
      </v-col>
    </v-row>

    <!-- if loading, show loading circle -->
    <div v-if="loading" class="text-center my-12">
      <AdminLoadingCircle />
    </div>

    <!-- if loading complete, show infomations -->
    <div v-else>
      <AdminInstaList :src="pagedSrc" @onInsert="onInsert" />
    </div>

    <!-- pagination -->
    <AdminPagination :page="page" :length="length" @onPageChange="onPageChange" />
  </v-col>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import AdminInstaList from "./AdminInstaList";
import AdminPagination from "./AdminPagination";
import AdminLoadingCircle from "./AdminLoadingCircle";

export default {
  name: "AdminInsta",
  components: {
    AdminInstaList: AdminInstaList,
    AdminPagination: AdminPagination,
    AdminLoadingCircle: AdminLoadingCircle
  },
  data: () => ({
    btnNames: {
      1: {
        name: "#국내여행",
        url: "instagram",
        target: "tour"
      },
      2: {
        name: "오늘뭐먹지",
        url: "instagram",
        target: "eat"
      }
    },
    loading: false,
    pagedSrc: {},
    src: {},
    page: 1,
    length: 1,
    slider: 10
  }),
  methods: {
    pagination() {
      this.length = 1;
      this.pagedSrc = {};
      const srcEntries = Object.entries(this.src);
      let idx = 0;
      for (const [key, val] of srcEntries) {
        // check index of data
        const pagedSrcEntries = Object.entries(this.pagedSrc);
        if (pagedSrcEntries.length < 10 && idx >= 10 * (this.page - 1)) {
          this.pagedSrc[key] = val;
        }
        idx++;
        // check total length
        if (idx % 10 === 0) {
          this.length++;
        }
      }
    },
    getInstaItems(name) {
      if (this.slider > 0) {
        this.page = 1;
        this.length = 1;

        if (this.getUser === process.env.VUE_APP_ADMIN_EMAIL) {
          this.loading = true;
          const url = `${process.env.VUE_APP_PYTHON_URL}/instagram/?length=${this.slider}&target=${name.target}`;
          axios
            .get(url)
            .then(r => {
              this.src = r.data;
              const keys = Object.keys(this.src);
              for (const key of keys) {
                if (key % 10 === 0) {
                  this.length++;
                }
              }
              this.pagination();
              this.loading = false;
            })
            .catch(() => {
              this.loading = false;
            });
        }
      }
    },
    onInsert(item) {
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
    onPageChange(newPage) {
      this.page = newPage;
      this.pagination();
    }
  },
  computed: {
    ...mapGetters(["getUser"]),
    isAdmin() {
      return this.getUser === process.env.VUE_APP_ADMIN_EMAIL ? true : false;
    }
  },
  mounted() {
    this.$store.dispatch("getUserAction");
  }
};
</script>

<style></style>
