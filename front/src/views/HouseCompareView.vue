<template>
  <div>
    <v-container>
      <v-row>
        <v-col cols="12">
          <compare-input :checkList="checkList" />
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" sm="6" md="6" v-for="(item, i) in checkList" :key="i">
          <compare-result :checkeditem="checkList[i]" />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import CompareResult from "@/components/house/CompareResult.vue";
import CompareInput from "@/components/house/CompareInput.vue";
export default {
  components: {
    CompareResult,
    CompareInput,
  },
  data() {
    return {
      checkList: [],
    };
  },
  created() {
    console.log("this.$route.params.list", this.$route.params.list);
    this.checkList = this.$route.params.list;
  },
};
</script>

<style></style>
