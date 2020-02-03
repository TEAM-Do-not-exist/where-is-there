<template>
  <div id="my-favorite">
      <v-card app background-color="#0000">
        <v-container fluid>
          <v-row dense>
            <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
              <v-card>
                <v-img
                  :src="card.src"
                  class="white--text align-end"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  height="200px"
                >
                  <v-card-title v-text="card.title"></v-card-title>
                </v-img>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn icon>
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
  </div>
</template>

<script>
export default {
  data: () => ({
    cards: [
      {
        title: "Pre-fab homes",
        src:
          "https://cdn.pixabay.com/photo/2020/01/22/17/21/camel-4785794__340.jpg",
      },
      {
        title: "Favorite road trips",
        src: "https://cdn.vuetifyjs.com/images/cards/road.jpg",
      },
      {
        title: "Best friends",
        src:
          "https://cdn.pixabay.com/photo/2018/10/05/10/56/landscape-3725657__340.jpg",
      },
      {
        title: "몰라",
        src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
      },
      {
        title: "모른다고",
        src:
          "https://cdn.pixabay.com/photo/2020/01/24/15/23/namibia-4790487__340.jpg",
      },
      {
        title: "그만",
        src:
          "https://cdn.pixabay.com/photo/2020/01/23/17/35/monkey-4788328__340.jpg",
      }
    ]
  })
};
</script>
