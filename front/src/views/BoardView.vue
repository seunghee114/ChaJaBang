<template>
  <v-container fluid>
    <template>
      <v-row>
        <v-col cols="12"></v-col>
      </v-row>
      
      <v-row>
        <v-col cols="12" md="2"></v-col>
        <v-col cols="12" md="8"> <board-header /></v-col>
        <v-col cols="12" md="2"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="2"></v-col>
        <v-col cols="12" md="4" style="text-align: left"><h2> 인기글</h2><board-ranking /> </v-col>
        <v-col cols="12" md="4" style="text-align: left"><h2> 공지사항</h2><board-notice4 /></v-col>
        <v-col cols="12" md="2"></v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="2"></v-col>
        <v-col cols="12" md="8"><router-view /></v-col>
        <v-col cols="12" md="2"></v-col>
      </v-row>
    </template>
  </v-container>
</template>

<script>
import BoardRanking from "@/components/board/BoardRanking.vue";
import BoardHeader from "@/components/board/BoardHeader.vue";
import BoardNotice4 from "@/components/board/alpha/BoardNotice4.vue";
export default {
  components: {
    BoardRanking,
    BoardHeader,
    BoardNotice4,
  },
};
</script>

<style>

</style>