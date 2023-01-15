<template>
  <div>
    <v-row>
      <v-col>
        <v-card class="overflow-hidden">
          <v-card-text>
            <v-text-field color="indigo lighten-3" label="Title" v-model="title"></v-text-field>
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
  </div>
</template>

<script>
import { qWrite } from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  data() {
    return {
      title: "",
      content: "",
      editorConfig: {},
      timeout: 1000,
      id: "",
    };
  },
  created() {
    this.id = this.userInfo.userid;
    console.log("Qna Write, id", this.id);
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    write() {
      let _this = this;
      let param = {
        qtitle: _this.title,
        qcontent: _this.content,
        qwriter: _this.id,
      };
      qWrite(
        param,
        ({ data }) => {
          alert(data);
          _this.$router.push({ name: "main" });
        },
        (error) => {
          _this.result = error;
          console.log("Qna Write error");
        }
      );
    },
  },
};
</script>

<style></style>
