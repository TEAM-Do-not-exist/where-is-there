<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn
          class="ma-2"
          outlined
          color="yellow darken-1"
          v-on="on"
          v-if="getUser"
        >
          <v-icon>mdi-alert</v-icon>
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="headline" primary-title>Report</v-card-title>
        <v-divider></v-divider>

        <v-card-text>
          <!-- autofield -->
          <v-row>
            <v-col cols="6">
              <v-text-field
                :value="pcode"
                label="신고할 사진 번호*"
                disabled
              ></v-text-field>
            </v-col>
            <v-col cols="6">
              <v-text-field
                :value="getUser"
                :hint="hint"
                label="신고자 이름*"
                persistent-hint
                disabled
              ></v-text-field>
            </v-col>
          </v-row>

          <!-- input reason -->
          <v-textarea
            v-model="value"
            :rules="[v => v.length <= 300 || 'Max 300 characters']"
            label="신고 사유"
            counter
          ></v-textarea>
        </v-card-text>
        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text color="warning" :disabled="!getUser" @click="reportPhoto"
            >Report</v-btn
          >
          <v-btn color="primary" text @click="dialog = false">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "PhotoReportDialog",
  data: () => ({
    dialog: false,
    value: ""
  }),
  props: {
    pcode: String
  },
  methods: {
    reportPhoto() {
      const url = `${process.env.VUE_APP_SPRING_URL}/api/report/insert`;
      const data = {
        rid: this.getUser,
        rcode: parseInt(this.pcode),
        rreason: this.value
      };
      axios.post(url, data).then(() => {
        this.value = "";
        this.dialog = false;
      });
    }
  },
  computed: {
    ...mapGetters(["getUser"]),
    hint() {
      if (this.getUser === undefined) {
        return "로그인이 필요한 기능입니다.";
      } else {
        return "사용자의 이메일이 전송됩니다.";
      }
    }
  },
  mounted() {
    this.$store.dispatch("getUserAction");
  }
};
</script>

<style></style>
