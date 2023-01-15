<!--
https://vuetifyjs.com/en/components/cards/
Custom actions
-->
<template>
  <v-card class="mx-auto" max-width="400" height="140" justify-content="center">
    <!-- <v-img src="../assets/danji.png" height="200px"></v-img> -->
  <div class="total"> <div class="left">
    <v-card-title style="text-align: center"> Apart </v-card-title>

      <v-btn color="pink lighten-2" text @click="move2House"> 아파트 보러가기 </v-btn>

   </div><div class="right"> <v-img src="@/assets/loupe.png"></v-img></div>
  </div>

  </v-card>
</template>

<script>
export default {
  data: () => ({
    show: false,
  }),
  methods: {
    move2House() {
      this.$router.push({ name: "houseTable" });
    },
  },
};
</script>

<style>
.total{
  display: flex;
}

.left{
  width:70%;
}

.right{
  width: 30%;

}
</style>
