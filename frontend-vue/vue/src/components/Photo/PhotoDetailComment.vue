<template>
  <v-card>
    <v-container>
      <v-col cols="12" class="headline font-weight-bold">
        <v-row class="justify-space-between align-center">
          <div v-if="!modified">{{ comment.content }}</div>
          <v-text-field
            v-else
            v-model="comment.content"
            label="Modify your comment"
            @keyup.enter="modifyComment"
          ></v-text-field>

          <div class="d-inline-flex">
            <v-btn icon>
              <v-icon v-show="comment.cid === 'admin@admin.com'" @click="modifyComment">mdi-pencil</v-icon>
            </v-btn>
            <PhotoCommentDeleteBtn
              v-show="comment.cid === 'admin@admin.com'"
              @deleteComment="deleteComment"
            />
          </div>
        </v-row>
      </v-col>
    </v-container>
  </v-card>
</template>

<script>
import PhotoCommentDeleteBtn from "./PhotoCommentDeleteBtn";

export default {
  name: "PhotoDetailComment",
  components: { PhotoCommentDeleteBtn: PhotoCommentDeleteBtn },
  data: () => ({
    modified: false
  }),
  props: {
    comment: Object
  },
  methods: {
    modifyComment() {
      this.modified = !this.modified;
      if (this.modified === false) {
        this.$emit(
          "onModifyComment",
          this.comment.ccode,
          this.comment.cid,
          this.comment.content
        );
      }
    },
    deleteComment() {
      this.$emit("deleteComment", this.comment.ccode, this.comment.cid);
    }
  }
};
</script>

<style>
</style>
