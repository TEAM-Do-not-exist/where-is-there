<template>
  <div>
    <CommentInput :ccode="ccode" />
    <v-divider class="my-4"></v-divider>
    <div v-for="(comment, idx) in comments" :key="idx">
      <v-card>
        <v-container>
          <v-col cols="12" class="headline font-weight-bold">
            <v-row class="justify-space-between align-center">
              <div v-if="!modified">{{ comment.content }}</div>
              <v-text-field
                label="Modify your comment"
                v-else
                v-model="content"
                @keyup.enter="updateComment(idx)"
              ></v-text-field>

              <!-- update, delete buttons -->
              <div class="d-inline-flex">
                <v-btn icon>
                  <v-icon v-show="comment.cid === 'admin'" @click="updateComment(idx)">mdi-pencil</v-icon>
                </v-btn>
                <CommentDeleteButton v-show="comment.cid === 'admin'" :ccode="ccode" />
              </div>
            </v-row>
          </v-col>
        </v-container>
      </v-card>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import CommentInput from "./CommentInput";
import CommentDeleteButton from "./CommentDeleteButton";

export default {
  name: "Comments",
  components: {
    CommentInput: CommentInput,
    CommentDeleteButton: CommentDeleteButton
  },
  data: () => ({
    modified: false,
    content: ""
  }),
  props: {
    ccode: String
  },
  methods: {
    updateComment(idx) {
      this.modified = !this.modified;
      if (this.modified === false) {
        const url = `${process.env.VUE_APP_SPRING_URL}/api/comment/update`;
        const data = {
          ccode: this.ccode,
          cid: "admin",
          content: this.content
        };
        axios.put(url, data).then(() => {
          this.comments[idx].content = this.content;
        });
      } else {
        this.content = this.comments[idx].content;
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
