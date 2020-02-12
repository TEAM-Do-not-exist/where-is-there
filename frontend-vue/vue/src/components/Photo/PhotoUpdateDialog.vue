<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on }">
      <v-btn
        :disabled="!isAdmin"
        color="yellow darken-1"
        class="ma-2"
        v-on="on"
        outlined
        dark
        >update</v-btn
      >
    </template>

    <v-card>
      <v-card-title>
        <span class="headline">Photo Update</span>
      </v-card-title>
      <v-divider></v-divider>

      <v-card-text>
        <v-container>
          <v-img :src="photo[0].psource"></v-img>
          <v-row>
            <v-col cols="6">
              <v-text-field
                v-model="place"
                :hint="`기존 지역 이름은 ${photo[0].pplace}입니다.`"
                label="Place*"
                persistent-hint
                required
              ></v-text-field>
            </v-col>
            <v-col cols="6">
              <v-text-field
                v-model="name"
                :hint="`기존 장소 이름은 ${photo[0].pname}입니다.`"
                label="Store*"
                persistent-hint
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Source*"
                :value="photo[0].psource"
                disabled
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="URL*"
                :value="photo[0].purl"
                disabled
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-divider></v-divider>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          color="warning darken-1"
          text
          :disabled="!validated || !isAdmin"
          @click="updatePhoto"
          >Update</v-btn
        >
        <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "PhotoUpdateDialog",
  data: () => ({
    dialog: false,
    place: "",
    name: ""
  }),
  methods: {
    updatePhoto() {
      if (this.isAdmin === true) {
        const url = `${process.env.VUE_APP_SPRING_URL}/api/photo/update`;
        const data = {
          pcode: this.photo[0].pcode,
          pplace: this.place,
          purl: this.photo[0].purl,
          pname: this.name,
          psource: this.photo[0].psource
        };
        this.dialog = false;
        axios.put(url, data).then(() => {
          this.$store.dispatch("getPhotoAction", this.photo[0].pcode);
        });
      }
    }
  },
  computed: {
    ...mapGetters(["photo", "getUser"]),
    validated() {
      return this.place !== "" && this.name !== "";
    },
    isAdmin() {
      return this.getUser === process.env.VUE_APP_ADMIN_EMAIL ? true : false;
    }
  }
};
</script>

<style></style>
