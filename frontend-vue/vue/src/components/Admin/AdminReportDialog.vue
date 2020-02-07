<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="orange darken-1" text v-on="on">Check Report</v-btn>
      </template>

      <!-- dialog information -->
      <v-card>
        <!-- head -->
        <v-card-title>
          <span class="headline">Report Information</span>
        </v-card-title>

        <v-divider></v-divider>

        <!-- body -->
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="6">
                <v-text-field label="Reported Photo Number*" :value="report.rcode" disabled></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field label="Reporter*" :value="report.rid" disabled></v-text-field>
              </v-col>
              <v-textarea label="Text" :value="report.rreason" counter disabled></v-textarea>
            </v-row>
          </v-container>
        </v-card-text>

        <v-divider></v-divider>

        <!-- footer -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="complete">Complete</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  name: "AdminReportDialog",
  data: () => ({
    dialog: false
  }),
  props: {
    report: Object
  },
  methods: {
    complete() {
      const url = `${process.env.VUE_APP_SPRING_URL}/api/report/delete/${this.report.rnum}`;
      axios
        .delete(url)
        .then(() => {
          this.dialog = false;
          this.$store.dispatch("getReportsAction");
        })
        .catch();
    }
  }
};
</script>

<style></style>
