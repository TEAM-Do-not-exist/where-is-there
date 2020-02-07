<template>
  <v-container>
    <v-row :block="true">
      <!-- crawling info check div -->
      <AdminInsta />
      <!-- user report check div -->
      <AdminReport />
    </v-row>
  </v-container>
</template>

<script>
import AdminInsta from "./AdminInsta";
import AdminReport from "./AdminReport";

export default {
  name: "Admin",
  components: {
    AdminInsta: AdminInsta,
    AdminReport: AdminReport
  }
};
</script>

<style></style>
