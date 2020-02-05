<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <!-- modal open button -->
      <template v-slot:activator="{ on }">
        <v-btn color="blue" text v-on="on">수정하기</v-btn>
      </template>

      <!-- modal information -->
      <v-card>
        <!-- head -->
        <v-card-title>
          <span class="headline"> 정보 수정</span>
        </v-card-title>

        <!-- body -->
        <v-card-text>
          <v-container>
            <v-row>
              <!-- select place name -->
              <v-col cols="12" sm="12" md="12">
                <v-text-field
                  v-model="inputId"
                  label="ID"
                  required
                ></v-text-field>
              </v-col>
      
              <!-- check url -->
              <v-col cols="12">
                <v-text-field
                  :rules="[v => !!v || 'Image url is required']"
                  v-model="inputPw"
                  label="pw*"
                  required
                ></v-text-field>
              </v-col>

              <!-- check source -->
              <v-col cols="12">
                <v-text-field
                  :rules="[v => !!v || 'Source url is required']"
                  v-model="inputName"
                  label="name*"
                  required
                ></v-text-field>
              </v-col>
                 <v-col cols="12">
                <v-text-field
                  :rules="[v => !!v || 'Source url is required']"
                  v-model="inputPhone"
                  label="phone*"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <!-- footer -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn :disabled="!this.selectedSource" color="red darken-1" text @click="deny(item)">Deny</v-btn>
          <v-btn :disabled="!validated" color="blue darken-1" text @click="save(item)">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    selectedPlace: null,
    selectedHashtag: null,
    selectedUrl: "",
    selectedSource: "",
    dialog: false
  }),
  props: {
    item: Object
  },
  methods: {
    save(item) {
      const basicUrl = "http://127.0.0.1:8090/";
      const addUrl = "api/member/update";
      const data = {
        pw: this.inputPw,
        name: this.inputName,
        phone: this.inputPhone, 
        purl: this.selectedSource // 이쪽이 출처에대한 정보
      };
      axios
        .post(basicUrl + addUrl, data)
        .then(r => {
          if (r.data.regmsg === "입력했습니다") {
            this.$emit("onInsert", item);
          }
        })
        .catch();
      return (this.dialog = false);
    }
  },
  computed: {
    validated() {
      return (
        this.selectedPlace &&
        this.selectedHashtag &&
        this.selectedUrl &&
        this.selectedSource
      );
    }
  }
};
</script>

<style></style>
