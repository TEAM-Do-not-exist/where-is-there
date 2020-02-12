<template>
  <v-text-field
    persistent-hint
    label="댓글"
    v-model="comment"
    :hint="hint"
    :rules="[
      v => !errorMessage || '하나의 아이디로 2개 이상의 댓글을 달 수 없습니다.'
    ]"
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
          comment => comment.cid === this.getUser
        );
        if (duplicated.length < 1) {
          const url = `${process.env.VUE_APP_SPRING_URL}/api/comment/insert`;
          const data = {
            ccode: Number(this.ccode),
            cid: this.getUser,
            content: this.comment
          };
          axios.post(url, data).then(() => {
            this.comment = "";
            this.$store.dispatch("getCommentsAction", this.ccode);
          });
        } else {
          this.comment = "";
          this.errorMessage = "에러가 발생했습니다.";
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
