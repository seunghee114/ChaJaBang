<!--
https://vuetifyjs.com/en/components/tabs/#icons-and-text
 -->
<template>
  <div>
    <div v-if="qna == null">
      <v-row>
        <v-col cols="12">
          <v-card>
            <v-tabs
              v-model="tab"
              background-color="indigo lighten-2"
              centered
              dark
              icons-and-text
            >
              <v-tabs-slider></v-tabs-slider>

              <v-tab href="#tab-1">
                미완료
                <v-icon>mdi-phone</v-icon>
              </v-tab>

              <v-tab v-if="userInfo.userid != 'admin'" href="#tab-2">
                완료
                <v-icon>mdi-heart</v-icon>
              </v-tab>

              <v-tab href="#tab-3">
                전체
                <v-icon>mdi-account-box</v-icon>
              </v-tab>
            </v-tabs>

            <v-tabs-items v-model="tab">
              <v-tab-item value="tab-1">
                <v-card>
                  <v-card-title>
                    <v-text-field
                      v-model="search"
                      append-icon="mdi-magnify"
                      label="Search"
                      single-line
                      hide-details
                      color="indigo lighten-1"
                    ></v-text-field>
                  </v-card-title>
                  <v-data-table
                    :headers="headers"
                    :items="notcomplete"
                    :items-per-page="5"
                    @click:row="getDetail"
                    :search="search"
                  ></v-data-table>
                </v-card>
              </v-tab-item>
              <v-tab-item value="tab-2">
                <v-card flat>
                  <v-card>
                    <v-card-title>
                      <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line
                        hide-details
                        color="indigo lighten-1"
                      ></v-text-field>
                    </v-card-title>
                    <v-data-table
                      :headers="headers"
                      :items="complete"
                      :items-per-page="5"
                      @click:row="getDetail"
                      :search="search"
                    ></v-data-table>
                  </v-card>
                </v-card>
              </v-tab-item>
              <v-tab-item value="tab-3">
                <v-card flat>
                  <v-card>
                    <v-card-title>
                      <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line
                        hide-details
                        color="indigo lighten-1"
                      ></v-text-field>
                    </v-card-title>
                    <v-data-table
                      :headers="headers1"
                      :items="allList"
                      :items-per-page="5"
                      @click:row="getDetail"
                      :search="search"
                    ></v-data-table>
                  </v-card>
                </v-card>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </v-col>
      </v-row>
    </div>
    <div v-else>
      <v-row>
        <v-col cols="12" md="6" style="margin: auto">
          <v-card>
            <v-tabs
              v-model="tab"
              background-color="indigo "
              centered
              dark
              icons-and-text
            >
              <v-tabs-slider></v-tabs-slider>

              <v-tab href="#tab-1">
                미완료
                <v-icon>mdi-phone</v-icon>
              </v-tab>

              <v-tab v-if="userInfo.userid != 'admin'" href="#tab-2">
                완료
                <v-icon>mdi-heart</v-icon>
              </v-tab>

              <v-tab href="#tab-3">
                전체
                <v-icon>mdi-account-box</v-icon>
              </v-tab>
            </v-tabs>

            <v-tabs-items v-model="tab">
              <v-tab-item value="tab-1">
                <v-card>
                  <v-card-title>
                    <v-text-field
                      v-model="search"
                      append-icon="mdi-magnify"
                      label="Search"
                      single-line
                      hide-details
                      color="indigo lighten-1"
                    ></v-text-field>
                  </v-card-title>
                  <v-data-table
                    :headers="headers"
                    :items="notcomplete"
                    :items-per-page="5"
                    @click:row="getDetail"
                    :search="search"
                  ></v-data-table>
                </v-card>
              </v-tab-item>
              <v-tab-item value="tab-2">
                <v-card flat>
                  <v-card>
                    <v-card-title>
                      <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line
                        hide-details
                        color="indigo lighten-1"
                      ></v-text-field>
                    </v-card-title>
                    <v-data-table
                      :headers="headers"
                      :items="complete"
                      :items-per-page="5"
                      @click:row="getDetail"
                      :search="search"
                    ></v-data-table>
                  </v-card>
                </v-card>
              </v-tab-item>
              <v-tab-item value="tab-3">
                <v-card flat>
                  <v-card>
                    <v-card-title>
                      <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line
                        hide-details
                        color="indigo lighten-1"
                      ></v-text-field>
                    </v-card-title>
                    <v-data-table
                      :headers="headers1"
                      :items="allList"
                      :items-per-page="5"
                      @click:row="getDetail"
                      :search="search"
                    ></v-data-table>
                  </v-card>
                </v-card>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </v-col>
        <v-col cols="12" md="6">
          <v-card class="mx-auto my-12" min-height="550" max-width="400">
            <v-container>
              <template slot="progress">
                <v-progress-linear
                  color="deep-purple"
                  height="10"
                  indeterminate
                ></v-progress-linear>
              </template>

              <br />
              <v-card-title>
                <h3>{{ qna.qtitle }}</h3>
              </v-card-title>
              <div style="text-align: right">
                <h4>
                  <v-icon size="x-large">mdi-account</v-icon>
                  {{ qna.qwriter }}
                </h4>
              </div>
              <v-card-text>
                <div style="text-align: right">
                  <span class="my-4 text-subtitle-1">
                    작성일 : {{ qna.qwriteDate | yyyyMMdd }}
                  </span>
                </div>

                <h3 v-html="qna.qcontent"></h3>
                <br />
              </v-card-text>
              <v-row style="text-align: center">
                <v-col cols="12" md="3"></v-col>
                <v-spacer></v-spacer>
                <!-- <v-col cols="12" md="3"><v-btn center color="indigo lighten-3">수정</v-btn></v-col> -->
                <v-col cols="12" md="3"
                  ><v-btn color="indigo lighten-5" @click="qnaDelete"
                    >삭제</v-btn
                  ></v-col
                >
                <!-- <v-col cols="12" md="3"></v-col> -->
              </v-row>

              <v-row>
                <v-card
                  class="overflow-hidden"
                  v-if="userInfo.userid === 'admin' && qna.acontent === null"
                >
                  <v-card-text>
                    <v-text-field
                      color="indigo lighten-3"
                      label="답변"
                      v-model="answer"
                    ></v-text-field>
                  </v-card-text>
                  <v-row style="text-align: center"
                    ><v-col
                      ><v-btn @click="qAnswer" color="indigo lighten-3"
                        >답변하기</v-btn
                      ></v-col
                    ></v-row
                  >
                  <br />
                </v-card>
                <br />
                <v-card class="overflow-hidden" v-if="qna.acontent !== null">
                  <v-card-title>
                    <h3>답변</h3>
                  </v-card-title>
                  <v-card-text>
                    {{ qna.acontent }}
                  </v-card-text>
                </v-card>
              </v-row>
            </v-container>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import {
  qModifyManager,
  qgetManagerNotAnswer,
  qgetManagerQna,
  qgetUserQna,
  qgetUserAnswered,
  qgetUserNotAnswer,
} from "@/api/board";
import { mapActions, mapState, mapMutations } from "vuex";
import { qRead, qDelete } from "@/api/board";
const memberStore = "memberStore";
const boardStore = "boardStore";

