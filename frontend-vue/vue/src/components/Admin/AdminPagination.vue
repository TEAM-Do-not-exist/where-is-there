<template>
  <div class="overflow-hidden">
    <!-- title -->
    <div class="text-center mb-2">
      <v-btn text class="font-weight-black" color="deep-purple" @click="showNav = !showNav">전체 페이지</v-btn>
    </div>

    <!-- move page -->
    <v-bottom-navigation v-model="activeBtn" :input-value="showNav" color="indigo">
      <v-btn @click="onPageChange(-1)">
        <v-icon>mdi-minus</v-icon>
      </v-btn>

      <!-- now page -->
      <v-btn disabled>
        <span>{{ page }}</span>
      </v-btn>

      <v-btn @click="onPageChange(1)">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </div>
</template>

<script>
export default {
  data: () => ({
    activeBtn: 1,
    showNav: true
  }),
  props: {
    page: Number,
    length: Number
  },
  methods: {
    onPageChange(n) {
      const newPage = this.page + n;
      if (0 < newPage && newPage <= this.length) {
        this.$emit("onPageChange", newPage);
      }
    }
  }
};
</script>

<style></style>
