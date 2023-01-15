<template>
  <!-- https://vuetifyjs.com/en/components/cards/#information-card -->
  <v-card height="650" class="scroll">
    
  <v-container >
    <v-row>
      <v-col cols="12" md="3">
        <v-btn @click="move2Table" class="ma-2" color="indigo darken-2" dark>
        <v-icon dark left> mdi-arrow-left </v-icon>Back
        </v-btn>
      </v-col>
      <v-col cols="12" md="1"></v-col>
      <v-col id="tttt" cols="12" md="4" style="text-align:center; vertical-align:base-line;">
        <h2 >local news</h2></v-col>
      <v-col cols="12" md="4"></v-col>
    </v-row>
    <v-row dense>
      
      <v-col v-for="news in localNews" :key="news.title" cols="12">
        <v-card class="my-auto"  width="auto" height="auto"  @click="oppp(news.link)">
          <div class="v-flex flex-no-wrap justify-space-between">
            <v-list-item three-line>
              <v-list-item-content>
                <div class="text-overline mb-4">
                  {{ sido + " " + gugun }}
                </div>
                <v-list-item-title>
                  <b>{{ news.title | strippedContent }}</b>
                </v-list-item-title>
                <v-list-item-subtitle
                  >{{ news.description | strippedContent }}<br /><br
                /></v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
  </v-card>
</template>

<script>
import { mapActions, mapState } from "vuex";
const naverStore = "naverStore";
const hosueStore = "houseStore";

export default {
  // components:{NaverTop3},
  name: "HouseNews",
  data() {
    return {
      keyword: "",
    };
  },
  created() {
    this.keyword = "";
    this.keyword += this.sido;
    console.log(this.gugun);
    this.keyword += this.gugun;
    console.log(this.keyword);
    this.searchLocalNews(this.keyword);
    console.log(this.newsList);
  },
  computed: {
    ...mapState(naverStore, ["newsList", "localNews"]),
    ...mapState(hosueStore, ["sido", "gugun"]),
  },
  filters: {
    strippedContent: function (string) {
      const div = document.createElement("div");
      div.innerHTML = string;
      const text = div.textContent || div.innerText || "";
      return text;
    },
  },
  methods: {
    ...mapActions(naverStore, ["searchLocalNews"]),

    search() {
      this.searchLocalNews(this.keyword);
    },
    oppp(link) {
      window.open(link, "_blanck");
      console.log("click");
    },
    move2Table() {
      this.$router.push({ name: "houseTable" });
      

    }, 
  },
};
</script>

<style>
v-container {
  display: flex !important;
  flex-direction: column;
}

.v-card {
  flex-grow: 1;
  overflow: auto;
}
.scroll {
   overflow-y: scroll
}
</style>
