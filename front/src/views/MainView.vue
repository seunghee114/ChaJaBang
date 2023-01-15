<template>
  <v-container fluid>
    <template>
      <v-row>                <v-col cols="12">
          <v-img src="../assets/apartment.png" class="home_logo" />
        </v-col>
        <v-col cols="12" md="3"></v-col>
        <v-col cols="12" md="3"><main-board /></v-col>
        <v-col cols="12" md="3"><main-house /></v-col>
        <v-col cols="12" md="3"></v-col>


      </v-row>
    </template>
  </v-container>
</template>

<script>
import MainHouse from "@/components/main/MainHouse.vue";
import MainBoard from "../components/main/MainBoard.vue";
export default {
  name: "MainView",

  components: {
    MainBoard,
    MainHouse,
  },
};
</script>
<style>
.home_logo {
  width: 30%;
  margin-left: 35%;
}
</style>
