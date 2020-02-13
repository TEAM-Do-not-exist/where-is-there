<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <!-- dialog open button -->
      <template v-slot:activator="{ on }">
        <v-btn class="font-weight-black" color="blue" text v-on="on" :disabled="!isAdmin">정보 확인하기</v-btn>
      </template>

      <!-- dialog information -->
      <v-card>
        <!-- head -->
        <v-card-title>
          <span class="headline font-weight-black">사진 정보</span>
        </v-card-title>
        <!-- carousel images, when click image, image will set selected image -->
        <v-carousel hide-delimiter-background delimiter-icon="mdi-minus">
          <v-carousel-item
            reverse-transition="fade-transition"
            transition="fade-transition"
            v-for="(url, idx) in item.psource"
            :key="idx"
            :src="url"
            @click="setUrl(url)"
          ></v-carousel-item>
        </v-carousel>

        <!-- body -->
        <v-card-text>
          <v-container>
            <v-row>
              <!-- select place name -->
              <v-col cols="12" sm="12" md="6">
                <v-autocomplete
                  :items="item.pplace_pname"
                  :rules="[v => !!v || '지역을 확인해주세요.']"
                  v-model="place"
                  label="지역 이름*"
                  required
                ></v-autocomplete>
              </v-col>
              <!-- select store name -->
              <v-col cols="12" sm="12" md="6">
                <v-autocomplete
                  :items="item.pplace_pname"
                  :rules="[v => !!v || '장소를 확인해주세요.']"
                  v-model="store"
                  label="장소 이름*"
                  required
                ></v-autocomplete>
              </v-col>
              <!-- check image url -->
              <v-col cols="12">
                <v-text-field
                  :rules="[v => !!v || '사진 주소를 확인해주세요.']"
                  v-model="source"
                  label="사진 주소*"
                  required
                ></v-text-field>
              </v-col>
              <!-- check original link -->
              <v-col cols="12">
                <v-text-field
                  :rules="[v => !!v || '원본 주소를 확인해주세요.']"
                  v-model="url"
                  label="원본 주소*"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <!-- footer -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="font-weight-black" color="blue darken-1" text @click="dialog = false">취소하기</v-btn>
          <v-btn
            text
            class="font-weight-black"
            color="red darken-1"
            :disabled="!this.isAdmin"
            @click="denyItem(item)"
          >제외하기</v-btn>
          <v-btn
            text
            class="font-weight-black"
            color="blue darken-1"
            :disabled="!validated"
            @click="insertItem(item)"
          >저장하기</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "AdminInstaItemsDialog",
  data: () => ({
    place: null,
    store: null,
    source: "",
    url: "",
    dialog: false
  }),
  props: {
    item: Object
  },
  methods: {
    setUrl(url) {
      (this.source = url), (this.url = this.item.purl); // url: original link, source: image url
    },
    sendPost(url, data, item) {
      axios.post(url, data).then(() => {
        this.$emit("onInsert", item);
      });
    },
    insertItem(item) {
      if (this.getUser === process.env.VUE_APP_ADMIN_EMAIL) {
        const url = `${process.env.VUE_APP_SPRING_URL}/api/photo/insert`;
        const data = {
          pname: this.store,
          pplace: this.place,
          psource: this.source,
          purl: this.url
        };
        this.sendPost(url, data, item);
      }
      this.dialog = false;
    },
    denyItem(item) {
      if (this.getUser === process.env.VUE_APP_ADMIN_EMAIL) {
        const url = `${process.env.VUE_APP_SPRING_URL}/api/photocheck/insert`;
        const data = { purl: this.url || item.purl };
        this.sendPost(url, data, item);
      }
      this.dialog = false;
    }
  },
  computed: {
    ...mapGetters(["getUser", "token"]),
    isAdmin() {
      return this.getUser === process.env.VUE_APP_ADMIN_EMAIL ? true : false;
    },
    validated() {
      return (
        this.place && this.store && this.source && this.url && this.getUser
      );
    }
  },
  mounted() {
    this.$store.dispatch("getUserAction");
  }
};
</script>

<style></style>
