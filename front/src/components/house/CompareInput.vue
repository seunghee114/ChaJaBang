<template>
  <div>
    <div ref="map" class="map_wrap">
      <div ref="overlay"></div>
      <div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden"></div>
    </div>
    <v-row>
      <v-col>
        <v-card>
          <v-toolbar color="white" flat>
            <template v-slot:extension>
              <v-tabs v-model="tab" align-with-title color="indigo lighten-2">
                <v-tabs-slider color="indigo lighten-2"></v-tabs-slider>

                <v-tab> 맞춤 정보 </v-tab>
                <v-tab> 자주 가는 곳 </v-tab>
                <v-tab> 길찾기 </v-tab>
              </v-tabs>
              <v-btn @click="compareClick">비교</v-btn>
              <v-btn @click="resetClick">초기화</v-btn>
            </template>
          </v-toolbar>

          <v-tabs-items v-model="tab">
            <v-tab-item>
              <v-card flat>
                <v-container fluid>
                  <v-row>
                    <v-col cols="12" sm="3" md="3">
                      <v-checkbox
                        v-model="checkedType"
                        label="자동차"
                        color="orange darken-3"
                        value="car"
                        hide-details
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-checkbox
                        v-model="checkedType"
                        label="자전거"
                        color="orange darken-3"
                        value="bicycle"
                        hide-details
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-checkbox
                        v-model="checkedType"
                        label="반려동물"
                        color="orange darken-3"
                        value="pet"
                        hide-details
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-checkbox
                        v-model="checkedType"
                        label="아이"
                        color="orange darken-3"
                        value="baby"
                        hide-details
                      ></v-checkbox>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card>
            </v-tab-item>
            <v-tab-item>
              <v-card flat>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      현재 클릭한 지점을 자주 가는 곳에 추가 &nbsp;&nbsp;&nbsp;
                    </v-col>
                    <v-col cols="12" md="7">
                      <v-text-field v-model="nickname"></v-text-field>
                    </v-col>
                    <v-col cols="12" md="5">
                      <v-btn @click="freRegister">등록</v-btn>
                    </v-col>
                  </v-row>
                  <v-row v-if="freLoc.length != 0">
                    <v-col cols="12"><v-divider></v-divider></v-col>
                    <v-col cols="12">
                      <v-simple-table fixed-header max-width="580" min-height="350">
                        <template v-slot:default>
                          <thead>
                            <tr>
                              <th class="text-left">이름</th>
                              <th class="text-left">lat</th>
                              <th class="text-left">lng</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr v-for="item in freLoc" :key="item.nickname">
                              <td>{{ item.nickname }}</td>
                              <td>{{ item.loc.Ma }}</td>
                              <td>{{ item.loc.La }}</td>
                            </tr>
                          </tbody>
                        </template>
                      </v-simple-table>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card>
            </v-tab-item>
            <v-tab-item>
              <v-card flat>
                <v-container fluid>
                  <v-row>
                    <v-col cols="12">탐색 옵션</v-col>
                    <v-col cols="12" sm="4" md="4">
                      <v-checkbox
                        v-model="checkedOpt"
                        value="trafast"
                        label="실시간 빠른길"
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="4" md="4">
                      <v-checkbox
                        v-model="checkedOpt"
                        value="tracomfort"
                        label="실시간 편한길"
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="4" md="4">
                      <v-checkbox
                        v-model="checkedOpt"
                        value="traoptimal"
                        label="실시간 최적"
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="4" md="4">
                      <v-checkbox
                        v-model="checkedOpt"
                        value="traavoidtoll"
                        label="무료 우선"
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="4" md="4">
                      <v-checkbox
                        v-model="checkedOpt"
                        value="traavoidcaronly"
                        label="자동차 전용도로 회피 우선"
                      ></v-checkbox>
                    </v-col>
                  </v-row>
                </v-container>
                <v-container fluid>
                  <v-row>
                    <v-col cols="12">유류비 계산용 유종</v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-checkbox
                        v-model="checkedFuel"
                        value="gasoline"
                        label="휘발유"
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-checkbox
                        v-model="checkedFuel"
                        value="highgradegasoline"
                        label="고급 휘발유"
                      ></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-checkbox v-model="checkedFuel" value="diesel" label="경유"></v-checkbox>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-checkbox v-model="checkedFuel" value="lpg" label="LPG "></v-checkbox>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
