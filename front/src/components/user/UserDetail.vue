<template>
  <v-row>
    <v-col cols="12">
      <v-card title="Account Details" min-height="340">
        <v-card-text class="d-flex"> </v-card-text>

        <v-divider />

        <h2 style="text-align: center">회원 정보</h2>
        <v-card-text>
          <!-- 👉 Form -->
          <v-form class="mt-6">
            <v-row>
              <!-- 👉 ID -->
              <v-col cols="12">
                <v-text-field v-model="id" label="Id" readonly>{{
                  id
                }}</v-text-field>
              </v-col>

              <!-- 👉 Name -->
              <v-col cols="12">
                <v-text-field v-model="name" label="Name">{{
                  name
                }}</v-text-field>
              </v-col>

              <!-- 👉 Email -->
              <v-col md="12">
                <v-text-field v-model="email" label="E-mail" type="email">{{
                  email
                }}</v-text-field>
              </v-col>

              <!-- 👉 Form Actions -->
              <v-col cols="12" sm="3" md="3"> </v-col>
              <v-col cols="12" sm="3" md="3" style="text-align: center">
                <v-btn color="indigo lighten-3" @click="modify"
                  >Save changes</v-btn
                >
              </v-col>
              <v-col cols="12" sm="3" md="3" style="text-align: center">
                <v-btn
                  color="indigo lighten-5"
                  variant="tonal"
                  type="reset"
                  @click.prevent="resetForm"
                >
                  Reset
                </v-btn>
              </v-col>
              <v-col cols="12" sm="3" md="3"> </v-col>
            </v-row>
          </v-form>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/http";

const memberStore = "memberStore";
export default {
  data() {
    return {
      id: "",
      name: "",
      email: "",
      isAccountDeactivated: false,
    };
  },
  created() {
    // this.$store.getters.checkUserInfo;
    console.log("create state", this.$store.state.memberStore.userInfo);
    console.log("what the..");
    let tmp = this.userInfo;
    this.id = tmp.userid;
    this.name = tmp.username;
    this.email = tmp.email;
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  watch: {
    name() {
      console.log("watch name");
    },
  },
  methods: {
    resetForm() {
      let tmp = this.userInfo;
      this.id = tmp.userid;
      this.name = tmp.username;
      this.email = tmp.email;
    },
    modify() {
      console.log("user modify");
      let _this = this;
      let param = this.userInfo;
      param.username = _this.name;
      param.email = _this.email;

      http
        .put(`/user`, JSON.stringify(param))
        .then((response) => console.log(response))
        .catch(console.log("userinfo modify fail"));

      this.$router.push({ name: "main" });

      // console.log(param);
    },
  },
};
</script>

<style>
v-container {
  display: flex !important;
  flex-direction: column;
}

.v-card {
  flex-grow: 1;
  overflow: auto;
}
.scroll {
  overflow-y: scroll;
}
</style>
