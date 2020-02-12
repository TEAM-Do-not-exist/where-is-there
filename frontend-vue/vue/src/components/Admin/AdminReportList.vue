<template>
  <v-container>
    <v-card
      v-for="(report, idx) in reports"
      :key="idx"
      :outlined="true"
      class="mx-auto"
    >
      <!-- simple info of report -->
      <v-card-text>
        <p class="title text--white">ID: {{ report.rid }}</p>
        <div>Reason:</div>
        <p class="subtitle-2 text--primary">{{ report.rreason }}</p>
      </v-card-text>

      <!-- check report button -->
      <v-card-actions>
        <AdminReportDialog :report="report" />
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import AdminReportDialog from "./AdminReportDialog";

export default {
  name: "AdminReportList",
  components: {
    AdminReportDialog: AdminReportDialog
  },
  props: {
    reports: Array
  }
};
</script>

<style></style>
