import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "../views/MainView.vue";
import HouseView from "../views/HouseView.vue";
import BoardView from "../views/BoardView.vue";
import BoardWrite from "../components/board/BoardWrite.vue";
import BoardList from "../components/board/BoardList.vue";
import BoardNotice from "../components/board/alpha/BoardNotice.vue";
import BoardDetail from "../components/board/BoardDetail.vue";
import UserLogin from "@/components/user/UserLogin";
import UserMyPage from "@/components/user/UserMyPage";
import UserRegister from "@/components/user/UserRegister";
import BoardModify from "@/components/board/BoardModify.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import HouseTable from "@/components/house/HouseTable.vue";
import HouseNews from "@/components/house/HouseNews.vue";
import HouseCompareView from "@/views/HouseCompareView";

import BoardNoticeWrite from "@/components/board/alpha/BoardNoticeWrite";
import NoticeDetail from "@/components/board/alpha/NoticeDetail";
import NoticeModify from "@/components/board/alpha/NoticeModify";
import QnaList from "@/components/board/alpha/QnaList";
import QnaDetail from "@/components/board/alpha/QnaDetail";
import QnaModify from "@/components/board/alpha/QnaModify";
import QnaWrite from "@/components/board/alpha/QnaWrite";

import store from "@/store";

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/house",
    name: "house",
    component: HouseView,
    children: [
      {
        path: "",
        name: "houseTable",
        component: HouseTable,
      },
      {
        path: "aptdetail",
        name: "houseDetail",
        component: HouseDetail,
      },
      {
        path: "aptNews",
        name: "news",
        component: HouseNews,
      },
    ],
  },
  {
    path: "/housecomapre",
    name: "houseCompare",
    component: HouseCompareView,
  },
  {
    path: "/board",
    name: "board",
    component: BoardView,
    children: [
      {
        path: "",
        name: "boardList",
        component: BoardList,
      },
      {
        path: "write",
        name: "boardWrite",
        beforeEnter: onlyAuthUser,
        component: BoardWrite,
      },
      {
        path: "detail",
        name: "boardDetail",
        beforeEnter: onlyAuthUser,
        component: BoardDetail,
      },
      {
        path: "notice",
        name: "notice",
        beforeEnter: onlyAuthUser,
        component: BoardNotice,
      },
      {
        path: "modify",
        name: "boardModify",
        beforeEnter: onlyAuthUser,
        component: BoardModify,
      },
      {
        path: "nwrite",
        name: "noticeWrite",
        component: BoardNoticeWrite,
      },
      {
        path: "nmodify",
        name: "noticeModify",
        component: NoticeModify,
      },
      {
        path: "ndetail",
        name: "noticeDetail",
        beforeEnter: onlyAuthUser,
        component: NoticeDetail,
      },
      {
        path: "qna",
        name: "qna",
        beforeEnter: onlyAuthUser,
        component: QnaList,
      },
      {
        path: "qmodify",
        name: "qnaModify",
        component: QnaModify,
      },
      {
        path: "qwrite",
        name: "qnaWrite",
        beforeEnter: onlyAuthUser,
        component: QnaWrite,
      },
      {
        path: "qdetail",
        name: "qDetail",
        component: QnaDetail,
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView"),
    children: [
      {
        path: "join",
        name: "join",
        component: UserRegister,
      },
      {
        path: "login",
        name: "login",
        component: UserLogin,
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: UserMyPage,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
