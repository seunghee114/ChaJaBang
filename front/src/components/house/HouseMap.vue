<template>
  <div id="app">
    <v-row>
      <v-col cols="12" md="2"></v-col>
      <v-col cols="12" md="4">
        <v-btn @click="clickTraffic"
          >교통 정보 &nbsp;
          <v-img
            class="image-btn"
            :src="require(`@/assets/car.png`)"
          ></v-img> </v-btn
      ></v-col>
      <v-col cols="12" md="4"
        ><v-btn @click="clickBicycle"
          >자전거도로&nbsp;
          <v-img
            class="image-btn"
            :src="require(`@/assets/bike.png`)"
          ></v-img> </v-btn
      ></v-col>
      <v-col cols="12" md="2"></v-col>
    </v-row>
    <br />
    <v-card id="map" class="mx-auto" max-width="580" height="590">
      <!-- <div id="map"></div> -->
    </v-card>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      markerPositions: [],
      markers: [],
      infowindow: null,
      ps: null,
      map: null,
      isTraffic: false,
      isBicycle: false,
      loc2: {
        lat: "",
        lng: "",
      },
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false?appkey=78b26667d20a38aa946ea1f6a8384730&libraries=services";
      document.head.appendChild(script);
    }
  },
  created() {},
  computed: {
    ...mapState(houseStore, ["houses", "points", "point"]),
  },
  watch: {
    points() {
      // this.$forceUpdate();
      this.map = null;
      if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement("script");

        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false?appkey=78b26667d20a38aa946ea1f6a8384730&libraries=services";
        document.head.appendChild(script);
      }
    },
    point() {
      // detail map 보여주는 곳
      const mapContainer = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(36.355142, 127.298514),
        level: 5,
      };

      this.map = new kakao.maps.Map(mapContainer, options);
      console.log(this.point);
      if (this.point.length != 0) {
        console.log("point : ", this.point);
        this.displayMarker(this.point);
      } else {
        console.log("points : ", this.points);
        this.displayMarker(this.points);
      }
    },
  },
  methods: {
    initMap() {
      this.removeMarker();

      const mapContainer = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(36.355142, 127.298514),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(mapContainer, options);
      this.displayMarker(this.points);
      ///////////////////////////////////////////////////////////////////////
    },
    displayMarker(markerPositions) {
      console.log("display Marker", markerPositions);
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    clickTraffic() {
      if (!this.isTraffic) {
        console.log("addTraffic click");
        // 지도에 교통정보를 표시하도록 지도타입을 추가합니다
        this.map.addOverlayMapTypeId(window.kakao.maps.MapTypeId.TRAFFIC);
        this.isTraffic = true;
      } else {
        console.log("removeTraffic click");
        //교통정보 지도타입을 제거합니다
        this.map.removeOverlayMapTypeId(window.kakao.maps.MapTypeId.TRAFFIC);
        this.isTraffic = false;
      }
    },
    clickBicycle() {
      if (!this.isBicycle) {
        console.log("addBicycle click");
        // 지도에 자전거도로를 표시하도록 지도타입을 추가합니다
        this.map.addOverlayMapTypeId(window.kakao.maps.MapTypeId.BICYCLE);
        this.isBicycle = true;
      } else {
        console.log("removeBicycle click");
        //자전거 지도타입을 제거합니다
        this.map.removeOverlayMapTypeId(window.kakao.maps.MapTypeId.BICYCLE);
        this.isBicycle = false;
      }
    },
  },
};
</script>

<style>
#t {
  border: 2px;
  border-color: blue;
}
.image-btn {
  height: 30px;
  width: 30px;
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

#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}

#category li {
  float: left;
  list-style: none;
  width: 60px;

  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}

#category li.on {
  background: #eee;
}

#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}

#category li:last-child {
  margin-right: 0;
  border-right: 0;
}

#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}

#category li .category_bg {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png")
    no-repeat;
}

#category li .bank {
  background-position: -10px 0;
}

#category li .mart {
  background-position: -10px -36px;
}

#category li .pharmacy {
  background-position: -10px -72px;
}

#category li .oil {
  background-position: -10px -108px;
}

#category li .cafe {
  background-position: -10px -144px;
}

#category li .store {
  background-position: -10px -180px;
}

#category li.on .category_bg {
  background-position-x: -46px;
}

.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}

.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}

.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}

.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}

.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png")
    no-repeat right 14px center;
}

.placeinfo .tel {
  color: #0f7833;
}

.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
