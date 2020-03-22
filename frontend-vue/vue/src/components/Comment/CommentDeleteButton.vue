<template>
  <div class="text-center">
    <v-bottom-sheet v-model="sheet" persistent>
      <template v-slot:activator="{ on }">
        <v-btn icon v-on="on">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </template>
      <v-sheet class="text-center" height="200px">
        <div class="py-12">정말로 삭제하시겠습니까?</div>
        <v-btn class="mt-6 font-weight-black" small text color="red" @click="deleteComment">예</v-btn>
        <v-btn class="mt-6 font-weight-black" small text color="blue" @click="sheet = !sheet">아니오</v-btn>
      </v-sheet>
    </v-bottom-sheet>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "CommentDeleteButton",
  data: () => ({
    sheet: false
  }),
  props: {
    ccode: String
  },
  methods: {
    deleteComment() {
      this.sheet = !this.sheet;
      if (this.sheet === false) {
        const url = `${process.env.VUE_APP_SPRING_URL}/api/comment/delete/${this.ccode}/${this.getUser}`;
        axios.delete(url).then(() => {
          this.$store.dispatch("deleteCommentAction", this.ccode);
        });
      }
    }
  },
  computed: {
    ...mapGetters(["getUser"])
  }
};
</script>

<style></style>
