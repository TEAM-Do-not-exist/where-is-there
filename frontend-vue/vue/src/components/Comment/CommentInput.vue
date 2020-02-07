<template>
  <v-text-field
    label="comment"
    v-model="comment"
    :rules="[v => !errorMessage || 'You cannot leave comments more than 2']"
    :disabled="errorMessage !== ''"
    @keyup.enter="sendComment"
  ></v-text-field>
</template>

<script>
import axios from "axios";

export default {
  name: "CommentInput",
  props: { comments: Array, pcode: String },
  data: () => ({
    comment: "",
    errorMessage: ""
  }),
  methods: {
    sendComment() {
      if (this.comment !== "") {
        console.log(this.comments);
        const duplicated = this.comments.filter(
          comment => comment.cid === "admin"
        );
        if (duplicated.length < 1) {
          const url = `${process.env.VUE_APP_SPRING_URL}/api/comment/insert`;
          const data = {
            ccode: Number(this.pcode),
            cid: "admin",
            content: this.comment
          };
          axios.post(url, data).then(() => {
            this.comment = "";
            this.$emit("onInput");
          });
        } else {
          this.comment = "";
          this.errorMessage = "You can write only one comment for one ID";
        }
      }
    }
  }
};
</script>

<style></style>
