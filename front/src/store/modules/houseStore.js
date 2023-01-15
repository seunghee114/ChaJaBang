import { sidoList, gugunList, dongList, houseList, DealList, houseDetail } from "@/api/house.js";


const houseStore = {
  namespaced: true,
  state: {
    sidos: [],
    guguns: [],
    dongs: [],
    houses: [],
    house: null,
    deals: [],
    points: [], 
    point: [],
    sido: "",
    gugun:"",
  },
  getters: {
  },
  mutations: {
  
    // House
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push(sido);
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push(gugun);
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push(dong);
      });
    },
    SET_SIDO_GUGUN(state, { sidoName, gugunName }) {
      state.sido = sidoName;
      state.gugun = gugunName;
    },
    CLEAR_SIDO_GUGUN(state) {
      state.sido = null;
      state.gugun = null;
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      // state.house = null;
      state.points = [];

    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
      state.points = [];
      state.house = null;
      houses.forEach((house) => {
        state.points.push([house.lat, house.lng]);
        console.log(house.lat);
      })
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
      state.point = [];
      state.point.push([house.lat, house.lng]);
    },
    SET_DEAL_LIST(state, deals) {
      state.deals = [];
      deals.forEach((deal) => {
        state.deals.push(deal);
      });
    },
    CLEAR_HOUSE_POINT(state) {
      state.point = [];
    }
  
  },
  actions: { //백엔드랑 통신하는 비동기 처리
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        });
    },
    getGugun: ({ commit }, sidoName) => {
      const params = sidoName;
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, { sidoName, gugunName }) => {
      const params = { sidoName, gugunName };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      )
    },

    getHouseList:({ commit }, { sidoName, gugunName, dongName })=> {
      const params = {sidoName, gugunName, dongName}

      houseList(
        params,
        ({ data }) => {
          console.log(data);
          // console.log(data[0].apartmentName);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    getDetail({ commit }, aptCode){
      const params = aptCode;
      houseDetail(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_DETAIL_HOUSE", data);
        },
        (error) => {
          console.log(error);
        }
      )
    },

    getDealList({ commit }, aptCode) {
      const param = aptCode;
      DealList(
        param,
        ({ data }) => {
          console.log("aptDealList", data);
          commit("SET_DEAL_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      )
    }

  },
};
export default houseStore;
