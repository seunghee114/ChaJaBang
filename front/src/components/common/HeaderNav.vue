<!--
https://vuetifyjs.com/en/components/app-bars/#dense
-->
<template>
  <div>
    <v-app-bar color="indigo lighten-2" dense dark>
      <v-app-bar-nav-icon
        theme--right
        @click.stop="drawer = !drawer"
      ></v-app-bar-nav-icon>
      <v-toolbar-title class="px-0">
        <router-link
          to="/"
          id="none-a"
          class="d-flex align-center justify-center"
        >
          <v-img
            class="mr-1"
            height="30"
            width="30"
            :src="require(`@/assets/house.png`)"
          ></v-img>

          <div style="font-family: JuaRegular; font-size: 21pt">차자방</div>
        </router-link>
      </v-toolbar-title>

      <v-spacer></v-spacer>
      <v-menu bottom min-width="200px" rounded offset-y v-if="userInfo">
        <template v-slot:activator="{ on }">
          <v-btn icon v-on="on">
            <v-icon>mdi-heart</v-icon>
          </v-btn>
        </template>

        <v-card>
          <v-list-item-content class="justify-center">
            <div class="mx-auto text-center">
              <v-avatar color="indigo">
                <v-icon large color="white">mdi-account-circle</v-icon>
              </v-avatar>
              <h6><br /></h6>
              <h3>{{ userInfo.username }}</h3>
              <p class="text-caption mt-1"></p>
              <v-divider class="my-3"></v-divider>
              <v-btn depressed rounded text @click="move2MyPage">
                My Page
              </v-btn>
              <v-divider class="my-3"></v-divider>
              <v-btn depressed rounded text @click="onClickLogout">
                Logout
              </v-btn>
            </div>
          </v-list-item-content>
        </v-card>
      </v-menu>

      <div v-if="userInfo">
        <v-btn text @click="move2MyPage">MyPage</v-btn>
        <v-btn text @click.prevent="onClickLogout">Logout</v-btn>
      </div>
      <div v-else>
        <v-btn text @click="move2Login">Login</v-btn>
        <v-btn text @click="move2Register">Join</v-btn>
      </div>
    </v-app-bar>
    <v-navigation-drawer v-model="drawer" absolute left temporary>
      <v-list-item class="px-2" v-if="userInfo">
        <v-list-item-avatar>
          <v-img src="https://randomuser.me/api/portraits/men/85.jpg"></v-img>
        </v-list-item-avatar>

        <v-list-item-title>{{ userInfo.username }}</v-list-item-title>
        <v-btn @click="move2Qna">
          <v-icon color="red" v-if="nchQna.length > 0">mdi-access-point</v-icon>
          <v-icon colcr="grey" v-else-if="nchQna.length == 0"
            >mdi-access-point</v-icon
          >
        </v-btn>
        <v-btn icon @click="drawer = !drawer">
          <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item v-if="userInfo" @click="move2MyPage">
          <v-list-item-icon>
            <v-icon>mdi-badge-account-horizontal</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Mypage</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>

        <v-list-item
          v-for="item in items"
          :key="item.title"
          @click="move2Page(item)"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
const memberStore = "memberStore";
const boardStore = "boardStore";
export default {
  name: "HeaderNav",
  data() {
    return {
      drawer: false,
      group: null,
      items: [
        { title: "Home", icon: "mdi-home", path: "/" },

        { title: "Boards", icon: "mdi-clipboard-text", path: "/board" },
        { title: "Apart deals", icon: " mdi-domain", path: "/house" },
        { title: "Notice", icon: "mdi-bullhorn", path: "/board/notice" },
      ],
      mini: true,
      // id: "",
    };
  },
  watch: {
    group() {
      this.drawer = false;
    },
    userInfo(val) {
      console.log("watch userInfo:", val);
      if (val != null) {
        this.updateNckQna(val.userid);
      }
    },
    // check() {
    //   if (this.userInfo) {
    //     let tmp = this.userInfo;
    //     this.id = tmp.userid;
    //     if (this.id === "admin") {
    //       this.getManagerNotAnswer();
    //     } else {
    //       this.getUserNotCheck(this.id);
    //     }
    //   }
    // },
    nchQna(val) {
      console.log("watch nchQna", val);
    },
  },
  created() {
    if (this.userInfo) {
      let tmp = this.userInfo;
      this.id = tmp.userid;
    }
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapState(boardStore, ["nchQna"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    move2Register() {
      console.log("move register");
      this.$router.push({ name: "join" }).catch((error) => {
        if (error.name !== "NavigationDuplicated") {
          this.$router.go(this.$router.currentRoute);
        }
      });
    },
    move2Login() {
      console.log("move login");
      this.$router.push({ name: "login" }).catch((error) => {
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
    },
    move2MyPage() {
      console.log("move mypage");
      var tab = 0;
      this.$router.push({ name: "mypage", params: { tab } }).catch((error) => {
        if (error.name !== "NavigationDuplicated") {
          this.$router.go(this.$router.currentRoute);
        }
      });
    },
    move2Qna() {
      console.log("move mypage");
      var tab = 1;
      this.$router.push({ name: "mypage", params: { tab } }).catch((error) => {
        if (error.name !== "NavigationDuplicated") {
          this.$router.go(this.$router.currentRoute);
        }
      });
    },
    move2Page(el) {
      this.$router.push({ path: el.path }).catch((error) => {
        if (error.name !== "NavigationDuplicated") {
          this.$router.go(this.$router.currentRoute);
        }
      });
    },

    ...mapActions(memberStore, ["userLogout"]),
    ...mapActions(boardStore, ["getUserNotCheck", "getManagerNotAnswer"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/")
        this.$router.push({ name: "main" }).catch((error) => {
          if (error.name !== "NavigationDuplicated") {
            this.$router.go(this.$router.currentRoute);
          }
        });
    },
    updateNckQna(userid) {
      if (this.userInfo != null) {
        // let tmp = this.userInfo;
        // this.id = tmp.userid;
        if (userid === "admin") {
          this.getManagerNotAnswer();
        } else {
          this.getUserNotCheck(userid);
        }
      }
    },
  },
};
</script>

<style>
#none-a {
  text-decoration: none;
  color: white;
}
v-toolbar-title {
  font-family: "JuaRegular";
}
</style>
