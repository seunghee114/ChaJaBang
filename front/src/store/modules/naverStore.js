import http from "@/api/http";

const naverStore ={
  namespaced: true,

  state: {
    newsList: [],
    blogList: [],
    shopList: [],
    news3: [],
    localNews:[],
  },
  getters: {
    // 굳이 state에 데이터를 중복해서 저장할 필요는 없늗네 가공된 데이터를
    // 필요로 하는 컴포넌트들한테 제공하고 싶은 경우 
    // top3Items(state) {
    //   return state.shopwList.sort((a, b) => {
    //     return a.lprice - b.lprice
    //   }
    //   )
    //     .slice(0, 3);
    // },
    // threeNews(state) {
    //   return state.newsList.sort((a, b) => {
    //     return b.pubDate - a.pubDate
    //   }).slice(0, 3);
    // }
  },
  mutations: {
    // state에 접근 권한이 있어서 데이터 저장시키는 setter 역할 가능!
    SEARCH_NAVER_SHOP(state, payload) {
      state.shopList = payload;
    },
    SEARCH_NAVER_NEWS(state, payload) {
      state.newsList = payload;
      state.news3 = [];
      state.news3 = state.newsList.slice(0, 3);
      console.log(state.news3);
    },
    SEARCH_LOCAL_NEWS(state, payload) {
      state.localNews = payload;

      console.log(state.localNews);
    },
  },
  actions: {
    // 백엔드랑 통신하는 비동기 처리를 얘가 다함.
    searchNaverShop({ commit }, payload) {
      payload = "라면"
      http.get("naver/shop?keyword=" + payload).then(({ data }) => {
        console.log(data); // 백엔드에서 데이터 받았어! 직접 state에 넣지 말랬으니깡
        // console.log(commit, payload)
        commit("SEARCH_NAVER_SHOP", data.items); //state에 넣어줘!(기록도 잘 해주고!)
      });
    },
    searchNaverNews({ commit }, payload) {

      http.get("naver/news?keyword=" + payload).then(({ data }) => {
        console.log(data); // 백엔드에서 데이터 받았어! 직접 state에 넣지 말랬으니깡
        // console.log(commit, payload)
        commit("SEARCH_NAVER_NEWS", data.items); //state에 넣어줘!(기록도 잘 해주고!)
      });
    },
    searchLocalNews({ commit }, payload) {

      http.get("naver/news?keyword=" + payload).then(({ data }) => {
        console.log(data); // 백엔드에서 데이터 받았어! 직접 state에 넣지 말랬으니깡
        // console.log(commit, payload)
        commit("SEARCH_LOCAL_NEWS", data.items); //state에 넣어줘!(기록도 잘 해주고!)
      });
    },
  },
  modules: {
  },
}

export default naverStore;
  