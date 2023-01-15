<template>
  <div>
    <v-row>
      <v-col>
        <v-card class="overflow-hidden">
          <v-card-text>
            <v-text-field
              color="indigo lighten-3"
              label="Title"
              v-model="title"
            ></v-text-field>
            <v-text-field
              color="indigo lighten-3"
              label="Writer"
              v-model="writer"
              readonly
            ></v-text-field>
          </v-card-text>
        </v-card> </v-col
    ></v-row>
    <v-row>
      <v-col cols="12">
        <ckeditor v-model="content" :config="editorConfig"></ckeditor>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" style="text-align: center">
        <v-btn @click="write" color="indigo lighten-2">
          <b style="color: white">작성 완료</b>
        </v-btn>
      </v-col>
    </v-row>

    <v-snackbar v-model="isShow" :timeout="timeout">
      {{ result }}

      <template v-slot:action="{ attrs }">
        <v-btn color="pink" text v-bind="attrs" @click="isShow = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import { bWrite } from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  data() {
    return {
      title: "",
      writer: "",
      content: "",
      editorConfig: {},
      isShow: false,
      result: "",
      timeout: 1000,
    };
  },
  created() {
    this.writer = this.userInfo.userid;
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    write() {
      let _this = this;
      let param = {
        title: _this.title,
        writer: _this.writer,
        content: _this.content,
      };
      bWrite(
        param,
        ({ data }) => {
          alert(data);
          _this.result = data;
          _this.isShow = true;

          _this.$router.push({ name: "boardList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style>
</style>
