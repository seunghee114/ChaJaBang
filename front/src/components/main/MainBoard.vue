<template>
  <v-card class="mx-auto" max-width="400" height="140">
    <!-- <v-img src="../assets/danji.png" height="200px"></v-img> -->
  <div class="total"> <div class="left">

    <v-card-title style="text-align: center">
      Board</v-card-title>

<!-- <v-img src="@/assets/corkboard.png" class="iconImage"
      /> -->

      <v-btn color="pink lighten-2" text @click="move2Board"> 게시판 보러가기 </v-btn>
   </div><div class="right"> <v-img src="@/assets/reminder.png"></v-img></div>
  </div>


  </v-card>
</template>

<script>
export default {
  data: () => ({
    show: false,
  }),
  methods: {
    move2Board() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>

<style>
.iconImage {
  height: 70px;
  width: 70px;
}
</style>
