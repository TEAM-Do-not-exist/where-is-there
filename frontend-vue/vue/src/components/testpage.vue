<template>
  <v-app>
    <v-col cols="12" md="6" sm="12">
      <!-- 정보를 요청할 v-btn -->
      <v-row no-gutters>
        <v-col v-for="(name, idx) in btnNames" :key="idx" md="6" sm="12">
          <v-btn dark @click="ax(name)" :block="true" color="blue">{{
            name.name
          }}</v-btn>
        </v-col>
      </v-row>

      <!-- 정보를 보여주는 공간 -->
      <v-container>
        <v-card v-for="item in src" :key="item.pcode" class="mx-auto">
          <v-img height="400px" position="center center" :src="item.purl[0]">
            <template v-slot:placeholder>
              <v-row class="fill-height ma-0" align="center" justify="center">
                <v-progress-circular
                  indeterminate
                  color="blue-grey darken-4"
                ></v-progress-circular>
              </v-row>
            </template>
          </v-img>
          <v-card-actions>
            <v-btn color="blue" text>Check Information</v-btn>
          </v-card-actions>
        </v-card>
      </v-container>
    </v-col>
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    btnNames: {
      1: {
        name: "travelholic",
        url: "instagram",
        target: "tour",
        length: 100
      },
      2: {
        name: "greedeat",
        url: "instagram",
        target: "eat",
        length: 100
      }
    },
    src: null
  }),
  methods: {
    ax(name) {
      const basicUrl = "http://127.0.0.1:8000/";
      const basicLength = "/?length=" + name.length;
      const basicTarget = "&target=" + name.target;
      const url = basicUrl + name.url + basicLength + basicTarget;
      axios
        .get(url)
        .then(r => {
          this.src = r.data;
        })
        .catch();
    }
  }
};
</script>

<style></style>
