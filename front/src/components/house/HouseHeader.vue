<template>
  <v-card
    style="text-align: center"
    title="House! 🎉"
    class="position-relative"
  >
    <v-card-text>
      <v-row>
        <v-col cols="12"> <h1 style="text-align: center">House</h1> </v-col>
      </v-row>
      <h5><br /></h5>
      <v-select
        class="select"
        label="시도"
        v-model="sidoName"
        :items="sidos"
        dense
        outlined
        placeholder="select"
        @change="gugunList"
      >
        <option v-for="sido in sidos" :key="sido" :value="sido">
          {{ sido }}
        </option>
      </v-select>
      <v-select
        class="select"
        label="구군"
        v-model="gugunName"
        :items="guguns"
        dense
        outlined
        placeholder="select"
        @change="dongList"
      >
        <option v-for="gugun in guguns" :key="gugun" :value="gugun">
          {{ gugun }}
        </option>
      </v-select>
      <v-select
        class="select"
        label="동"
        v-model="dongName"
        :items="dongs"
        dense
        outlined
        placeholder="select"
        @change="searchApt"
      >
        <option v-for="dong in dongs" :key="dong" :value="dong">
          {{ dong }}
        </option>
      </v-select>

      <v-row>
        <v-col cols="12" sm="4" md="4">
          <div class="text-center">
            <v-dialog v-model="dialog" width="600">
              <template v-slot:activator="{ on, attrs }">
                <v-btn color="" v-bind="attrs" v-on="on"> Accident </v-btn>
              </template>

              <v-card>
                <v-container>
                  <v-toolbar color="indigo" dark
                    >해당 지역 교통사고 정보</v-toolbar
                  >
                  <v-sparkline
                    :labels="labels"
                    :value="value"
                    :gradient="gradient"
                    :smooth="radius || false"
                    :padding="padding"
                    :line-width="width"
                    :stroke-linecap="lineCap"
                    :gradient-direction="gradientDirection"
                    :fill="fill"
                    :type="type"
                    :auto-line-width="autoLineWidth"
                    auto-draw
                  >
                  </v-sparkline>
                  <v-row style="text-align: center">
                    <v-col
                      ><v-btn small @click="changeChild"
                        >보행 어린이 사고 다발 지역</v-btn
                      ></v-col
                    ><v-spacer></v-spacer>
                    <v-col
                      ><v-btn small @click="changeOldman"
                        >보행 노인 사고 다발 지역</v-btn
                      ></v-col
                    ><v-spacer></v-spacer>
                    <v-col
                      ><v-btn small @click="changeBicycle"
                        >자전거 사고 다발지역</v-btn
                      ></v-col
                    >
                  </v-row>

                  <v-card-actions class="justify-end">
                    <v-btn text @click="dialog = false">Close</v-btn>
                  </v-card-actions>
                </v-container>
              </v-card>
            </v-dialog>
          </div>
        </v-col>
        <v-col cols="12" sm="4" md="4"
          ><v-btn size="small" @click="move2Home"> Home </v-btn></v-col
        >
        <v-col cols="12" sm="4" md="4"
          ><v-btn size="small" @click="move2News"> Local News</v-btn></v-col
        >
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { sidoGugunCode, getChild, getOldman, getBicycle } from "@/api/house";
/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }
*/
const houseStore = "houseStore";
const gradients = [
  ["#222"],
  ["#42b3f4"],
  ["red", "orange", "yellow"],
  ["purple", "violet"],
  ["#00c6ff", "#F0F", "#FF0"],
  ["#f72047", "#ffd200", "#1feaea"],
];
export default {
  name: "HouseHeader",
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,
      code: "",
      dialog: false,
      child: [],
      oldman: [],
      bicycle: [],
      ///////sparkline//////
      width: 2,
      radius: 10,
      padding: 8,
      lineCap: "round",
      gradient: gradients[5],
      labels: [
        "2012",
        "2013",
        "2014",
        "2015",
        "2016",
        "2017",
        "2018",
        "2019",
        "2020",
      ],
      value: [],
      gradientDirection: "top",
      gradients,
      fill: false,
      type: "trend",
      autoLineWidth: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  watch: {
    value() {},
  },

  created() {
    this.CLEAR_SIDO_LIST();
    // this.CLEAR_GUGUN_LIST();
    // this.CLEAR_DONG_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },

  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
    ]),
    ...mapMutations(houseStore, [
      "SET_SIDO_GUGUN",
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
    ]),

    gugunList() {
      // console.log(sidos)
      console.log(this.sidoName);
      this.CLEAR_GUGUN_LIST();
      this.gugunName = null;
      if (this.sidoName) this.getGugun(this.sidoName);
    },
    dongList() {
      console.log(this.gugunName);
      let param = {
        sidoName: this.sidoName,
        gugunName: this.gugunName,
      };
      let _this = this;
      sidoGugunCode(
        param,
        ({ data }) => {
          this.code = data;
          console.log("this code!!!!!!", this.code);
          getChild(
            this.code,
            ({ data }) => {
              console.log("getChild data", data);
              _this.child = data;
              _this.value = data;
            },
            (error) => {
              console.log("getChild error", error);
            }
          );
          getOldman(
            this.code,
            ({ data }) => {
              console.log("getOldman data", data);
              _this.oldman = data;
            },
            (error) => {
              console.log("getOldman error", error);
            }
          );
          getBicycle(
            this.code,
            ({ data }) => {
              console.log("getBicycle data", data);
              _this.bicycle = data;
            },
            (error) => {
              console.log("getBicycle error", error);
            }
          );
        },
        (error) => {
          console.log("sidogugun code error!!!!!!");
          console.log(error);
        }
      );

      this.CLEAR_DONG_LIST();
      this.dongName = null;
      if (this.gugunName) {
        this.getDong({ sidoName: this.sidoName, gugunName: this.gugunName });
        this.SET_SIDO_GUGUN(this.sidoName, this.gugunName);
      }
    },
    searchApt() {
      // this.CLEAR_APT_LIST();

      if (this.dongName) {
        this.getHouseList({
          sidoName: this.sidoName,
          gugunName: this.gugunName,
          dongName: this.dongName,
        });
        this.SET_SIDO_GUGUN({
          sidoName: this.sidoName,
          gugunName: this.gugunName,
        });
      }
      this.$router.push({ name: "houseTable" }).catch((error) => {
        if (error.name !== "NavigationDuplicated") {
          this.$router.go(this.$router.currentRoute);
        }
      });
    },

    move2Home() {
      this.$router.push({ name: "main" }).catch((error) => {
        if (error.name !== "NavigationDuplicated") {
          this.$router.go(this.$router.currentRoute);
        }
      });
      // this.CLEAR_APT_LIST();
    },
    move2News() {
      this.$router.push({ name: "news" }).catch((error) => {
        if (error.name !== "NavigationDuplicated") {
          this.$router.go(this.$router.currentRoute);
        }
      });
    },
    changeChild() {
      this.value = this.child;
    },
    changeOldman() {
      this.value = this.oldman;
    },
    changeBicycle() {
      this.value = this.bicycle;
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
  overflow-y: scroll;
}
</style>
