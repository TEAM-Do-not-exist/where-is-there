<template>
  <v-text-field
    persistent-hint
    label="comment"
    v-model="comment"
    :hint="hint"
    :rules="[v => !errorMessage || 'You cannot leave comments more than 2']"
    :disabled="errorMessage !== '' || getUser === undefined"
    @keyup.enter="sendComment"
  ></v-text-field>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "CommentInput",
  props: { ccode: String },
  data: () => ({
    comment: "",
    errorMessage: ""
  }),
  methods: {
    sendComment() {
      if (this.comment !== "") {
        const duplicated = this.comments.filter(
          comment => comment.cid === "admin"
        );
        if (duplicated.length < 1) {
          const url = `${process.env.VUE_APP_SPRING_URL}/api/comment/insert`;
          const data = {
            ccode: Number(this.ccode),
            cid: "admin",
            content: this.comment
          };
          axios.post(url, data).then(() => {
            this.comment = "";
            this.$store.dispatch("getCommentsAction", this.ccode);
          });
        } else {
          this.comment = "";
          this.errorMessage = "You can write only one comment for one ID";
        }
      }
    }
  },
  computed: {
    ...mapGetters(["comments", "getUser"]),
    hint() {
      if (this.getUser === undefined) {
        return "로그인이 필요한 기능입니다.";
      } else {
        return "댓글을 남겨주세요.";
      }
    }
  },
  mounted() {
    this.$store.dispatch("getCommentsAction", this.ccode);
    this.$store.dispatch("getUserAction");
  }
};
</script>

<style></style>
