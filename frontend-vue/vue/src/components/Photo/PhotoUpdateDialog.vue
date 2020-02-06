<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on }">
      <v-btn class="ma-2" outlined dark v-on="on" color="yellow darken-1">update</v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="headline">Photo update</span>
      </v-card-title>
      <v-divider></v-divider>
      <v-card-text>
        <v-container>
          <v-img :src="photo.psource"></v-img>
          <v-divider class="my-4"></v-divider>
          <v-row>
            <v-col cols="12" sm="6" md="6">
              <v-text-field
                label="Place*"
                :hint="`기존 지역 이름은 ${photo.pplace}입니다.`"
                v-model="place"
                persistent-hint
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-text-field
                label="hashtag*"
                :hint="`기존 태그 이름은 ${photo.pname}입니다.`"
                v-model="name"
                persistent-hint
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field label="Source*" required :value="photo.psource" disabled></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field label="URL*" required :value="photo.purl" disabled></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
        <v-btn color="blue darken-1" text @click="updatePhoto">Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "PhotoUpdateDialog",
  data: () => ({
    dialog: false,
    place: "",
    name: ""
  }),
  props: {
    photo: Object
  },
  methods: {
    updatePhoto() {
      const updatedPhoto = {
        pcode: this.photo.pcode,
        pplace: this.place,
        purl: this.photo.purl,
        pname: this.name,
        psource: this.photo.psource
      };
      this.dialog = false;
      this.$emit("updatePhoto", updatedPhoto);
    }
  }
};
</script>

<style>
</style>
