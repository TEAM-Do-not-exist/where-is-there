<template>
    <v-app>
        Loading...
    </v-app>
</template>
<script>
import axios from 'axios'
import NaverLogin from 'vue-naver-login'

let callbackFunction = status => {
  if (status) {
    /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
    var email = NaverLogin.user.getEmail();
    if (email == undefined || email == null) {
      alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
      /* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
      NaverLogin.reprompt();
      return;
    }

    var item = {
      name: NaverLogin.user.getName(),
      email: NaverLogin.user.getEmail(),
    }

    axios
    .post('localhost:8090/api/test/naver',item)
    .then()

    window.location.replace(
      "http://" +
        window.location.hostname +
        (location.port == "" || location.port == undefined
          ? ""
          : ":" + location.port) +
        "/main"
    );
  } else {
    this.console.log("callback 처리에 실패하였습니다.");
  }
};

export default {
  data() {
    return{
      carrage: {
        token: '',
        status: '',
      }
    }
  },
  props:{
      input: String
  },
  methods:{
    tokenizer(){
      var src = window.location.href.split("#")[1];
        if(src===false){
            return
        }

    },
    send(){
      axios
      .post("localhost:8090/api/test/naver", this.carrage)
      .then(
          this.router.push('/')
      )
    },
    callbackFunction() {
      callbackFunction();
    }
  },
  mounted() {
    this.tokenizer(this.input)
  }
}
</script>