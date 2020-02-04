<template>
  <div class="overflow-hidden">
    <div class="text-center mb-2">
      <v-btn text color="deep-purple" @click="showNav = !showNav">Pagination</v-btn>
    </div>

    <v-bottom-navigation v-model="activeBtn" :input-value="showNav" color="indigo">
      <v-btn @click="onPageChange(-1)">
        <span>Prev</span>
        <v-icon>mdi-minus</v-icon>
      </v-btn>

      <v-btn disabled>
        <span>{{page}}</span>
      </v-btn>

      <v-btn @click="onPageChange(1)">
        <span>Next</span>
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
    pageLength: Number
  },
  methods: {
    onPageChange(num) {
      const changedPage = this.page + num;
      if (0 < changedPage && changedPage <= this.pageLength) {
        return this.$emit("onPageChange", changedPage);
      }
    }
  }
};
</script>

<style>
</style>
