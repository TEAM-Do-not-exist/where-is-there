<template>
  <v-app id="inspire">
    <v-container>
      <v-layout align-center justify-center>
        <v-card flat>
          <v-card-title>
            <h1>SIGN UP</h1>
          </v-card-title>
          <v-card-text>
            <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
              @keyup.13="submit"
            ></v-text-field>
            <v-btn :disabled="!valid" @click="submit" outlined width="277.33" height="60">submit</v-btn>
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
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "SignUp",
  data: () => ({
    valid: true,
    email: "",
    emailRules: [
      v => !!v || "E-mail is required",
      v =>
        /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) ||
        "E-mail must be valid"
    ]
  }),
  methods: {
    submit() {
      if (this.valid) {
        // Native form submission is not yet supported
        const uriDupliecated = `${process.env.VUE_APP_SPRING_URL}/api/member/duplicateCheckEmail/`;
        axios.get(uriDupliecated + this.email).then(r => {
          if (r.data.resvalue == 1) {
            this.$router.push("/signup1/" + this.email);
          } else {
            alert("중복된 아이디 입니다.");
          }
        });
      }
    },
    goback() {
      this.$router.go(-1);
    }
  }
};
</script>

<style></style>