export default {
  name: "QnaList",
  data() {
    return {
      qno: "",
      qna: null,
      tab: null,
      answer: "",
      allList: [],
      complete: [],
      notcomplete: [],
      err: "",
      search: "",
      page: "",
      headers: [
        {
          text: "no",
          align: "start",
          sortable: false,
          value: "qno",
        },
        {
          text: "title",
          value: "qtitle",
        },
        {
          text: "write_date",
          value: "qwriteDate",
        },
      ],
      headers1: [
        {
          text: "no",
          align: "start",
          sortable: false,
          value: "qno",
        },
        {
          text: "title",
          value: "qtitle",
        },
        {
          text: "write_date",
          value: "qwriteDate",
        },
        {
          text: "o/x",
          value: "answered",
        },
      ],
    };
  },
  created() {
    this.init();
    console.log(this.userInfo.userid);
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapMutations(boardStore, ["SET_CHECK_STATUS"]),
  },
  watch: {
    qna() {
      console.log("qna list qna watch");
    },
    allList() {},
    complete() {
      let tmp = this.userInfo;
      this.id = tmp.userid;
      if (this.id === "admin") {
        this.getManagerNotAnswer();
      } else {
        this.getUserNotCheck(this.id);
      }
    },
    notcomplete() {},
  },
  methods: {
    ...mapActions(boardStore, ["getManagerNotAnswer", "getUserNotCheck"]),
    init() {
      let _this = this;
      let id = _this.userInfo.userid;
      console.log(_this.userInfo.userid);
      if (id === "admin") {
        qgetManagerQna(({ data }) => {
          console.log("admin qna list, all list???", data);
          _this.allList = data;
        }),
          (error) => {
            _this.err = error;
            console.log("admin qna all list fail");
          },
          qgetManagerNotAnswer(({ data }) => {
            console.log("admin qna list, notcomplete list", data);
            this.notcomplete = data;
          }),
          (error) => {
            this.err = error;
            console.log("admin qna list, notcomplete list fail");
          };
      } else {
        qgetUserQna(id, ({ data }) => {
          console.log("qna list, all list", data);
          this.allList = data;
        }),
          (error) => {
            this.err = error;
            console.log("qna list, all list fail");
          };
        qgetUserAnswered(id, ({ data }) => {
          console.log("qna list, complete list", data);
          this.complete = data;
        }),
          (error) => {
            this.err = error;
            console.log("qna list, complete list fail");
          };
        qgetUserNotAnswer(id, ({ data }) => {
          console.log("qna list, notcomplete list", data);
          this.notcomplete = data;
        }),
          (error) => {
            this.err = error;
            console.log("qna list, notcomplete list fail");
          };
      }
      this.updateNckQna(id);
    },
    getDetail(el) {
      console.log("qna list el", el);
      console.log("qna list detail qno", el.qno);
      this.qno = el.qno;
      qRead(
        this.qno,
        ({ data }) => {
          console.log("qna detail qna ", data);
          this.qna = data;
        },
        (error) => {
          this.err = error;
          console.log("qna detail fail");
        }
      );
      this.updateNckQna(this.userInfo.userid);
    },
    qnaDelete() {
      qDelete(
        this.qno,
        ({ data }) => {
          alert(data);
          this.qna = null;
          this.init();
        },
        (error) => {
          console.log("qna detail fail", error);
        }
      );
    },
    qAnswer() {
      console.log(this.answer);
      this.qna.acontent = this.answer;
      qModifyManager(
        this.qna,
        ({ data }) => {
          alert("답변 완료");
          console.log("답변 success", data);
          this.init();
        },
        (error) => {
          console.log("qna answer", error);
        }
      );
    },
    updateNckQna(userid) {
      if (this.userInfo != null) {
        if (userid === "admin") {
          this.getManagerNotAnswer();
        } else {
          this.getUserNotCheck(userid);
        }
      }
    },
  },
  filters: {
    ox: function (value) {
      if (value == "true") {
        return "O";
      } else {
        return "X";
      }
    }, // filter로 쓸 filter ID 지정
    yyyyMMdd: function (value) {
      // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
      if (value == "") return "";

      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      var js_date = new Date(value);

      // 연도, 월, 일 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();

      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = "0" + month;
      }

      if (day < 10) {
        day = "0" + day;
      }

      // 최종 포맷 (ex - '2021-10-08')
      return year + "-" + month + "-" + day;
    },
  },
};
</script>

<style>
v-container {
  display: flex !important;
  flex-direction: column;
}
</style>
