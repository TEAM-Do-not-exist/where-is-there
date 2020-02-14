<template>
  <div>
    <!-- item cards -->
    <v-card v-for="item in src" :key="item.pcode" class="my-5">
      <!-- item image -->
      <v-img height="400px" position="center center" :src="item.psource[0] || error">
        <template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular indeterminate color="blue-grey darken-4"></v-progress-circular>
          </v-row>
        </template>
      </v-img>
      <!-- item dialog -->
      <v-card-actions>
        <AdminInstaListDialog :item="item" @onInsert="onInsert" />
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import AdminInstaListDialog from "./AdminInstaListDialog";

export default {
  name: "AdminInstaList",
  components: {
    AdminInstaListDialog: AdminInstaListDialog
  },
  props: {
    src: Object
  },
  data: () => ({
    error:
      "https://cdn.pixabay.com/photo/2017/03/09/12/31/error-2129569_960_720.jpg"
  }),
  methods: {
    onInsert(item) {
      this.$emit("onInsert", item);
    }
  }
};
</script>

<style></style>
