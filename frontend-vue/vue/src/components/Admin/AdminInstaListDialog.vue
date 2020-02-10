<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <!-- dialog open button -->
      <template v-slot:activator="{ on }">
        <v-btn color="blue" text v-on="on">Check Information</v-btn>
      </template>

      <!-- dialog information -->
      <v-card>
        <!-- head -->
        <v-card-title>
          <span class="headline">Image Information</span>
        </v-card-title>
        <!-- carousel images, when click image, image will set selected image -->
        <v-carousel hide-delimiter-background delimiter-icon="mdi-minus">
          <v-carousel-item
            reverse-transition="fade-transition"
            transition="fade-transition"
            v-for="(url, idx) in item.psource"
            :key="idx"
            :src="url"
            @click="setUrl(url)"
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
                  :rules="[v => !!v || 'Place name is required']"
                  v-model="place"
                  label="Place Name*"
                  required
                ></v-autocomplete>
              </v-col>
              <!-- select store name -->
              <v-col cols="12" sm="12" md="6">
                <v-autocomplete
                  :items="item.pplace_pname"
                  :rules="[v => !!v || 'Store name is required']"
                  v-model="store"
                  label="Store Name*"
                  required
                ></v-autocomplete>
              </v-col>
              <!-- check image url -->
              <v-col cols="12">
                <v-text-field
                  :rules="[v => !!v || 'Image url is required']"
                  v-model="source"
                  label="Image Url*"
                  required
                ></v-text-field>
              </v-col>
              <!-- check original link -->
              <v-col cols="12">
                <v-text-field
                  :rules="[v => !!v || 'Original url is required']"
                  v-model="url"
                  label="Original Url*"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <!-- footer -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false"
            >Close</v-btn
          >
          <v-btn
            :disabled="!this.source"
            color="red darken-1"
            text
            @click="denyItem(item)"
            >Deny</v-btn
          >
          <v-btn
            :disabled="!validated"
            color="blue darken-1"
            text
            @click="insertItem(item)"
            >Save</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "AdminInstaItemsDialog",
  data: () => ({
    place: null,
    store: null,
    source: "",
    url: "",
    dialog: false
  }),
  props: {
    item: Object
  },
  methods: {
    setUrl(url) {
      (this.source = url), (this.url = this.item.purl); // url: original link, source: image url
    },
    sendPost(url, data, item) {
      axios.post(url, data).then(() => {
        this.$emit("onInsert", item);
      });
    },
    insertItem(item) {
      if (this.getAdmin === true) {
        const url = `${process.env.VUE_APP_SPRING_URL}/api/photo/insert`;
        const data = {
          pname: this.store,
          pplace: this.place,
          psource: this.source,
          purl: this.url
        };
        this.sendPost(url, data, item);
      }
      this.dialog = false;
    },
    denyItem(item) {
      if (this.getAdmin === true) {
        const url = `${process.env.VUE_APP_SPRING_URL}/api/photocheck/insert`;
        const data = { purl: this.url };
        this.sendPost(url, data, item);
      }
      this.dialog = false;
    }
  },
  computed: {
    ...mapGetters(["getAdmin"]),
    validated() {
      return this.place && this.store && this.source && this.url;
    }
  }
};
</script>

<style></style>
