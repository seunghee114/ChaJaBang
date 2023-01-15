<!--
https://vuetifyjs.com/en/components/simple-tables/#fixed-header
-->
<template>
  <v-simple-table class="ttt" fixed-header max-width="580" height="240">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Title</th>
          <th class="text-left">Registed</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in latest" :key="item.nno">
          <td @click="move2Detail(item.nno)">
            <b>{{ item.ntitle }}</b>
          </td>
          <td>{{ item.nwriteDate | yyyyMMdd }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import { mapState, mapActions } from "vuex";

const boardStore = "boardStore";

export default {
  name: "boardNotice4",
  data() {
    return {
      nno:"",
    };
  },
  created() {
    
    this.getNotices();
    this.getLatest();
    console.log("board notice4 created");
    console.log(this.latest);
  },
  computed: {
    ...mapState(boardStore, ["notices", "latest"]),
  },
  watch: {
    latest() {},
  },
  methods: {
    ...mapActions(boardStore, ["getLatest","getNotices","getNotice"]),
    move2Detail(nno) {

      this.getNotice(nno);
      // this.$router.push({ name: "noticeDetail" ,params: { nno } });
      this.$router.push({ name: "noticeDetail"})      .catch((error)=>{
        if(error.name !== 'NavigationDuplicated'){
          this.$router.go(this.$router.currentRoute);
        }
      });
    },
  },
  filters: {
    // filter로 쓸 filter ID 지정
    yyyyMMdd: function (value) {
      // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
      if (value == "") return "";

      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      var js_date = new Date(value);

      // 연도, 월, 일 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();

      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = "0" + month;
      }

      if (day < 10) {
        day = "0" + day;
      }

      // 최종 포맷 (ex - '2021-10-08')
      return year + "-" + month + "-" + day;
    },
  },
};
</script>

<style>
.ttt{
  font-family: 'NanumSquare';
}
</style>
