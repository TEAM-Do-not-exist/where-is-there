<template>
  <v-app>
    <v-lazy
    false
    :options="{
      threshold: .5
    }"
    min-height="1000"
    transition="fade-transition">
      <v-container>
        <div v-for="(item3, index) in imgs" :key="index">
            pcode : {{index+1}}
            <div v-for="(itemi,idx) in item3" :key="idx">  
        <v-lazy
        false
        :options="{
          threshold: .5
        }"
        min-height="200"
        transition="fade-transition">
            <img width="100%" :src="itemi">
        </v-lazy>
            </div>
        </div>
        <a @click="ax" href="#">click</a>
      </v-container>
    </v-lazy>
  </v-app>
</template>

<script>
import axios from 'axios'

export default {
  data: () => ({
    src: null,
    length: 100,
    tmp: [],
    imgs: [],
  }),
  methods:{
    ax() {
      axios
      .get('http://127.0.0.1:8000/instagram/?length='+this.length+'&target=tour') //여기에 url이 들어갑니다
      .then(response => {
        this.src = response.data;
        for (let index = 1; index < 100; index++) {
          this.tmp.push(response.data[index])
        }
        for (let index = 0; index < 99; index++) {
          this.imgs.push(this.tmp[index]['purl'])
        }
      })
    }
  },
  mounted(){
    this.ax();
  }
};
</script>

<style>
</style>
