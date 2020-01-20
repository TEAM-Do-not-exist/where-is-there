<template>
  <div id="app">
    <v-container>
      <div v-for="(item, index) in src.hashtags" :key="index">
        {{item.id}} : {{item.hashtag}}
      </div>
      <div v-for="(item, index) in src.images" :key="index">
        {{item.id}}
        <img :src="item.url">
      </div>
      <p>{{src.link}}</p>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  props: {
    source: String,
  },
  data: () => ({
    drawer: null,
    src: null,
  }),
  mounted(){
    axios
    .get('') //여기에 url이 들어갑니다
    .then(response => {
      this.data.src = response;
    })
  }
};
</script>

<style>
</style>
