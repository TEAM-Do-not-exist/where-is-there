<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn class="ma-2" outlined fab color="yellow darken-1" v-on="on">
          <v-icon>mdi-alert-octagon</v-icon>
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="headline" primary-title>Report</v-card-title>

        <v-card-text>
          <!-- autofield -->
          <v-row>
            <v-col cols="12" sm="6" md="6">
              <v-text-field label="신고할 사진 번호*" :value="pcode" required disabled></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-text-field
                label="신고자 이름*"
                hint="신고자의 아이디가 전송됩니다."
                :value="id"
                persistent-hint
                disabled
              ></v-text-field>
            </v-col>
          </v-row>

          <!-- input form -->
          <v-textarea counter label="Text" :rules="rules" :value="value"></v-textarea>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="warning" text @click="reportPhoto">Report</v-btn>
          <v-btn color="primary" text @click="dialog = false">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: "PhotoReportModal",
  data: () => ({
    dialog: false,
    id: "admin@admin.com",
    rules: [v => v.length <= 300 || "Max 300 characters"],
    value: ""
  }),
  props: {
    pcode: String
  },
  methods: {
    reportPhoto() {
      // 신고에 대한 C로직 추가하기
      console.log("???");
    }
  }
};
</script>

<style>
</style>
