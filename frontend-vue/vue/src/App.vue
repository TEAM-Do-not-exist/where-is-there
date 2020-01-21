<template>
  <div id="app">
      <div v-for="(item3, index) in imgs" :key="index">
        pcode : {{index+1}}
        <div v-for="(itemi,idx) in item3" :key="idx">  
          <img :src="itemi">
        </div>
      </div>
      <a @click="ax">hi</a>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name:'app',
  props: {
    source: String,
  },
  data: () => ({
    drawer: null,
    src: null,
    arr: null,
    imsrg: null,
    length: 100,
    tmp: [],
    imgs: [],
  }),
  methods:{
    ax() {
      axios
      .get('http://127.0.0.1:8000/instagram/?length='+this.length+'&target=tour') //여기에 url이 들어갑니다
      .then(response => {
        this.arr = response.data.purl;
        this.imsrg = response.data.images;
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
    this.ax;
  }
};
</script>

<style>
</style>
