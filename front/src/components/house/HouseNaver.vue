
<template>
<!-- https://vuetifyjs.com/en/components/cards/#information-card -->
  <v-card
    class="mx-auto"
    max-width="1000"
  >
    <v-container fluid>
      <h3>부동산 News</h3>
      <v-row dense>
        <v-col
          v-for="(news, index) in news3"
          :key="news.title"
          :cols="4"
        >
          <v-card>
            <v-img
              :src="cards[index].src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
              
            >
              <v-card-title v-text="$options.filters.strippedContent(news.title)" @click="oppp(news.link)" ></v-card-title>
            </v-img>

            <v-card-actions >
              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon >mdi-bookmark</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
import {mapActions, mapState} from "vuex";
  const naverStore = "naverStore";

  export default {
    name: "HouseNaver",
    data: () => ({

      cards: [
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 4 },
        { title: "KB국민은행, &apos;KB 가족<b>부동산</b> 지킴신탁&apos; 출시", src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg', flex: 4 },
        { title: 'Best airlines', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
      ],
    }),
    computed:{
      ...mapState(naverStore, ["newsList","news3"]),
    },
    created(){
      this.searchNaverNews("부동산");
        console.log(this.newsList);
      
    },
    filters: {
    strippedContent: function(string) {
      const div = document.createElement('div')
      div.innerHTML = string
      const text = div.textContent || div.innerText || ''
      return text
    },
  },
      methods: {
        ...mapActions(naverStore, ["searchNaverNews"]),
      oppp(link){
        window.open(link, "_blanck");
        console.log("click");
    },
  },
  }
</script>

<style>

</style>