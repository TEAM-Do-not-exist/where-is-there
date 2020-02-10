<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <!-- modal open button -->
      <template v-slot:activator="{ on }">
        <v-btn color="orange darken-1" text v-on="on">Check Report</v-btn>
      </template>

      <!-- modal information -->
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
              <v-col cols="12" sm="6" md="6">
                <v-text-field
                  label="신고된 사진 번호*"
                  :value="report.rcode"
                  required
                  disabled
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-text-field
                  label="신고자 이름*"
                  :value="report.rid"
                  disabled
                ></v-text-field>
              </v-col>
              <v-textarea
                counter
                label="Text"
                :value="report.rreason"
                disabled
              ></v-textarea>
            </v-row>
          </v-container>
        </v-card-text>

        <v-divider></v-divider>

        <!-- footer -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false"
            >Close</v-btn
          >
          <v-btn color="blue darken-1" text @click="done">Done</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  data: () => ({
    dialog: false
  }),
  props: {
    report: Object
  },
  methods: {
    done() {
      if (this.getAdmin === true) {
        const url = `${process.env.VUE_APP_SPRING_URL}/api/report/delete/${this.report.rnum}`;
        const num = this.report.rnum;
        axios
          .delete(url)
          .then(() => {
            this.$emit("deleteReport", num);
          })
          .catch();
      }
      this.dialog = false;
    }
  },
  computed: {
    ...mapGetters(["getAdmin"])
  }
};
</script>

<style></style>
