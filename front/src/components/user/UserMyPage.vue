<!--
https://vuetifyjs.com/en/components/tabs/#props
-->
<template>
  <div>
    <br />
    <v-row>
      <v-col cols="12" md="2"></v-col>
      <v-col cols="12" md="8">
        <v-card>
          <v-toolbar color="indigo darken-1" flat>
            <template v-slot:extension>
              <v-tabs v-model="tab" align-with-title color="white">
                <v-tabs-slider color="white"></v-tabs-slider>

                <v-tab> 회원정보 </v-tab>
                <v-tab>내 문의 </v-tab>
                <v-tab> 좋아요 </v-tab>
              </v-tabs>
            </template>
          </v-toolbar>

          <v-tabs-items v-model="tab">
            <v-tab-item>
              <v-card flat>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <user-detail />
                    </v-col>
                  </v-row>
                </v-container>
              </v-card>
            </v-tab-item>
            <v-tab-item>
              <v-card flat>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <qna-list />
                    </v-col>
                  </v-row>
                </v-container>
              </v-card>
            </v-tab-item>
            <v-tab-item>
              <v-card flat>
                <v-container>
                  <v-card-text><user-interest /></v-card-text>
                </v-container>
              </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
      </v-col>
      <v-col cols="12" md="2"></v-col>
    </v-row>
  </div>
</template>
<script>
import UserDetail from "@/components/user/UserDetail.vue";
import QnaList from "@/components/board/alpha/QnaList.vue";
// import QnaDetail from "@/components/board/alpha/QnaDetail.vue";
import UserInterest from "@/components/user/UserInterest.vue";
export default {
  data() {
    return {
      tab: null,
      items: ["회원정보", "내 문의", "좋아요목록"],
    };
  },
  components: {
    UserDetail,
    QnaList,
    // QnaDetail,
    UserInterest,
  },
  mounted() {
    this.tab = this.$route.params.tab;
  },
};
</script>

<style>
v-container {
  display: flex !important;
  flex-direction: column;
}
.scroll {
  overflow-y: scroll;
}
</style>
