<template>
  <v-bottom-sheet v-model="sheet" persistent>
    <template v-slot:activator="{ on }">
      <v-btn
        class="ma-2"
        color="red darken-1"
        :disabled="!isAdmin"
        v-on="on"
        outlined
        dark
        >delete</v-btn
      >
    </template>

    <v-sheet class="text-center" height="200px">
      <div class="py-12">정말로 삭제하시겠습니까?</div>
      <v-btn class="mb-6" color="red" @click="deletePhoto" small text
        >delete</v-btn
      >
      <v-btn class="mb-6" color="blue" @click="sheet = !sheet" small text
        >close</v-btn
      >
    </v-sheet>
  </v-bottom-sheet>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "PhotoDeleteButton",
  data: () => ({
    sheet: false
  }),
  methods: {
    deletePhoto() {
      this.sheet = !this.sheet;
      // delete photo
      if (this.isAdmin === true) {
        const deleteUrl = `${process.env.VUE_APP_SPRING_URL}/api/photo/delete/${this.photo[0].pcode}`;
        axios.delete(deleteUrl).then(() => {
          this.$router.push("/");
        });
        // insert photo to uncrawling list
        const checkUrl = `${process.env.VUE_APP_SPRING_URL}/api/photocheck/insert`;
        axios.post(checkUrl, { purl: this.photo[0].purl });
      }
    }
  },
  computed: {
    ...mapGetters(["photo", "getUser"]),
    isAdmin() {
      return this.getUser === process.env.VUE_APP_ADMIN_EMAIL ? true : false;
    }
  }
};
</script>

<style></style>
