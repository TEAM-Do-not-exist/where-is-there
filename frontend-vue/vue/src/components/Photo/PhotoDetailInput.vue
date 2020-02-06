<template>
  <!-- DB 수정이 완료되면 다시 할 것 -->
  <v-text-field
    v-model="comment"
    label="comment"
    :rules="[v => !errorMessage || 'You cannot leave comments more than 2']"
    :disabled="errorMessage !== ''"
    @keyup.enter="sendComment"
  ></v-text-field>
</template>

<script>
import axios from "axios";

export default {
  name: "PhotoDetailInput",
  props: {
    photo: Object,
    comments: Array
  },
  data: () => ({
    comment: "",
    errorMessage: ""
  }),
  methods: {
    sendComment() {
      if (this.comment !== "") {
        const checkArray = this.comments.filter(
          comment => comment.cid === "admin@admin.com"
        );
        if (checkArray.length < 1) {
          const url = "http://localhost:8090/api/comment/insert";
          const data = {
            ccode: Number(this.photo.pcode),
            cid: "admin@admin.com",
            content: this.comment
          };
          axios
            .post(url, data)
            .then(() => {
              this.comment = "";
              this.$emit("onInput");
            })
            .catch();
        } else {
          this.comment = "";
          this.errorMessage = "You can write only one comment for one ID";
        }
      }
    }
  }
};
</script>

<style>
</style>
