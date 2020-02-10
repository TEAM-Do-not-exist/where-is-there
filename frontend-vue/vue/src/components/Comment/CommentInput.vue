<template>
  <v-text-field
    label="comment"
    hint="press enter for remain comment"
    v-model="comment"
    :rules="[v => !errorMessage || 'You cannot leave comments more than 2']"
    :disabled="errorMessage !== ''"
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
    ...mapGetters(["comments"])
  },
  mounted() {
    this.$store.dispatch("getCommentsAction", this.ccode);
  }
};
</script>

<style></style>
