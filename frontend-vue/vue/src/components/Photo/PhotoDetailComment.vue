<template>
  <v-card>
    <v-container>
      <v-row class="justify-space-between">
        <v-col cols="12" class="headline font-weight-bold">
          <p>{{ comment.content }}</p>

          <v-row class="justify-space-between align-center">
            <p class="subtitle-1 font-weight-thin">{{ comment.cid }}</p>
            <div>
              <v-btn icon>
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-divider class="mx-2" vertical></v-divider>
              <v-btn icon>
                <v-icon>mdi-delete</v-icon>
              </v-btn>
            </div>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: "PhotoDetailComments",
  data: () => ({}),
  props: {
    comment: Object
  }
};
</script>

<style>
</style>
