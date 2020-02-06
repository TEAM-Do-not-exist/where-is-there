<template>
  <div class="text-center">
    <v-bottom-sheet v-model="sheet" persistent>
      <template v-slot:activator="{ on }">
        <v-btn class="ma-2" outlined dark color="red darken-1" v-on="on">delete</v-btn>
      </template>
      <v-sheet class="text-center" height="200px">
        <div class="py-12">정말로 삭제하시겠습니까?</div>
        <v-btn class="mt-6" small text color="red" @click="deletePhoto">delete</v-btn>
        <v-btn class="mt-6" small text color="blue" @click="sheet = !sheet">close</v-btn>
      </v-sheet>
    </v-bottom-sheet>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CommentDeleteButton",
  props: {
    pcode: String,
    purl: String
  },
  data: () => ({
    sheet: false
  }),
  methods: {
    deletePhoto() {
      this.sheet = !this.sheet;

      const deleteUrl = `http://localhost:8090/api/photo/delete/${this.pcode}`;
      axios
        .delete(deleteUrl)
        .then(() => {
          this.$router.push("/");
        })
        .catch();

      const checkUrl = "http://localhost:8090/api/photocheck/insert";
      axios
        .post(checkUrl, { purl: this.purl })
        .then()
        .catch();
    }
  },
  mounted() {
    this.$store.dispatch("getPhotosAction");
  }
};
</script>

<style>
</style>
