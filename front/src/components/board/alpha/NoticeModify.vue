<template>
  <div>
    <v-row>
      <v-col>
        <v-card class="overflow-hidden" color="indigo lighten-3" dark>
          <v-card-text>
            <v-text-field filled color="white" label="Title" v-model="board.ntitle"></v-text-field>
          </v-card-text>
        </v-card> </v-col
    ></v-row>
    <v-row>
      <v-col cols="12">
        <ckeditor fillled v-model="board.ncontent" :config="editorConfig"></ckeditor>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" style="text-align: center">
        <v-btn @click="modify" color="indigo">
          <b style="color: white">수정 완료</b>
        </v-btn>
      </v-col>
    </v-row>

    <v-snackbar v-model="isShow" :timeout="timeout">
      {{ result }}

      <template v-slot:action="{ attrs }">
        <v-btn color="pink" text v-bind="attrs" @click="isShow = false"> Close </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import { nRead, nModify } from "@/api/board";
export default {
  data() {
    return {
      nno: "",
      board: {},
      ntitle: "",

      ncontent: "",
      editorConfig: {},
      isShow: false,
      result: "",
      timeout: 1000,
    };
  },
  created() {
    this.nno = this.$route.params.nno;
    let _this = this;
    let param = this.nno;
    nRead(
      param,
      ({ data }) => {
        _this.board = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    modify() {
      let _this = this;
      let param = {
        nno: _this.nno,
        ntitle: _this.board.ntitle,
        
        ncontent: _this.board.ncontent,
        nwriteDate: _this.nwriteDate,
      };
      nModify(
        param,
        ({ data }) => {
          alert(data);
          _this.result = data;
          _this.isShow = true;

          _this.$router.push({ name: "notice" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
