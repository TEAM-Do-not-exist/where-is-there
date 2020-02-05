<template>
  <v-list v-auto-bottom="msgs" style="overflow-y: auto; overflow-x: hidden;">
    <transition-group name="list">
      <div v-for="(msg,index) in msgs" v-bind:key="index">
        <v-list-tile>
          <v-list-tile-action>
            <span v-if="msg.from.name=='나:'" style="display:block; text-align:right; ">
              <v-spacer/>
                   <v-list-tile-content>
            <v-list-tile-title>{{ msg.msg}}  </v-list-tile-title>
          </v-list-tile-content>
            </span>
            <span v-else>
              {{msg.from.name }}
             <v-list-tile-content>
            <v-list-tile-title>{{ msg.msg}}</v-list-tile-title>
          </v-list-tile-content>
            </span>

          </v-list-tile-action>
        </v-list-tile>
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
      name: this.msg.from.name
    }
  }
};
</script>
<style>
.list-item {
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