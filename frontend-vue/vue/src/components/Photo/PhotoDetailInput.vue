<template>
  <!-- DB 수정이 완료되면 다시 할 것 -->
  <v-text-field v-model="comment" @keyup.enter="sendComment"></v-text-field>
</template>

<script>
import axios from "axios";

export default {
  name: "PhotoDetailInput",
  props: {
    photo: Object
  },
  data: () => ({
    comment: ""
  }),
  methods: {
    sendComment() {
      if (this.comment !== "") {
        const url = "http://localhost:8090/api/comment/insert";
        const data = {
          ccode: Number(this.photo.pcode),
          cid: "admin@admin.com",
          content: this.comment
          // purl: this.photo.purl
        };
        axios
          .post(url, data)
          .then(r => {
            console.log(r);
            this.comment = "";
            this.$emit("onInput");
          })
          .catch();
      }
    }
  }
};
</script>

<style>
</style>
