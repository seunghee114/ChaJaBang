<template>
  <div>
    <h3>{{ house.apartmentName }}</h3>
    <h5>건축년도 : {{ house.buildYear }}</h5>
    <div ref="map" class="map_wrap">
      <div ref="overlay"></div>
      <div
        id="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      ></div>
    </div>
    <v-row>
      <v-col>
        <v-card>
          <v-toolbar color="white" flat>
            <v-row>
              <v-col></v-col><v-spacer></v-spacer>
              <v-col>
                <div style="display: flex">
                  <v-btn small @click="clickTraffic">교통 정보 </v-btn>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <v-btn small @click="clickBicycle">자전거도로</v-btn>
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <v-btn small @click="removeMarker">초기화</v-btn>
                </div>
              </v-col>
            </v-row>
            <template v-slot:extension>
              <v-tabs v-model="tab" align-with-title color="indigo lighten-2">
                <v-tabs-slider color="indigo lighten-2"></v-tabs-slider>

                <v-tab> 맞춤 정보 </v-tab>
                <v-tab> 자주 가는 곳 </v-tab>
                <v-tab> 길찾기 </v-tab>
              </v-tabs>
            </template>
          </v-toolbar>

          <v-tabs-items v-model="tab">
            <v-tab-item>
              <v-card flat>
                <v-container fluid>
                  <v-row>
                    <v-col cols="12"> <v-divider></v-divider> </v-col>
                    <v-col cols="12"><h3>주변 편의 시설</h3></v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['MT1']"
                      ><v-btn @click="categoryFacility('MT1')">대형마트</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['CS2']"
                      ><v-btn @click="categoryFacility('CS2')">편의점</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['BK9']"
                      ><v-btn @click="categoryFacility('BK9')">은행</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['CT1']"
                      ><v-btn @click="categoryFacility('CT1')">문화시설</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['PO3']"
                      ><v-btn @click="categoryFacility('PO3')">공공기관</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['FD6']"
                      ><v-btn @click="categoryFacility('FD6')">음식점</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['CE7']"
                      ><v-btn @click="categoryFacility('CE7')">카페</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['HP8']"
                      ><v-btn @click="categoryFacility('HP8')">병원</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['PM9']"
                      ><v-btn @click="categoryFacility('PM9')">약국</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4"
                      ><v-btn @click="keywordFacility('올리브영')"
                        >올리브영</v-btn
                      >
                    </v-col>
                    <v-col cols="12" sm="4" md="4"
                      ><v-btn @click="keywordFacility('스타벅스')"
                        >스타벅스</v-btn
                      >
                    </v-col>
                    <v-col cols="12" sm="4" md="4"
                      ><v-btn @click="keywordFacility('헬스장')">헬스장</v-btn>
                    </v-col>
                  </v-row>
                </v-container>
                <v-container fluid>
                  <v-row v-if="checkedTypes.includes('car')">
                    <v-col cols="12"> <v-divider></v-divider> </v-col>
                    <v-col cols="12"><h3>자동차</h3> </v-col>
                    <v-col cols="12" sm="6" md="6" v-model="categoryList['PK6']"
                      ><v-btn @click="categoryFacility('PK6')">주차장</v-btn>
                    </v-col>
                    <v-col cols="12" sm="6" md="6" v-model="categoryList['OL7']"
                      ><v-btn @click="categoryFacility('OL7')">주유소</v-btn>
                    </v-col>
                  </v-row>
                </v-container>
                <v-container fluid>
                  <v-row v-if="checkedTypes.includes('pet')">
                    <v-col cols="12"> <v-divider></v-divider> </v-col>
                    <v-col cols="12"><h3>반려동물</h3></v-col>
                    <v-col
                      cols="12"
                      sm="4"
                      md="4"
                      v-model="keywordList['동물병원']"
                      ><v-btn @click="keywordFacility('동물병원')"
                        >동물병원</v-btn
                      >
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="keywordList['공원']"
                      ><v-btn @click="keywordFacility('공원')">공원</v-btn>
                    </v-col>
                    <v-col
                      cols="12"
                      sm="4"
                      md="4"
                      v-model="keywordList['반려동물용품점']"
                      ><v-btn @click="keywordFacility('반려동물용품점')"
                        >반려동물용품점</v-btn
                      >
                    </v-col>
                  </v-row>
                </v-container>
                <v-container fluid>
                  <v-row v-if="checkedTypes.includes('baby')">
                    <v-col cols="12"> <v-divider></v-divider> </v-col>
                    <v-col cols="12"><h3>아이</h3></v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['PS3']"
                      ><v-btn @click="categoryFacility('PS3')">유치원</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['SC4']"
                      ><v-btn @click="categoryFacility('SC4')">학교</v-btn>
                    </v-col>
                    <v-col cols="12" sm="4" md="4" v-model="categoryList['AC5']"
                      ><v-btn @click="categoryFacility('AC5')">학원</v-btn>
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
                      <v-simple-table
                        fixed-header
                        max-width="580"
                        min-height="350"
                      >
                        <template v-slot:default>
                          <thead>
                            <tr>
                              <th class="text-left">이름</th>
                              <th class="text-left">거리</th>
                              <th class="text-left">소요 시간</th>
                              <th class="text-left">도착시간</th>
                              <th class="text-left">택시비</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr v-for="item in freResult" :key="item.nickname">
                              <td>{{ item.nickname }}</td>
                              <td>{{ item.summary.distance | distance }}</td>
                              <td>{{ item.summary.duration | time }}</td>
                              <td>{{ item.summary.departureTime }}</td>
                              <td>{{ item.summary.taxiFare }}원</td>
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
                    <v-col cols="12"> <v-divider></v-divider> </v-col>
                    <v-col cols="12" v-if="searchData != null">
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title>{{
                            searchData.message
                          }}</v-list-item-title>
                          <v-list-item-subtitle>{{
                            searchData.currentDateTime
                          }}</v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                      <v-list-item>
                        <v-list-item-content>
                          <v-list-item-title>guide</v-list-item-title>
                          <v-simple-table fixed-header height="300px">
                            <template v-slot:default>
                              <thead>
                                <tr>
                                  <th class="text-left">instructions</th>
                                  <th class="text-left">이동거리</th>
                                  <th class="text-left">예상 소요시간</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr
                                  v-for="(item, idx) in searchRoute.guide"
                                  :key="idx"
                                >
                                  <td>{{ item.instructions }}</td>
                                  <td>{{ item.distance | distance }}</td>
                                  <td>{{ item.duration | time }}</td>
                                </tr>
                              </tbody>
                            </template>
                          </v-simple-table>
                        </v-list-item-content>
                      </v-list-item>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title>예상 도착 시간</v-list-item-title>
                          <v-list-item-subtitle>
                            {{ searchRoute.summary.departureTime }}
                          </v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title>총 이동 거리</v-list-item-title>
                          <v-list-item-subtitle>
                            {{ searchRoute.summary.distance | distance }}
                          </v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title>예상 소요 시간</v-list-item-title>
                          <v-list-item-subtitle>
                            {{ searchRoute.summary.duration | time }}
                          </v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title>예상 유류비</v-list-item-title>
                          <v-list-item-subtitle>
                            {{ searchRoute.summary.fuelPrice }}원
                          </v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                      <v-list-item two-line>
                        <v-list-item-content>
                          <v-list-item-title>예상 택시비</v-list-item-title>
                          <v-list-item-subtitle>
                            {{ searchRoute.summary.taxiFare }}원
                          </v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
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
import http from "@/api/http";
import { mapState } from "vuex";
const compareStore = "compareStore";
export default {
  props: ["checkeditem"],
  data() {
    return {
      house: null,
      ino: null,
      tab: null,
      mapInstance: null,
      ps: null,
      start: null,
      startLatlng: null,

      // 마커
      markers: [],
      homeSrc: "https://cdn-icons-png.flaticon.com/512/4827/4827543.png",
      homeSize: null,
      homeOption: null,
      homeImg: null,
      //교통정보, 자전거도로정보
      isTraffic: false,
      isBicycle: false,

      // 길찾기 결과
      searchData: null,
      searchRoute: null,

      // 편의시설
      keywordList: {},
      categoryList: {},

      // 자주 가는 곳과 현재 아파트 위치와의 비교
      freResult: [],
    };
  },
  created() {
    this.house = this.checkeditem.data;
    this.ino = this.checkeditem.ino;
    console.log("this.house", this.house);
    console.log("this.ino ", this.ino);
  },
  computed: {
    ...mapState(compareStore, [
      "checkedTypes",
      "goalLatlng",
      "checkedOpt",
      "checkedFuel",
      "freLoc",
      "isChange",
    ]),
  },
  watch: {
    freResult() {
      this.removeMarker();
    },
    searchData() {},
    checkedTypes() {},
    isChange() {
      // 자주 가는 장소
      console.log("getFreResult,", this.freLoc.length);
      this.getFreResult();
      // 길찾기
      this.getSearch();
      this.isTraffic = false;
      this.isBicycle = false;

      // 길찾기 결과
      this.searchData = null;
      this.searchRoute = null;

      // 편의시설
      this.keywordList = {};
      this.categoryList = {};

      // 자주 가는 곳과 현재 아파트 위치와의 비교
      this.freResult = [];
    },
    house() {
      console.log("house watch");
      let kakao = window.kakao;

      var container = this.$refs.map;

      this.mapInstance = new kakao.maps.Map(container, {
        center: new kakao.maps.LatLng(this.house.lat, this.house.lng),
        level: 5,
      }); //지도 생성 및 객체 리턴

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places(this.mapInstance);

      this.homeSize = new kakao.maps.Size(50, 50);
      this.homeOption = { offset: new kakao.maps.Point(18, 50) };
      this.homeImg = new kakao.maps.MarkerImage(
        this.homeSrc,
        this.homeSize,
        this.homeOption
      );

      // start marker 띄우기
      this.startLatlng = new kakao.maps.LatLng(this.house.lat, this.house.lng);
      console.log("startLatlng", this.startLatlng);
      this.start = new window.kakao.maps.Marker({
        position: this.startLatlng, // 마커의 위치
        image: this.homeImg, // 마커 이미지 설정
      });
      this.addMarker(this.start);

      //편의시설 리스트 가져오기
      this.facility();
      this.uncheckedCar();
      this.checkedCar();
      this.checkedBicycle();
      this.checkedBaby();
      this.checkedPet();
    },
  },

  mounted() {},
  methods: {
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(marker) {
      marker.setMap(this.mapInstance); // 지도 위에 마커를 표출합니다
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },

    // 카테고리 검색을 요청하는 함수입니다
    categorySearchPlaces(category) {
      let _this = this;
      this.ps.categorySearch(
        category,
        (result, status) => {
          if (status === window.kakao.maps.services.Status.OK) {
            // console.log(result);
            // console.log("category", category);
            _this.categoryList[category] = result;
            return result;
          } else {
            console.log(status);
            // console.log("category", category);
          }
        },
        {
          // Map 객체를 지정하지 않았으므로 좌표객체를 생성하여 넘겨준다.
          location: new window.kakao.maps.LatLng(
            _this.startLatlng.Ma,
            _this.startLatlng.La
          ),
          useMapCenter: false,
          radius: 500,
        }
      );
    },
    // 키워드 검색을 요청하는 함수입니다
    keywordSearchPlaces(keyword) {
      // 지도에 표시되고 있는 마커를 제거합니다
      // this.removeMarker();

      let _this = this;
      this.ps.keywordSearch(
        keyword,
        (result, status) => {
          if (status === window.kakao.maps.services.Status.OK) {
            // console.log(result);
            // console.log("category", category);
            _this.keywordList[keyword] = result;
            return result;
          } else {
            console.log(status);
            // console.log("category", category);
          }
        },
        {
          // Map 객체를 지정하지 않았으므로 좌표객체를 생성하여 넘겨준다.
          location: new window.kakao.maps.LatLng(
            _this.startLatlng.Ma,
            _this.startLatlng.La
          ),
          useMapCenter: false,
          radius: 500,
        }
      );
    },

    checkedCar() {
      //주차장
      this.categorySearchPlaces("PK6");
      //주유소
      this.categorySearchPlaces("OL7");
    },
    uncheckedCar() {
      //지하철역
      this.categorySearchPlaces("SW8");
    },
    checkedBicycle() {},
    checkedPet() {
      // 동물병원
      this.keywordSearchPlaces("동물병원");
      // 공원
      this.keywordSearchPlaces("공원");
      // 반려동물용품점
      this.keywordSearchPlaces("반려동물용품점");
    },
    checkedBaby() {
      //유치원 어린이집
      this.categorySearchPlaces("PS3");
      //학교
      this.categorySearchPlaces("SC4");
      //학원
      this.categorySearchPlaces("AC5");
    },
    facility() {
      //대형마트
      this.categorySearchPlaces("MT1");
      //편의점
      this.categorySearchPlaces("CS2");
      //은행
      this.categorySearchPlaces("BK9");
      //문화시설
      this.categorySearchPlaces("CT1");
      //공공기관
      this.categorySearchPlaces("PO3");
      //음식점
      this.categorySearchPlaces("FD6");
      //카페
      this.categorySearchPlaces("CE7");
      //병원
      this.categorySearchPlaces("HP8");
      //약국
      this.categorySearchPlaces("PM9");
      //올리브영
      this.keywordSearchPlaces("올리브영");
      //스타벅스
      this.keywordSearchPlaces("스타벅스");
      //헬스장
      this.keywordSearchPlaces("헬스장");
    },

    // 편의 시설
    categoryFacility(category) {
      this.removeMarker();
      if (this.categoryList[category] == undefined) {
        alert("반경 500m 내에 해당 시설이 없어요 (๑•́ㅿ•̀๑) ᔆᵒʳʳᵞ");
      } else {
        console.log(category, this.categoryList[category]);
        this.removeMarker();
        for (let i = 0; i < this.categoryList[category].length; i++) {
          let newMarker = new window.kakao.maps.Marker({
            position: new window.kakao.maps.LatLng(
              this.categoryList[category][i].y,
              this.categoryList[category][i].x
            ), // 마커의 위치
          });
          this.markers.push(newMarker);
          this.addMarker(newMarker);
        }
      }
    },
    keywordFacility(keyword) {
      this.removeMarker();
      if (this.keywordList[keyword] == undefined) {
        alert("반경 500m 내에 해당 시설이 없어요 (๑•́ㅿ•̀๑) ᔆᵒʳʳᵞ");
      } else {
        console.log(keyword, this.keywordList[keyword]);

        for (let i = 0; i < this.keywordList[keyword].length; i++) {
          let newMarker = new window.kakao.maps.Marker({
            position: new window.kakao.maps.LatLng(
              this.keywordList[keyword][i].y,
              this.keywordList[keyword][i].x
            ), // 마커의 위치
          });
          this.markers.push(newMarker);
          this.addMarker(newMarker);
        }
      }
    },

    // 길찾기 하는 함수
    async getDir(end, fuel, opt) {
      console.log("start : ", this.startLatlng.La);
      console.log("end : ", end);
      let url = "/naver/road/";
      url += this.startLatlng.La + "," + this.startLatlng.Ma + "/";
      url += end.La + "," + end.Ma + "/";
      url += fuel + "/";
      url += opt;
      console.log("url", url);

      let resp = await http.get(url);
      console.log(resp);
      return resp.data;
    },
    // 자주 가는 곳에 대한 정보 가져오기
    async getFreResult() {
      // 길찾기를 한다.
      console.log("freLoc", this.freLoc);
      let tempList = [];
      for (var i = 0; i < this.freLoc.length; i++) {
        let temp = await this.getDir(this.freLoc[i].loc, "gasoline", "trafast");
        tempList.push({
          nickname: this.freLoc[i].nickname,
          summary: temp.route.trafast[0].summary,
        });
      }
      this.freResult = tempList;
    },
    async getSearch() {
      this.searchData = await this.getDir(
        this.goalLatlng,
        this.checkedFuel,
        this.checkedOpt
      );
      console.log("searchData keys", Object.keys(this.searchData.route));
      let key = Object.keys(this.searchData.route);
      this.searchRoute = this.searchData.route[key][0];
      console.log("searchRoute", this.searchRoute);
    },
    clickTraffic() {
      if (!this.isTraffic) {
        console.log("addTraffic click");
        // 지도에 교통정보를 표시하도록 지도타입을 추가합니다
        this.mapInstance.addOverlayMapTypeId(
          window.kakao.maps.MapTypeId.TRAFFIC
        );
        this.isTraffic = true;
      } else {
        console.log("removeTraffic click");
        //교통정보 지도타입을 제거합니다
        this.mapInstance.removeOverlayMapTypeId(
          window.kakao.maps.MapTypeId.TRAFFIC
        );
        this.isTraffic = false;
      }
    },
    clickBicycle() {
      if (!this.isBicycle) {
        console.log("addBicycle click");
        // 지도에 자전거도로를 표시하도록 지도타입을 추가합니다
        this.mapInstance.addOverlayMapTypeId(
          window.kakao.maps.MapTypeId.BICYCLE
        );
        this.isBicycle = true;
      } else {
        console.log("removeBicycle click");
        //자전거 지도타입을 제거합니다
        this.mapInstance.removeOverlayMapTypeId(
          window.kakao.maps.MapTypeId.BICYCLE
        );
        this.isBicycle = false;
      }
    },
  },
  filters: {
    time: function (value) {
      let svalue = value / 1000;
      svalue /= 60;
      return "약 " + Math.ceil(svalue) + "분";
    },
    distance: function (value) {
      let temp = Math.ceil(value / 100);
      return temp / 10 + "km";
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
