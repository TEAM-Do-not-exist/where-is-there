<template>
  <v-container>
    <v-row no-gutters :block="true">
      <AdminCrawling />
      <AdminReport />
    </v-row>
  </v-container>
</template>

<script>
import AdminCrawling from "./AdminCrawling.vue";
import AdminReport from "./AdminReport.vue";

export default {
  components: {
    AdminCrawling: AdminCrawling,
    AdminReport: AdminReport
  },
  data: () => ({})
};
</script>

<style></style>
