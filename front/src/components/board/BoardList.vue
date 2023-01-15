<!--
https://vuetifyjs.com/en/components/data-tables/#filterable
-->
<template>
  <v-card>
    <v-card-title>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
        color="indigo lighten-1"
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="boardList"
      @click:row="move2Detail"
      :search="search"
    ></v-data-table>
  </v-card>
</template>

<script>
import { bList } from "@/api/board";
export default {
  data() {
    return {
      search: "",
      totalCnt: 0,
      headers: [
        {
          text: "no",
          align: "start",
          sortable: false,
          value: "bno",
        },
        { text: "title", sortable: false, value: "title" },
        { text: "writer", sortable: false, value: "writer" },
        { text: "write_date", value: "writeDate" },
        { text: "read_count", value: "readCount" },
      ],
      boardList: [],
    };
  },

  created() {
    let _this = this;
    bList(
      ({ data }) => {
        console.log(data.length, data);
        _this.boardList = data;
        _this.totalCnt = data.length;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    move2Detail(el) {
      console.log("el", el);
      let bno = el.bno;
      console.log(bno);
      this.$router.push({ name: "boardDetail", query: { bno } });
    },
  },
};
</script>

<style></style>
