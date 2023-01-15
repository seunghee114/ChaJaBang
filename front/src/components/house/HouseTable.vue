<!--
https://vuetifyjs.com/en/components/simple-tables/#usage
-->
<template>
  <div>
    <h3>아파트 매물 목록</h3>
    <h6> <br></h6>
    <v-simple-table fixed-header max-width="580" height="590">
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">아파트 이름</th>
            <th class="text-left">건축년도</th>
            <th class="text-left">좋아요</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in houses" :key="item.aptCode">
            <td @click="getlist(item.aptCode)">{{ item.apartmentName }}</td>
            <td>{{ item.buildYear }}년</td>
            <td>
              <v-btn @click="btnClick(item.aptCode)"
                ><v-icon size="small" color="indigo lighten-1">mdi-heart</v-icon></v-btn
              >
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import {interest} from "@/api/house";

const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "HouseTable",
  data() {
    return {
      aptCode: null,
      getid : "",
    };
  },
  created() {
    this.CLEAR_HOUSE_POINT();
  },
  computed: {
    ...mapState(houseStore, ["houses", "points", "house", "deals"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {
    deals() {
      this.move2Detail();
    },
  },
  methods: {
    ...mapActions(houseStore, ["getDetail", "getDealList"]),
    ...mapMutations(houseStore, ["CLEAR_HOUSE_POINT", "CLEAR_APT_LIST"]),
    getlist(code) {
      console.log(code);
      this.getDetail(code);
      this.getDealList(code);
    },
    btnClick(code) {
      console.log("house table interest click", code);
      let id = this.userInfo.userid;
      // console.log(id);
      let param = {
        aptcode: code,
        userid: id,
      };
      console.log(param);
      
      interest(
        param,
        ({data})=>{
          console.log(data);
        },(error)=>{
          console.log(error);
        }
      )
    },
    move2Detail() {
      // this.CLEAR_HOUSE_POINT();
      this.$router.push({ name: "houseDetail" });
    },
  },
};
</script>

<style></style>
