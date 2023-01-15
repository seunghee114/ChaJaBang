<!--
https://vuetifyjs.com/en/components/autocompletes/#filter
-->
<template>
  <v-card class="mx-auto my-12" color="indigo lighten-5" max-width="374">
    <v-card-title>LOGIN</v-card-title>
    <v-list-item>
      <template>
        <v-list-item-content>
          <!-- comment input start -->
          <v-card class="overflow-hidden">
            <v-card-text>
              <v-text-field
                color="indigo lighten-2"
                label="ID"
                v-model="user.userid"
                :rules="idRules"
                @keyup.enter="confirm"
              ></v-text-field>
              <v-text-field
                color="indigo lighten-2"
                label="PASSWORD"
                v-model="user.userpwd"
                :type="isShow ? 'text' : 'password'"
                :rules="[rules.required, rules.min]"
                @keyup.enter="confirm"
              ></v-text-field>
            </v-card-text>
            <v-row style="text-align: center">
              <v-col></v-col>
              <v-col>
                <v-btn @click="move2Register" color="indigo lighten-2">JOIN</v-btn>
              </v-col>
              <v-col>
                <v-btn @click="confirm" color="indigo lighten-2">Login</v-btn>
              </v-col>
              <v-col></v-col>
            </v-row>
            <br />
          </v-card>
          <!-- comment input end -->
        </v-list-item-content>
      </template>
    </v-list-item>
  </v-card>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
export default {
  data() {
    return {
      isShow: false,
      user: {
        userid: "",
        userpwd: "",
      },
      idRules: [
        (v) => !!v || "id is required",
        (v) => (v && v.length <= 15) || "id must be less than 15 characters",
      ],
      rules: {
        required: (value) => !!value || "Required.",
        min: (v) => v.length >= 8 || "Min 8 characters",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "getUserNotCheck" ,"getManagerNotAnswer"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);


        this.$router.push({ name: "main" });
      }
    },
    move2Register() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>
<style></style>
