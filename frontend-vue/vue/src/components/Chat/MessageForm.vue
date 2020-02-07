<template>
  <div class="inner-wrap">

    <!-- 로그인 했을 시 채팅 -->
    <div v-if="this.token!=null">
    <v-text-field
      v-model="msg"
      label="chat"
      placeholder="보낼 메세지를 입력하세요."
      solo
      @keyup.13="submitMessageFunc"
    ></v-text-field>
    </div>
    <!-- 로그인이 안되었을 때 채팅 -->
    <div v-else>
      <v-text-field
      v-model="msg"
      label="chat"
      placeholder="로그인 후 사용 가능합니다."
      solo
    ></v-text-field>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "MessageForm",
  data() {
    return {
      msg: ""
    };
  },
  methods: {
    submitMessageFunc() {
      if (this.msg.length === 0) return false;
      this.$emit("submitMessage", this.msg);
      this.msg = "";
      return true;
    }
  },
  computed:{
    ...mapGetters(["token"])
  }
};
</script>

<style>
</style>
