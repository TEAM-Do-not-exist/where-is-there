<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="600px"
      overlay-color="white"
      overlay-opacity="1"
    >
      <!-- modal open button -->
      <template v-slot:activator="{ on }">
        <v-btn color="blue" text v-on="on">Check Information</v-btn>
      </template>

      <!-- modal information -->
      <v-card>
        <!-- head -->
        <v-card-title>
          <span class="headline">Image Information</span>
        </v-card-title>
        <!-- carousel images, when click image, image will set selected image -->
        <v-carousel hide-delimiter-background delimiter-icon="mdi-minus">
          <v-carousel-item
            v-for="(url, idx) in item.psource"
            :key="idx"
            :src="url"
            @click="setUrl(url)"
            reverse-transition="fade-transition"
            transition="fade-transition"
          ></v-carousel-item>
        </v-carousel>

        <!-- body -->
        <v-card-text>
          <v-container>
            <v-row>
              <!-- select place name -->
              <v-col cols="12" sm="12" md="6">
                <v-autocomplete
                  :items="item.pplace_pname"
                  :rules="[v => !!v || 'Place is required']"
                  v-model="selectedPlace"
                  label="Place Name*"
                  required
                ></v-autocomplete>
              </v-col>
              <!-- select store or important tag name -->
              <v-col cols="12" sm="12" md="6">
                <v-autocomplete
                  :items="item.pplace_pname"
                  :rules="[v => !!v || 'Hashtag is required']"
                  v-model="selectedHashtag"
                  label="Hashtag Name*"
                  required
                ></v-autocomplete>
              </v-col>
              <!-- check url -->
              <v-col cols="12">
                <v-text-field
                  :rules="[v => !!v || 'Image url is required']"
                  v-model="selectedUrl"
                  label="Selected Image Url*"
                  required
                ></v-text-field>
              </v-col>
              <!-- check source -->
              <v-col cols="12">
                <v-text-field
                  :rules="[v => !!v || 'Source url is required']"
                  v-model="selectedSource"
                  label="Source Url*"
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
          <v-btn :disabled="!validated" color="blue darken-1" text @click="ax(item)">Save</v-btn>
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
    setUrl(url) {
      return (this.selectedUrl = url), (this.selectedSource = this.item.purl);
    },
    ax(item) {
      const basicUrl = "http://127.0.0.1:8090/";
      const addUrl = "api/photo/insert";
      const data = {
        pname: this.selectedHashtag,
        pplace: this.selectedPlace,
        psource: this.selectedUrl, // 이쪽이 사진에 대한 정보
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
    },
    deny(item) {
      const basicUrl = "http://127.0.0.1:8090/";
      const addUrl = "api/photocheck/insert";
      const data = {
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
