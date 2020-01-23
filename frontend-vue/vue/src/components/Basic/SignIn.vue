<template>
  <v-app id="inspire">
    <v-container>
      <v-card
        flat
      >
        <v-card-title>
          <h1>SIGN IN</h1>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
              v-model="id"
              :rules="nameRules"
              :counter="10"
              label="ID"
              required
          ></v-text-field>
          <v-text-field
              v-model="password"
              :rules="passwordRules"
              label="Password"
              type="password"
              required
          ></v-text-field>
          <v-container>
            <KakaoLogin
              api-key="6276da62b0eea6dd696fe9b1e192dfdb"
              image="kakao_account_login_btn_large_wide_ov"
              :on-success=onSuccess
              :on-failure=onFailure
            />
          </v-container>
          <v-container>
            <NaverLogin
              client-id="nsrxqIjEGhhBf9jdPBFD"
              callback-url="/main"
              v-bind:is-popup="true"
              v-bind:button-type="3"
              v-bind:button-height="100"
              button-color="green"
              :callbackFunction=callbackFunction
            />
          </v-container>
          <p>
            <a @click="to_sign_up">sign up</a>
          </p>
          <v-btn
              :disabled="!valid"
              @click="submit"
              color="indigo"
              right
          >
              Sign in
          </v-btn>
          
          </v-form>
        </v-card-text>
      </v-card>
    </v-container>
  </v-app>
</template>

<script>
// import axios from 'axios';
import KakaoLogin from 'vue-kakao-login'
import NaverLogin from 'vue-naver-login'
 
let callbackFunction = (status) => {
    if (status) {
    /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
    var email = NaverLogin.user.getEmail();
    if( email == undefined || email == null) {
      alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
      /* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
      NaverLogin.reprompt();
      return;
    }
 
    window.location.replace("http://" + window.location.hostname + ( (location.port==""||location.port==undefined)?"":":" + location.port) + "/signin");
  } else {
    this.console.log("callback 처리에 실패하였습니다.");
  }
}
let onSuccess = (data) => {
  this.console.log(data)
  this.console.log("success")
}
let onFailure = (data) => {
  this.console.log(data)
  this.console.log("failure")
}

export default {
  components: {
    NaverLogin,
    KakaoLogin
  },
  data: () => ({
    valid: true,
    name: '',
    nameRules: [
      v => !!v || 'ID is required'
    ],
    password: '',
    passwordRules: [
      v => !!v || 'Password is required'
    ],
    drawer: null,
  }),

  methods: {
    submit () {
      if (this.$refs.form.validate()) {
        // Native form submission is not yet supported
        // axios.post('/api/submit', {
          //   name: this.name,
        //   email: this.email,
        //   select: this.select,
        //   checkbox: this.checkbox
        // })
      }
    },
    clear () {
      this.$refs.form.reset()
    },
    onSuccess,
    onFailure,
    callbackFunction,
    to_sign_up() {
      this.$router.push('/signup')
    }
  }
}

</script>

<style>
span {
  display: inline;
}
</style>