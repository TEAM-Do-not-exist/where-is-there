<template>
  <v-app id="inspire">

  </v-app>
</template>


<script>
export default {
  methods:{
    naverToken(){
      var access_token = window.location.href.split("#")[1].split("&")[0].split("=")[1]
      this.$store.dispatch('LOGIN_NAVER',access_token)
      .then(
        this.$router.push("/")
        // setTimeout(()=>{this.$router.push("/")},300)
        );//메인 페이지로 가야함
    }
  },
  mounted(){
    this.naverToken()
  }
}
</script>