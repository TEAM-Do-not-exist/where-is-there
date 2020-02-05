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

                </v-layout>
                <v-layout
                  align-center
                  justify-center
                >

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
import Axios from 'axios';
export default {
  methods:{
    naverToken(){
      var access_token = window.location.href.split("#")[1].split("&")[0].split("=")[1]
      // alert(window.location.href.split("#")[1].split("&")[0].split("=")[1]);
      Axios
        .get('http://192.168.100.52:8090/api/external/login_naver/'+access_token)
        .then(r=>{
          alert(r.data.token)
          this.$router.push("/"); //메인 페이지로 가야함
      })
      // window.close()
    }
  },
  mounted(){
    this.naverToken()
  }
}
</script>