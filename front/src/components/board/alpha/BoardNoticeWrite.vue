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

    <v-snackbar v-model="isShow" :timeout="timeout">
      {{ result }}

      <template v-slot:action="{ attrs }">
        <v-btn color="pink" text v-bind="attrs" @click="isShow = false"> Close </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import { nWrite } from "@/api/board";
import { mapActions } from "vuex";

const boardStore = "boardStore";
export default {
  data() {
    return {
      title: "",
      content: "",
      editorConfig: {},
      isShow: false,
      result: "",
      timeout: 1000,
    };
  },
  methods: {
    ...mapActions(boardStore, ["getLatest"]),
    write() {
      let _this = this;
      let param = {
        ntitle: _this.title,
        ncontent: _this.content,
      };
      nWrite(
        param,
        ({ data }) => {
          alert(data);
          _this.result = data;
          _this.isShow = true;
          this.getLatest();
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
