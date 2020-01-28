<template>
  <v-container>
    <v-card v-for="item in src" :key="item.pcode" class="mx-auto">
      <v-img height="400px" position="center center" :src="item.purl[0]">
        <template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular indeterminate color="blue-grey darken-4"></v-progress-circular>
          </v-row>
        </template>
      </v-img>
      <v-card-actions>
        <AdminItemModal :item="item" @onInsert="onInsert" />
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import AdminItemModal from "./AdminItemModal.vue";

export default {
  components: {
    AdminItemModal: AdminItemModal
  },
  methods: {
    onInsert(item) {
      this.$emit("onInsert", item);
    }
  },
  props: {
    src: Object
  }
};
</script>

<style></style>
