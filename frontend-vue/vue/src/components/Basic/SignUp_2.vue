<template>
  <v-container>
    <v-layout align-center justify-center>
      <v-card flat>
        <v-card-title>
          <h1>SIGN UP</h1>
        </v-card-title>
        <v-card-text>
          <span style="color : red;">남은 시간: {{ timeCounter }}초</span>
          <v-text-field
            v-model="authKey"
            :rules="nameRules"
            :counter="10"
            label="인증 번호"
            required
            @keyup.13="submit"
          ></v-text-field>
          <v-btn @click="submit" outlined width="277.33" height="60">submit</v-btn>
          <v-btn @click="re_request" outlined width="277.33" height="60">인증번호 재발급</v-btn>
          <v-container />
          <v-layout align-center justify-center>
            <p style="upper-margin: 10px;">
              <a @click="goback">back</a>
            </p>
          </v-layout>
        </v-card-text>
      </v-card>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "SignUp_2",
  data: () => ({
    valid: true,
    authKey: "",
    timeCounter: 300,
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 10) || "Name must be less than 10 characters"
    ]
  }),
  methods: {
    start() {
      setInterval(() => {
        this.timeCounter--;
      }, 1000);
    },
    re_request() {
      const uriEamilCheck = `${process.env.VUE_APP_SPRING_URL}/api/emailauth/request_re`;
      this.timeCounter = 300;
      axios
        .post(uriEamilCheck, {
          email: this.$route.params.email
        })
        .then(alert(this.$route.params.email + "로 재발송 되었습니다."));
    },
    submit() {
      if (this.valid) {
        // Native form submission is not yet supported
        const uriEmailCheck = `${process.env.VUE_APP_SPRING_URL}/api/emailauth/auth_check`;
        const uriSignUp = `${process.env.VUE_APP_SPRING_URL}/api/member/insert`;

        axios
          .put(uriEmailCheck, {
            email: this.$route.params.email,
            auth_key: this.authKey
          })
          .then(r => {
            if (r.data.resvalue == 1) {
              axios.post(uriSignUp, {
                email: this.$route.params.email,
                nickname: this.$route.params.name,
                pw: this.$route.params.pw,
                name: "",
                phone: ""
              });
              alert("가입 되었습니다.");
              this.$router.push("/");
            } else {
              alert("인증번호가 틀렸습니다.");
            }
          });
      }
    },
    goback() {
      this.$router.go(-1);
    }
  },
  mounted() {
    this.start();
  }
};
</script>

<style></style>
