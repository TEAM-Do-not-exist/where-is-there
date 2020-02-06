<template>
  <v-col cols="12" md="6" sm="12">
    <v-alert
      border="top"
      colored-border
      color="red"
      elevation="2"
      class="text-center font-weight-bold"
    >User report board</v-alert>
    <AdminReportList :reports="reports" />
  </v-col>
</template>

<script>
import { mapGetters } from "vuex";
import AdminReportList from "./AdminReportList";

export default {
  name: "AdminReport",
  components: {
    AdminReportList: AdminReportList
  },
  computed: {
    ...mapGetters(["reports"])
  },
  mounted() {
    this.$store.dispatch("getReportsAction");
  }
};
</script>

<style>
</style>
