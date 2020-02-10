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
              <!-- nickname 입력란 -->
              <v-col cols="12">
                <v-text-field v-model="nickname" label="nickname" required>
                  {{item.nickname}}
                </v-text-field>
              </v-col>

              <!-- phone 입력란 -->
              <v-col cols="12">
                <v-text-field v-model="phone" label="phone" required></v-text-field>
              </v-col>

              <!-- pw 입력란 -->
              <!-- extenal==0 일 때만(직접 회원가입 시) 비밀번호 수정이 가능합니다.  -->
              <v-col cols="12">
                <div v-if="external==0">
                <v-text-field v-model="pw" label="pw*" required :rules="[v => !!v || '비밀번호를 입력해주세요']"></v-text-field>
                </div>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <!-- footer -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :disabled="!validated" color="blue darken-1" text @click="updateMember()">Save</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
  import axios from "axios";
  import { mapGetters } from "vuex";

  export default {
    data: () => ({
      email: null,
      phone: null,
      name: "",
      pw: "",
      nickname: "",
      external:"",
      dialog: false,
      item:[]
    }),
    
    methods: {
      showmyinfo() {
        const basicUrl = "http://127.0.0.1:8090/";
        const addUrl = "api/member/selectOneToken/";
        const token = this.token

        axios
          .get(basicUrl + addUrl + token)
          .then(response => {
            this.item = response.data['resvalue'];
            this.email = this.item.email;
            this.phone = this.item.phone;
            this.name = this.item.name;
            this.nickname = this.item.nickname;
            this.external = this.item.external;
          })
          .catch(() => {
            this.errored = true;
          })
          .finally(() => (this.loading = false));
      },
      updateMember() {
        const basicUrl = "http://127.0.0.1:8090/";
        const addUrl = "api/member/update";
        const data = {
          email: this.email,
          pw: this.pw,
          name: this.name,
          phone: this.phone,
          nickname: this.nickname
        };
        sessionStorage.setItem("nickname",this.nickname);
        this.$store.state.nickname = this.nickname;
        axios
          .put(basicUrl + addUrl, data)
          .then(r => {
            if (r.data.regmsg === "입력했습니다") {
              console.log("rr")
            }
          })
          .catch();
        location.reload(); //얘 말고 다른 방법이 있을까?
        return (this.dialog = false);
      }
    },
    computed: {
      ...mapGetters(["token"]),
        validated() {
        return (
          this.phone &&
          this.nickname
        );
      }
    },
    mounted() {
      this.showmyinfo();
    }

  };
</script>

<style></style>