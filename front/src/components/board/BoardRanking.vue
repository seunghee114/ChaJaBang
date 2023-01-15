<!--
https://vuetifyjs.com/en/components/simple-tables/#fixed-header
-->
<template>
  <v-simple-table class="ttt" fixed-header max-width="580" height="240">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Title</th>
          <th class="text-left">Writer</th>
          <th class="text-left">Hit</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in rankingList" :key="item.bno">
          <td @click="move2Detail(item.bno)">
            <b>{{ item.title }}</b>
          </td>
          <td>{{ item.writer }}</td>
          <td>
            <v-btn
              depressed
              disable
              dark
              color="red lighten-3"
              class="rounded-pill"
            >
              <v-icon size="small" dark left>mdi-heart</v-icon>
              {{ item.readCount }}
            </v-btn>
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import { bOrderList } from "@/api/board";
import { mapState, mapActions } from "vuex";

const boardStore = "boardStore";

export default {
  data() {
    return {
      search: "",
      totalCnt: 0,

      rankingList: [],
    };
  },
  computed: {
    ...mapState(boardStore, ["ranking", "board"]),
  },
  created() {
    let _this = this;
    bOrderList(
      ({ data }) => {
        console.log(data.length, data);
        _this.rankingList = data;
        _this.totalCnt = data.length;
      },
      (error) => {
        console.log(error);
      }
    );
    this.getBoardList();
    this.getRanking();
  },
  watch: {},
  methods: {
    ...mapActions(boardStore, [
      "getRanking",
      "getDetail",
      "getComments",
      "getBoardList",
    ]),
    move2Detail(el) {
      let bno = el;
      this.getDetail(bno);
      this.getComments(bno);
      console.log(bno);
      this.$router
        .push({ name: "boardDetail", query: { bno } })
        .catch((error) => {
          if (error.name !== "NavigationDuplicated") {
            this.$router.go(this.$router.currentRoute);
          }
        });
    },
  },
};
</script>

<style>
.ttt {
  font-family: "NanumSquare";
}
</style>
