<template>
  <v-app id="inspire">
    <v-container>
      <v-layout align-center justify-center>
        <v-card flat>
          <v-card-title>
            <h1>SIGN IN</h1>
          </v-card-title>
          <v-card-text>
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-row>
                <v-col :cols="8">
                  <v-text-field
                    v-model="id"
                    :rules="nameRules"
                    :counter="30"
                    label="ID"
                    required
                    @keyup.13="submit"
                  ></v-text-field>
                  <v-text-field
                    v-model="password"
                    :rules="passwordRules"
                    label="Password"
                    type="password"
                    required
                    @keyup.13="submit"
                  ></v-text-field>
                </v-col>
                <v-col :cols="4">
                  <v-btn @click="submit" outlined height="90%">로그인</v-btn>
                </v-col>
              </v-row>
              <v-layout align-center justify-center>
                <KakaoLogin
                  api-key="1ccf33bc552996470ef083f21eff16c2"
                  image="kakao_account_login_btn_large_narrow_ov"
                  :on-success="onSuccess"
                  :on-failure="onFailure"
                />
              </v-layout>
              <v-layout align-center justify-center>
                <NaverLogin
                  client-id="nsrxqIjEGhhBf9jdPBFD"
                  callback-url="http://i02c107.p.ssafy.io/naversignin"
                  v-bind:is-popup="false"
                  v-bind:button-type="3"
                  v-bind:button-height="60"
                  button-color="green"
                  :callbackFunction="callbackFunction"
                />
              </v-layout>

              <v-layout align-center justify-center>
                <v-btn @click="to_sign_up" outlined width="277.33" height="60">...or sign up now!</v-btn>
              </v-layout>
            </v-form>
          </v-card-text>
        </v-card>
      </v-layout>
    </v-container>
  </v-app>
</template>

<script>
import KakaoLogin from "vue-kakao-login";
import NaverLogin from "vue-naver-login";
import { mapGetters } from "vuex";

export default {
  name: "SignIn",
  components: {
    NaverLogin,
    KakaoLogin
  },
  data: () => ({
    valid: true,
    id: "",
    nameRules: [v => !!v || "ID is required"],
    password: "",
    passwordRules: [v => !!v || "Password is required"]
  }),
  computed: {
    ...mapGetters(["token"])
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.$store.dispatch("LOGIN_NOMAL", {
          id: this.id,
          password: this.password
        });
      }
    },
    onSuccess(data) {
      this.$store.dispatch("LOGIN_KAKAO", data.access_token).then();
    },
    onFailure() {},
    callbackFunction() {},
    to_sign_up() {
      this.$router.push("/signup");
    }
  }
};
</script>

<style>
span {
  display: inline;
}
#kakao-login-btn {
  display: inline;
}
#kakao-login-btn img {
  height: 60px;
  width: 277.33px;
}
.v-btn__content {
  font-size: 18px;
}
</style>
