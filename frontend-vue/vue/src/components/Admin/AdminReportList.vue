<template>
  <v-container>
    <v-card v-for="(report, idx) in reports" :key="idx" :outlined="true" class="mx-auto">
      <v-card-text>
        <p class="title text--white">ID: {{ report.rid }}</p>
        <div>Reason:</div>
        <p class="subtitle-2 text--primary">{{ report.rreason }}</p>
      </v-card-text>
      <v-card-actions>
        <AdminReportItemModal :report="report" @deleteReport="deleteReport" />
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import AdminReportItemModal from "./AdminReportItemModal";

export default {
  name: "AdminReportList",
  data: () => ({}),
  components: {
    AdminReportItemModal: AdminReportItemModal
  },
  methods: {
    deleteReport(num) {
      this.reports = this.reports.filter(report => report.rnum !== num);
    }
  },
  props: {
    reports: Array
  }
};
</script>

<style>
</style>
