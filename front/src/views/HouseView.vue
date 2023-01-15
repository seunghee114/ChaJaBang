<template>
  <v-container fluid>
    <template>
      <v-row>
        <v-col cols="12"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="2"></v-col>
        <v-col cols="12" md="8"> <house-header /></v-col>
        <v-col cols="12" md="2"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="2"></v-col>
        <v-col cols="12" md="4"><house-map /></v-col>
        <v-col cols="12" md="4"><router-view/></v-col>
        <v-col cols="12" md="2"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12"></v-col>
      </v-row>      
      <v-row>
        <v-col cols="12" md="2"></v-col>
        <v-col cols="12" md="8"> <house-naver /></v-col>
        <v-col cols="12" md="2"></v-col>
      </v-row>
    </template>
  </v-container>
</template>


<script>
// import HouseTable from "@/components/house/HouseTable.vue";
import HouseHeader from "../components/house/HouseHeader.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import HouseNaver from "@/components/house/HouseNaver.vue";

export default {
  name: "HouseView",

  components: {
    HouseHeader,
    HouseNaver,
    HouseMap,
  },
};
</script>
<style></style>
