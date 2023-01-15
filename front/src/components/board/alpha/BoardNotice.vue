<!--
https://vuetifyjs.com/en/components/data-tables/#server-side-paginate-and-sort
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
      <v-btn v-if="userInfo.userid === 'admin'"
      class="mx-2"
      fab
      dark
      small
      color="cyan" @click="move2Write" >
      <v-icon dark>
        mdi-pencil
      </v-icon>
    </v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="notices"
      @click:row="move2Detail"
      :search="search"
    ></v-data-table>
  </v-card>
</template>

<script>
// import { nRead } from "@/api/board";
import {mapState, mapActions, mapMutations} from "vuex";

const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "NoticeList",
  data() {
    return {
      search: "",
      totalCnt: 0,
      headers: [
        {
          text: "no",
          align: "start",
          sortable: false,
          value: "nno",
        },
        { text: "title", sortable: false, value: "ntitle" },
        { text: "write_date", value: "nwriteDate" },
      ],
      boardList: [],
    };
  },
  created(){
    this.getNotices();
    this.getLatest();
    console.log(this.notices);
  },

  computed:{
    ...mapState(boardStore, ["notices", "notice"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {

  },
  methods: {
    ...mapActions(boardStore, ["getNotices","getLatest","getNotice"]),
    ...mapMutations(boardStore, ["SET_NOTICE_LIST"]),

    move2Detail(el){
      let nno = el.nno;
      this.getNotice(nno);
      // this.$router.push({ name: "noticeDetail" ,params: { nno } });
      this.$router.push({ name: "noticeDetail"}).catch((error)=>{
        if(error.name !== 'NavigationDuplicated'){
          this.$router.go(this.$router.currentRoute);
        }
      });

    },
    move2Write(){
      this.$router.push({ name: "noticeWrite" }).catch((error)=>{
        if(error.name !== 'NavigationDuplicated'){
          this.$router.go(this.$router.currentRoute);
        }
      });
    },    

  },
};
</script>

<style></style>
