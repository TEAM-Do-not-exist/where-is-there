<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn
          text
          class="font-weight-black"
          color="orange darken-1"
          v-on="on"
          :disabled="!isAdmin"
        >상세보기</v-btn>
      </template>

      <!-- dialog information -->
      <v-card>
        <!-- head -->
        <v-card-title>
          <span class="headline font-weight-black">신고 정보</span>
        </v-card-title>

        <v-divider></v-divider>

        <!-- body -->
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="6">
                <v-text-field
                  persistent-hint
                  label="신고된 사진 번호*"
                  hint="클릭하면 사진 페이지로 넘어갑니다."
                  :value="report.rcode"
                  @click="checkPhoto"
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field label="신고한 유저*" :value="report.rid" disabled></v-text-field>
              </v-col>
              <v-textarea label="사유" :value="report.rreason" counter disabled></v-textarea>
            </v-row>
          </v-container>
        </v-card-text>

        <v-divider></v-divider>

        <!-- footer -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="font-weight-black" color="blue darken-1 " text @click="dialog = false">닫기</v-btn>
          <v-btn class="font-weight-black" color="blue darken-1" text @click="complete">완료</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "AdminReportDialog",
  data: () => ({
    dialog: false
  }),
  props: {
    report: Object
  },
  methods: {
    checkPhoto() {
      this.$router.push({
        name: "photo",
        params: { pcode: this.report.rcode.toString() }
      });
    },
    complete() {
      if (this.isAdmin === true) {
        const url = `${process.env.VUE_APP_SPRING_URL}/api/report/delete/${this.report.rnum}`;
        axios.delete(url).then(() => {
          this.$store.dispatch("getReportsAction");
        });
      }
      this.dialog = false;
    }
  },
  computed: {
    ...mapGetters(["getUser"]),
    isAdmin() {
      return this.getUser === process.env.VUE_APP_ADMIN_EMAIL ? true : false;
    }
  }
};
</script>

<style></style>
