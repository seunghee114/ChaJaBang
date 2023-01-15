<template>
  <v-card class="mx-auto my-12" color="indigo lighten-5" max-width="374">
    <v-card-title>SIGN UP</v-card-title>
    <v-list-item>
      <template>
        <v-list-item-content>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-card class="overflow-hidden">
              <v-card-text>
                <v-text-field
                  v-model="id"
                  color="indigo lighten-2"
                  :counter="15"
                  :rules="idRules"
                  label="ID"
                  required
                ></v-text-field>

                <v-text-field
                  v-model="password"
                  color="indigo lighten-2"
                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[rules.required, rules.min]"
                  :type="show1 ? 'text' : 'password'"
                  name="input-10-1"
                  label="Password"
                  hint="At least 8 characters"
                  counter
                  @click:append="show1 = !show1"
                >
                </v-text-field>

                <v-text-field
                  v-model="name"
                  color="indigo lighten-2"
                  label="Name"
                  required
                ></v-text-field>

                <v-text-field
                  v-model="email"
                  color="indigo lighten-2"
                  :rules="emailRules"
                  label="E-mail"
                  required
                ></v-text-field>
              </v-card-text>
              <v-row style="text-align: center">
                <v-col></v-col>
                <v-col>
                  <v-btn @click="move2Login" color="indigo lighten-2">LOGIN</v-btn>
                </v-col>
                <v-col>
                  <v-btn @click="register" color="indigo lighten-2">JOIN</v-btn>
                </v-col>
                <v-col></v-col>
              </v-row>
              <br />
            </v-card>
          </v-form>
        </v-list-item-content>
      </template>
    </v-list-item>
  </v-card>
</template>

<script>
import http from "@/api/http";
export default {
  name: "UserRegister",
  data() {
    return {
      valid: true,
      id: "",
      idRules: [
        (v) => !!v || "id is required",
        (v) => (v && v.length <= 15) || "id must be less than 15 characters",
      ],

      show1: false,
      password: "",
      rules: {
        required: (value) => !!value || "Required.",
        min: (v) => v.length >= 8 || "Min 8 characters",
        emailMatch: () => `The email and password you entered don't match`,
      },

      name: "",

      email: "",
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
      ],
    };
  },

  computed: {},
  methods: {
    move2Login() {
      console.log("move login");
      this.$router.push({ name: "login" });
    },
    register() {
      console.log("register");
      let _this = this;
      http
        .post(
          `/user/join`,
          JSON.stringify({
            userid: _this.id,
            username: _this.name,
            userpwd: _this.password,
            email: _this.email,
          })
        )
        .then((response) => console.log(response))
        .catch(console.log("register fail"));
      this.$router.push({ name: "main" });
        
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>

<style></style>