const compareStore = "compareStore";
export default {
  props: ["checkList"],
  data() {
    return {
      house: null,
      ino: null,
      tab: null,
      mapInstance: null,
      ps: null,

      homeSrc: "https://cdn-icons-png.flaticon.com/512/4827/4827543.png",
      homeSize: null,
      homeOption: null,
      homeImg: null,

      goal: null,
      goalLatlng: null,
      // 길찾기할 때 경로 우선순위
      checkedOpt: "",
      // 경로 유류비 계산할 때 필요한
      checkedFuel: "",

      // 체크박스 중 체크 된 것
      checkedType: [],
      // 자주 가는 곳 별명
      nickname: "",
      // 자주 가는 곳으로 추가된 마커
      freLoc: [],
      // 자주 가는 곳과 현재 아파트 위치와의 비교
      freResult: [],
    };
  },
  created() {
    console.log("CompareInput created checkList", this.checkList);
  },
  computed: {},
  watch: {
    checkList() {
      console.log("checklist watch");
    },
  },
  mounted() {
    let kakao = window.kakao;

    var container = this.$refs.map;

    this.mapInstance = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(this.checkList[0].data.lat, this.checkList[0].data.lng),
      level: 5,
    }); //지도 생성 및 객체 리턴

    this.homeSize = new kakao.maps.Size(50, 50);
    this.homeOption = { offset: new kakao.maps.Point(18, 50) };
    this.homeImg = new kakao.maps.MarkerImage(this.homeSrc, this.homeSize, this.homeOption);

    // checkList 마커 띄우기
    let markerPositions = [];
    for (var i = 0; i < this.checkList.length; i++) {
      markerPositions.push([this.checkList[i].data.lat, this.checkList[i].data.lng]);
    }
    this.displayMarker(markerPositions);

    // 지도에 클릭 이벤트를 등록합니다
    // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
    let _this = this;
    new kakao.maps.event.addListener(this.mapInstance, "click", function (mouseEvent) {
      // 클릭한 위도, 경도 정보를 가져옵니다
      let latlng = new kakao.maps.LatLng(mouseEvent.latLng.getLat(), mouseEvent.latLng.getLng());
      _this.goalLatlng = latlng;
      if (_this.goal != null) {
        _this.removeMarker();
      }
      _this.goal = new window.kakao.maps.Marker({
        position: latlng, // 마커의 위치
      });
      _this.addMarker(_this.goal);
      // this.addMarker(new kakao.maps.LatLng(this.latlng.getLat(), this.latlng.getLng()));
    });
  },
  methods: {
    ...mapMutations(compareStore, [
      "SET_GOAL_LATLNG",
      "SET_CHECKED_OPT",
      "SET_CHECKED_FUEL",
      "SET_CHECKED_TYPES",
      "SET_FRE_LOC",
      "SET_IS_CHANGE",
    ]),
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(marker) {
      marker.setMap(this.mapInstance); // 지도 위에 마커를 표출합니다
    },
    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      this.goal.setMap(null);
    },
    displayMarker(markerPositions) {
      console.log("displayMarker", markerPositions);
      const positions = markerPositions.map(
        (position) => new window.kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new window.kakao.maps.Marker({
              map: this.mapInstance,
              position,
              image: this.homeImg, // 마커 이미지 설정
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new window.kakao.maps.LatLngBounds()
        );

        this.mapInstance.setBounds(bounds);
      }
    },
    move2Table() {
      this.$router.push({ name: "houseTable" });
    },

    //자주가는 곳 탭에서 등록을 누르면
    freRegister() {
      this.freLoc.push({ nickname: this.nickname, loc: this.goalLatlng });
      (this.nickname = ""), console.log("fre loc", this.freLoc);
    },
    compareClick() {
      this.SET_GOAL_LATLNG({ goalLL: this.goalLatlng });
      this.SET_CHECKED_OPT({ opt: this.checkedOpt });
      this.SET_CHECKED_FUEL({ fuel: this.checkedFuel });
      this.SET_CHECKED_TYPES({ types: this.checkedType });
      this.SET_FRE_LOC({ list: this.freLoc });
      this.SET_IS_CHANGE();
    },
    resetClick() {
      this.removeMarker();
      this.goalLatlng = null;
      this.checkedOpt = "";
      this.checkedFuel = "";
      this.checkedType = [];
      this.freLoc = [];
      this.SET_GOAL_LATLNG({ goalLL: this.goalLatlng });
      this.SET_CHECKED_OPT({ opt: this.checkedOpt });
      this.SET_CHECKED_FUEL({ fuel: this.checkedFuel });
      this.SET_CHECKED_TYPES({ types: this.checkedType });
      this.SET_FRE_LOC({ list: this.freLoc });
      this.SET_IS_CHANGE();
    },
  },
};
</script>

<style>
.map {
  widows: 100%;
  height: 600px;
}

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
</style>
