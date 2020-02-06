<template>
  <v-app id="inspire">
    <v-container>
      <v-layout align-center justify-center>
        <v-card flat>
          <v-card-title>
            <h1>SIGN UP</h1>
          </v-card-title>
          <v-card-text>
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field v-model="auth_key" :rules="nameRules" :counter="10" label="인증 번호" required></v-text-field>
              <v-btn :disabled="! valid" @click="submit" outlined width="277.33" height="60">submit</v-btn>
              <v-container />
              <v-layout align-center justify-center>
                <p style="upper-margin: 10px;">
                  <a @click="goback">back</a>
                </p>
              </v-layout>
            </v-form>
          </v-card-text>
        </v-card>
      </v-layout>
    </v-container>
  </v-app>
</template>

<script>

import axios from 'axios';
export default {
  data: () => ({
    valid: true,
    auth_key:'',
    name: "",
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 10) || "Name must be less than 10 characters"
    ],
    email: "",
    emailRules: [
      v => !!v || "E-mail is required",
      v =>
        /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) ||
        "E-mail must be valid"
    ],
    password: "",
    passwordRules: [
      v => !!v || "Password is required",
      v => (v && v.length >= 3) || "Password must be in sufficient size"
    ],
    checkbox: false
  }),

  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        // Native form submission is not yet supported
        const uri_email_check = 'http://localhost:8090/api/emailauth/auth_check'
        axios.post(uri_email_check, {
          email: this.email,
          auth_key: this.auth_key
        }).then(r=>{
            if(r.data.resvalue==1){
                alert('okok')
                this.$router.push('/')
            }else{
                alert("nono")
            }
        })
      }
    },
    clear() {
      this.$refs.form.reset();
    },
    goback() {
      this.$router.go(-1);
    }
  }
};
</script>

<style>
</style>
