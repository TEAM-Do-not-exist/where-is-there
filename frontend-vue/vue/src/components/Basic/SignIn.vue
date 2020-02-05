<template>
  <v-app id="inspire">
    <v-container>
      <v-layout
        align-center
        justify-center
      >
          <v-card flat>
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
                
                <v-layout
                  align-center
                  justify-center
                >
                  <KakaoLogin
                    api-key="1ccf33bc552996470ef083f21eff16c2"
                    image="kakao_account_login_btn_medium_narrow"
                    :on-success="onSuccess"
                    :on-failure="onFailure"
                  />
                </v-layout>
                <v-layout
                  align-center
                  justify-center
                >
                  <NaverLogin
                    client-id="nsrxqIjEGhhBf9jdPBFD"
                    callback-url="http://192.168.100.52:8080/naversignin"
                    v-bind:is-popup="false"
                    v-bind:button-type="3"
                    v-bind:button-height="60"
                    button-color="green"
                    :callbackFunction="callbackFunction"
                  />
                </v-layout>
                <v-layout
                  align-center
                  justify-center
                >
                  <v-btn @click="to_sign_up" outlined width="277.33" height="60">
                    ...or sign up now!
                  </v-btn>
                </v-layout>
              </v-form>
            </v-card-text>
          </v-card>
      </v-layout>
    </v-container>
  </v-app>
</template>

<script>
// import axios from 'axios';
import KakaoLogin from "vue-kakao-login";
import NaverLogin from "vue-naver-login";
import nearo from '../Error/main';
import Axios from 'axios';
import router from '../../router'

let callbackFunction = nearo.callbackFunction;


let onSuccess = data => {
  // this.console.log(data);
  // this.console.log("qwer");
  // this.console.log("success");
  Axios
    .get('http://192.168.100.52:8090/api/external/login_kakao/'+data.access_token)
    .then(r=>{
      alert(r.data.token) //카카오 토큰
      router.push('/'); 
    })
};
let onFailure = data => {
  this.console.log(data);
  this.console.log("failure");
};

export default {
  components: {
    NaverLogin,
    KakaoLogin
  },
  data: () => ({
    valid: true,
    id: "",
    nameRules: [v => !!v || "ID is required"],
    password: "",
    passwordRules: [v => !!v || "Password is required"],
    drawer: null
  }),

  methods: {
    // tokenizer(){
    //   window.location.href.split("/")[2].split(':')[1]
    // },

    submit() {
      if (this.$refs.form.validate()) {
        // Native form submission is not yet supported
        // axios.post('/api/submit', {
        //   name: this.name,
        //   email: this.email,
        //   select: this.select,
        //   checkbox: this.checkbox
        // })
        // Axios.get('client_id=nsrxqIjEGhhBf9jdPBFD&response_type=code&redirect_uri=http://192.168.100.52:8080&state=1111111111')
        // alert(window.location.href.split("/")[2].split(':')[1])


        // Axios
        //   .get('http://localhost:8090/api/member/login/'+this.id+'/'+this.password)
        //   .then(r=>{
        //     alert(r.data.token) //그냥 로그인 토큰
        //     router.push('/'); 
        // })  
        
      }
    },
    clear() {
      this.$refs.form.reset();
    },
    onSuccess,
    onFailure,
    callbackFunction,
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
#kakao-login-btn{
  display: inline;
}
#kakao-login-btn img{
  height: 60px;
  width: 277.33px;
}
.v-btn__content {
  font-size: 18px;
}
</style>
