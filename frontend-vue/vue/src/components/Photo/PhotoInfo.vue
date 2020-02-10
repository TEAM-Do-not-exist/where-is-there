<template>
  <div>
    <!-- store and place name -->
    <v-card-title class="headline font-weight-bold mb-4 text-wrap"
      >#거기어디니: {{ photo[0].pname }}</v-card-title
    >
    <v-card-subtitle class="subtitle-1 font-weight-thin"
      >#어디에있니: {{ photo[0].pplace }}</v-card-subtitle
    >

    <!-- original link -->
    <v-chip class="ma-2">
      <a
        :href="photo[0].purl"
        target="_blank"
        class="caption font-weight-thin white--text"
        >{{ photo[0].purl }}</a
      >
    </v-chip>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "PhotoInfo",
  computed: {
    ...mapGetters(["photo"])
  }
};
</script>

<style></style>
