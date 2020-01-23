<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="blue" text v-on="on">Check Information</v-btn>
      </template>
      <v-card>
        <!-- head -->
        <v-card-title>
          <span class="headline">Image Information</span>
        </v-card-title>
        <!-- carousel images, when click image, image will set selected image -->
        <v-carousel hide-delimiter-background delimiter-icon="mdi-minus">
          <v-carousel-item
            v-for="(url, idx) in item.purl"
            :key="idx"
            :src="url"
            @click="setValidation(url)"
            reverse-transition="fade-transition"
            transition="fade-transition"
          ></v-carousel-item>
        </v-carousel>
        <v-card-text>
          <v-container>
            <v-row>
              <!-- select place name -->
              <v-col cols="12" sm="12" md="6">
                <v-autocomplete
                  :items="item.pplace_pname"
                  label="Place Name*"
                  hint="add place name from given texts"
                  persistent-hint
                  required
                ></v-autocomplete>
              </v-col>
              <!-- select store or important tag name -->
              <v-col cols="12" sm="12" md="6">
                <v-autocomplete
                  :items="item.pplace_pname"
                  label="Important Name*"
                  hint="add important hashtag from given texts"
                  persistent-hint
                  required
                ></v-autocomplete>
              </v-col>
              <!-- check url -->
              <v-col cols="12">
                <v-text-field
                  v-model="selectedUrl"
                  label="Selected Image Url*"
                  required
                ></v-text-field>
              </v-col>
              <!-- check source -->
              <v-col cols="12">
                <v-text-field
                  v-model="item.psource"
                  label="Source Url*"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false"
            >Close</v-btn
          >
          <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  data: () => ({
    validateUrl: "",
    selectedUrl: "",
    validateSource: "",
    selectedSource: "",
    itemList: [],
    dialog: false
  }),
  props: {
    item: Object
  },
  methods: {
    setValidation(url) {
      return (this.selectedUrl = this.validateUrl = url);
    }
  }
};
</script>

<style></style>
