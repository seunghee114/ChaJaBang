<!-- comments
https://vuetifyjs.com/en/components/lists/#action-stack
-->

<template>
  <div>
    <v-row>
      <!-- <v-col cols="12" md="6"> -->
        <!-- board detail start -->
        <v-card class="mx-auto my-12" min-height="550" max-width="650">
          <v-container>
          <template slot="progress">
            <v-progress-linear color="deep-purple" height="10" indeterminate></v-progress-linear>
          </template>

          <br />
          <v-card-title
            ><h3>{{ notice.ntitle }}</h3>
          </v-card-title>
          <div style="text-align: right">
            <h4>
              <v-icon size="x-large">mdi-account</v-icon>
              운영자 &nbsp;&nbsp;&nbsp;
            </h4>
          </div>
          <div style="height:400px">
            <div style="text-align: right">
              <span class="my-4 text-subtitle-1"> 작성일 : {{ notice.nwriteDate | yyyyMMdd }} </span>
            </div>

            <h3 v-html="notice.ncontent"></h3>
            <br />

          </div>

          <v-card-actions>
            <v-row v-if="userInfo.userid==='admin'" style="text-align: center">
              <v-col cols="12" md="3"></v-col>
              
              <v-col cols="12" md="2"
                ><v-btn center color="indigo lighten-3" @click="modifyNotice(notice.nno)"
                  >수정</v-btn
                ></v-col
              >
              <v-col cols="12" md="2"
                ><v-btn color="indigo lighten-5" @click="deleteNotice">삭제</v-btn></v-col
              >
              <v-col cols="12" md="2"
                ><v-btn color="indigo lighten-5" @click="move2Notice">목록</v-btn></v-col
              >     
                            <v-col cols="12" md="2"></v-col>

            </v-row>
          </v-card-actions>
          </v-container>
        </v-card>
        <!-- board detail end -->
      <!-- </v-col> -->

    </v-row>
  </div>
</template>

<script>
import {nDelete } from "@/api/board";
import { mapState} from "vuex";

const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "NoticeDetail",
  data() {
    return {
      nno: 0,
      ncontent: "",
      ntitle:"",
      board: {},
    };
  },

  created() {

  },
  computed: {
    ...mapState(boardStore, [ "notice"]),
    ...mapState(memberStore, [ "userInfo"]),
    },
  methods: {

    modifyNotice(nno) {
      this.$router.push({ name: "noticeModify", params: { nno } });
    },
    deleteNotice() {
      nDelete(
        this.nno,
        ({ data }) => {
          alert(data);
          this.$router.push({ name: "notice" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
        move2Notice() {
      this.$router.push({ name: "notice" });
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
