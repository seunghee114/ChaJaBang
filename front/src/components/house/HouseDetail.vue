<template>
  <div>
    <h3>{{ house.apartmentName }}</h3>
    <h4>{{ house.buildYear }} 준공</h4>
    <v-btn @click="move2Table" class="ma-2" color="indigo darken-2" dark>
      <v-icon dark left> mdi-arrow-left </v-icon>Back
    </v-btn>

    <v-data-table :headers="headers" :items="deals" :search="search">
    </v-data-table>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  data() {
    return {
      aptCode: null,
      search: "",
      headers: [
        {
          text: "거래일",
          value: "dealDate",
        },
        {
          text: "층수(층)",
          value: "floor",
        },
        {
          text: "면적",
          value: "area",
        },
        {
          text: "거래 가격",
          value: "dealAmountStr",
        },
      ],
      value: [],
    };
  },
  created() {
    this.deals.forEach((element) => {
      element.dealDate =
        element.dealYear + "/" + element.dealMonth + "/" + element.dealDay;
      let c = element.dealAmount.replace(/,/g, "");
      let a = Math.floor(c / 10000);
      let b = c % 10000;
      if (b == 0) {
        element.dealAmountStr = a + "억";
      } else {
        element.dealAmountStr = a + "억 " + b + "만원";
      }
    });
    console.log("deals : ", this.deals);
  },
  computed: {
    ...mapState(houseStore, ["houses", "points", "house", "deals"]),
  },
  methods: {
    move2Table() {
      console.log(this.houses);
      if (this.houses.length > 0) {
        console.log("points : ", this.points);
        this.$router.push({ name: "houseTable" });
      } else {
        var tab = 2;
        this.$router.push({ name: "mypage", params: { tab } });
      }
    },
    clickRoad() {
      this.$router.push({ name: "houseRoad" });
    },
  },
  filters: {},
};
</script>

<style></style>
