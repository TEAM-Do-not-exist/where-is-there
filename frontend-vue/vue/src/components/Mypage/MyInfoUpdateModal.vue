<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <!-- modal open button -->
      <template v-slot:activator="{ on }">
        <v-btn color="orange" text v-on="on">수정하기</v-btn>
      </template>

      <!-- modal information -->
      <v-card>
        <!-- head -->
        <v-card-title>
          <span class="headline"> 정보 수정 </span>
        </v-card-title>

        <!-- body -->
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  v-model="name"
                  label="nickname*"
                  required > 
                {{item.name}}
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="phone"
                  label="phone*"
                  required
                ></v-text-field>
              </v-col>
                 <v-col cols="12">
                <v-text-field
                  v-model="pw"
                  label="pw*"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <!-- footer -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn :disabled="!validated" color="blue darken-1" text @click="updateMember()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    email: null,
    phone: null,
    name: "",
    pw:"",
    dialog: false
  }),
  props: {
    item: Object
  },
  methods: {
     showmyinfo(){
      const basicUrl = "http://127.0.0.1:8090/";
      const addUrl = "api/member/selectOneEmail/";
      // const cid = this.cid;
      const id = "123@123"; // 현재 아이디 박아놓은 상태.
      axios
        .get(basicUrl+addUrl+id)
        .then(response => {
          this.item = response.data['resvalue'];
          this.email = this.item.email;
          this.phone = this.item.phone; 
          this.name = this.item.name;
        })
        .catch(() => {
               this.errored = true;
         })
        . finally(() => (this.loading = false));
    },
    updateMember() {
      const basicUrl = "http://127.0.0.1:8090/";
      const addUrl = "api/member/update";
      const data = {
        email: "123@123", //임시로 넣어놓은 아이디 입니다. 
        pw: this.pw, 
        name: this.name,
        phone: this.phone
        };
      axios
        .put(basicUrl + addUrl, data)
        .then(r => {
          if (r.data.regmsg === "입력했습니다") {
            this.$emit("onInsert");
          }
        })
        .catch();
      return (this.dialog = false);
    }
  },
  computed: {
    validated() {
      return (
        this.pw &&
        this.name &&
        this.phone 
      );
    }
  },
  mounted(){
    this.showmyinfo(); 
  }
};
</script>

<style></style>
