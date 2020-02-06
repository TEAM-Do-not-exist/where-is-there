<template>
  <v-list v-auto-bottom="msgs" style="overflow-y: auto; overflow-x: hidden;">
    <transition-group name="list">
      <div v-for="(msg,index) in msgs" v-bind:key="index">
        <div>
          <div>
            <span v-if="msg.from.name=='나:'" style="display:block; text-align:right; padding:5px;">
              <div class="list-chat">
                <span>{{ msg.msg}}  </span>
              </div>
            </span>
            <span v-else>
              {{msg.from.name }} {{ msg.msg}}
            </span>
          </div>
        </div>
      </div>
    </transition-group>
  </v-list>
</template>

<script>
export default {
  name: "MessageList",
  props: ["msgs"],
  data(){
    return{
      // name: this.msg.from.name
    }
  },
};
</script>

<style>
.list-chat {
  display: inline-block;
  margin-right: 10px;
}
.list-enter-active,
.list-leave-active {
  transition: all 1s;
}
.list-enter, .list-leave-to /* .list-leave-active below version 2.1.8 */ {
  opacity: 0;
  transform: translateX(30px);
}
</style>