<template>
  <v-col cols="12" md="6" sm="12">
    <v-alert
      colored-border
      border="top"
      color="red"
      elevation="2"
      class="text-center font-weight-black"
      >유저 신고 게시판</v-alert
    >

    <!-- report list -->
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

<style></style>
