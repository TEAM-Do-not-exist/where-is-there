<template>
<div id="app">
  <v-app id="inspire">
        <!-- <v-card-title  >
              <h1>My Page</h1>
            </v-card-title> -->
    <v-card
      class="mx-auto"
      max-width="1000"
    >
      <v-img
        class="white--text align-end"
        height="200px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
      </v-img>
  
    <v-card-subtitle class="pb-0">Name:  </v-card-subtitle>
     <v-card-subtitle class="pb-0">ID: </v-card-subtitle>
     <v-card-subtitle class="pb-0">e-mail: </v-card-subtitle>
     <v-card-subtitle class="pb-0">phone: </v-card-subtitle>
      <!-- <v-card-text class="text--primary">
        <div>Whitehaven Beach</div>
  
        <div>Whitsunday Island, Whitsunday Islands</div>
      </v-card-text>
   -->
      <v-card-actions>
        <v-btn
          color="orange"
          text
        >
          수정하기
        </v-btn>

      </v-card-actions>
    </v-card>
    
    <v-card>
            <v-card-actions>
        <v-btn
          color="yellow"
          text
        >
          내 좋아요
        </v-btn>
        <v-btn
          color="yellow"
          text
        >
          내 댓글
        </v-btn>
            <v-content>
      <router-view />
    </v-content>

      </v-card-actions>
    </v-card>
  </v-app>
</div>
</template>

<style >

</style>