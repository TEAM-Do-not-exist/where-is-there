<template>
<div id="app">
  <v-app id="inspire">
    <v-card
      max-width="1000"
      class="mx-auto"
    >
      <v-container>
        <v-row dense>
          <!-- <v-col cols="12">
            <v-card
              color="#385F73"
              dark
            >
              <v-card-title class="headline">Unlimited music now</v-card-title>
  
              <v-card-subtitle>Listen to your favorite artists and albums whenever and wherever, online and offline.</v-card-subtitle>
  
              <v-card-actions>
                <v-btn text>Listen Now</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
   -->
          <v-col
            v-for="(item, i) in items"
            :key="i"
            cols="12"
          >
            <v-card
              :color="item.color"
              dark
            >
              <div class="d-flex flex-no-wrap ">
                 <v-avatar
                  class="ma-3"
                  size="50"
                  tile
                >
                  <v-img :src="item.src"></v-img>
                </v-avatar>
                <div >
                  <!-- <v-card-title
                    class="headline"
                    v-text="item.title"
                  ></v-card-title> -->
                  <v-card-subtitle v-text="item.title"></v-card-subtitle>
                  <v-card-subtitle v-text="item.content"></v-card-subtitle>
                </div>
              </div>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-app>
</div>
</template>

<script>
export default {
    data: () => ({
    items: [
      {
        color: 'black',
        src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
        // title: '사진 바로가기',
        content: '내가 쓴 댓글이 들어갈거임',
      },
      {
        color: 'black',
        src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
        // title: '사진 바로가기',
        content: '내가 쓴 댓글이 들어갈거임',
      },
           {
        color: 'black',
        src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
        // title: '사진 바로가기',
        content: '내가 쓴 댓글이 들어갈거임',
      },
      {
        color: 'black',
        src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
        // title: '사진 바로가기',
        content: 'Ellie Goulding',
      },     {
        color: 'black',
        src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
        // title: '사진 바로가기',
        content: 'Foster the People',
      },
      {
        color: 'black',
        src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
        // title: '사진 바로가기',
        content: 'Ellie Goulding',
      },
    ],
  })
}
</script>