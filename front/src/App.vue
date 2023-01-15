<template>
  <v-app>
    <header-nav />
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import HeaderNav from "./components/common/HeaderNav.vue";
export default {
  name: "App",
  components: {
    HeaderNav,
  },

  data: () => ({
    //
  }),
};
</script>
<style>

  @font-face {
    font-family:'NanumBarunGothic';
    src: url('@/assets/font/NanumBarunGothic.ttf');
    font-weight: 400;
  }
  @font-face {
    font-family:'NanumBarunGothic';
    src: url('@/assets/font/NanumBarunGothicLight.ttf') ;
    font-weight: 300;
  }
  @font-face {
    font-family:'NanumBarunGothic';
    src: url('@/assets/font/NanumBarunGothicBold.ttf');
    font-weight: 700;
  }

  @font-face {
    font-family:'NanumSquare';
    src: url('@/assets/font/NanumSquare_acB.ttf');
  }
  @font-face {
    font-family:'NanumSquare';
    src: url('@/assets/font/NanumSquare_acEB.ttf');
  }
    @font-face {
    font-family:'NanumSquare';
    src: url('@/assets/font/NanumSquare_acL.ttf');
  }
    @font-face {
    font-family:'NanumSquare';
    src: url('@/assets/font/NanumSquare_acR.ttf');
  }
    @font-face {
    font-family:'NanumSquare';
    src: url('@/assets/font/NanumSquareB.ttf');
  }
  @font-face {
    font-family:'NanumSquare';
    src: url('@/assets/font/NanumSquareEB.ttf');
  }
    @font-face {
    font-family:'NanumSquare';
    src: url('@/assets/font/NanumSquareL.ttf');
  }
    @font-face {
    font-family:'NanumSquare';
    src: url('@/assets/font/NanumSquareR.ttf');
  }
  @font-face {
    font-family:'JuaRegular';
    src: url('@/assets/font/Jua-Regular.ttf');
  }

#app{
  font-family: 'NanumBarunGothic';
}

</style>