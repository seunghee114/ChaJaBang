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

    <v-simple-table fixed-header max-width="580" min-height="500">
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">아파트 이름</th>
            <th class="text-left">건축년도</th>
            <th class="text-left">좋아요 취소</th>
            <th class="text-left">선택</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in interestList" :key="item.data.aptCode">
            <td @click="getlist(item.data.aptCode)">
              {{ item.data.apartmentName }}
            </td>
            <td>{{ item.data.buildYear }}년</td>
            <td>
              <v-btn @click="btnClick(item.ino)"
                ><v-icon size="small" color="indigo lighten-1"
                  >mdi-heart</v-icon
                ></v-btn
              >
            </td>
            <td>
              <v-checkbox v-model="checkList" :value="item"></v-checkbox>
            </td>
          </tr>
          <tr>
            <td></td>
            <td></td>
            <td></td>
            <td><v-btn @click="move2Compare">비교</v-btn></td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>

    <!--     
    <v-data-table
      :headers="headers"
      :items="interestList"
      @click:row="move2Detail"
      :search="search"
    ></v-data-table> -->
  </v-card>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { uninterest, interestList, houseDetail } from "@/api/house";
const memberStore = "memberStore";
const houseStore = "houseStore";

export default {
  data() {
    return {
      search: "",
      headers: [
        { text: "아파트 이름", sortable: false, value: "apartmentName" },
        { text: "건축년도", sortable: false, value: "buildYear" },
        // { text: "평균 거래 금액", value: "writeDate" },
        // { text: "최고 거래 금액", value: "readCount" },
        // { text: "최저 거래 금액", value: "readCount" },
      ],
      interestList: [],
      err: "",
      checkList: [],
      inumber: "",
    };
  },
  created() {
    this.getInterests();
  },
  watch: {
    checkList() {
      console.log("checkList", this.checkList);
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["getDetail", "getDealList"]),
    ...mapMutations(houseStore, ["CLEAR_HOUSE_POINT"]),

    getInterests() {
      interestList(
        this.userInfo.userid,
        ({ data }) => {
          console.log("Interest List Success");
          console.log(data.length);
          for (var i = 0; i < data.length; i++) {
            var code = data[i].aptcode;
            this.inumber = data[i].ino;
            houseDetail(
              code,
              (resp) => {
                console.log("interest object resp : ", resp);
                // resp.data["ino"] = data[i].ino;
                console.log("number: ", this.inumber);
                var param = {
                  ino: this.inumber,
                  data: resp.data, // 객체
                };
                console.log("interest object param : ", param);
                this.interestList.push(param);
              },
              (error) => {
                this.err = error;
                console.log("interest object fail");
              }
            );
          }
        },
        (error) => {
          this.err = error;
          console.log("Interest List Fail");
        }
      );
    },
    getlist(code) {
      console.log(code);

      this.getDetail(code);
      this.getDealList(code);
      this.$router.push({ name: "houseDetail" });
    },

    btnClick(no) {
      console.log(no);
      uninterest(
        no,
        ({ data }) => {
          console.log("interest delete", data);
          this.interestList = [];
          this.getInterests();
        },
        (error) => {
          console.log(error);
        }
      );
    },

    move2Detail() {
      // this.CLAER_HOUSE_POINT();
      this.$router.push({ name: "houseDetail" });
    },
    move2Compare() {
      console.log("move2Compare click");
      console.log("checkList", this.checkList);
      let list = this.checkList;
      this.$router.push({ name: "houseCompare", params: { list } });
    },
  },
};
</script>

<style></style>
