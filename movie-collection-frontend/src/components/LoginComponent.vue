<template>
  <div class="loginComponent">
    <p class="closeButton" @click="closeLoginForm">X</p>
    <p class="loginFormTitle">Login Form</p>
    <form @submit.prevent="login" class="loginForm">

      <label class="loginLabel" for="loginUsername">Username:</label>
      <input v-model.trim="$v.username.$model" class="loginInput" type="text" name="loginUsername" id="loginUsername"
             :class="{invalidFiled: $v.username.$error}">
      <div class="loginError">
        <span v-if="$v.username.$dirty && !$v.username.required" class="errorMessage">
        <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.USERNAME_REQUIRED }}
        </span>
        <span v-else-if="!$v.username.alphaNum" class="errorMessage">
          <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.USERNAME_ALPHA_NUM }}
        </span>
        <span v-else-if="!$v.username.minLength || !$v.username.maxLength" class="errorMessage">
        <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.USERNAME_BETWEEN }}
        </span>
      </div>

      <label class="loginLabel" for="loginPassword">Password:</label>
      <input v-model.trim="$v.password.$model" class="loginInput" type="password" name="loginPassword" id="loginPassword"
             :class="{invalidFiled: $v.password.$error}">
      <div class="loginError">
        <span v-if="$v.password.$dirty && !$v.password.required" class="errorMessage">
        <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.PASSWORD_REQUIRED }}
        </span>
        <span v-else-if="!$v.password.alphaNum" class="errorMessage">
          <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.PASSWORD_ALPHA_NUM }}
        </span>
        <span v-else-if="!$v.password.minLength || !$v.password.maxLength" class="errorMessage">
        <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.PASSWORD_BETWEEN }}
        </span>
      </div>
      <button class="loginButton">LOGIN</button>
      <p class="smallText">If you don't have account <a href="/movies/register">click here</a></p>
    </form>
  </div>
</template>

<script>
import {alphaNum, maxLength, minLength, required} from "vuelidate/lib/validators";
import {Constants} from "@/constants/constants";

export default {
  name: "LoginComponent",
  data() {
    return {
      constants: Constants,
      username: null,
      password: null
    }
  },
  validations: {
    username: {
      required,
      minLength: minLength(6),
      maxLength: maxLength(10),
      alphaNum
    },
    password: {
      required,
      minLength: minLength(6),
      maxLength: maxLength(10),
      alphaNum
    }
  },
  methods: {
    login() {
      this.$v.$touch();

      if (!this.$v.$invalid) {
        //ToDo
      }
    },
    closeLoginForm() {
      this.$emit('closeLoginForm');
    }
  },
}
</script>

<style scoped>

div.loginComponent {
  z-index: 100;
  background-color: #010340;
  width: 100%;
  height: 100%;
}

div.loginComponent p.closeButton {
  position: absolute;
  color: white;
  background-color: red;
  padding: 0.3rem 0.7rem;
  font-size: 2rem;
  right: 0;
  top: 0;
  text-align: center;
  font-family: "Helvetica", serif;
  font-weight: bold;
}

div.loginComponent p.closeButton:hover {
  cursor: pointer;
  border: 1px solid white;
}

div.loginError {
  height: 20px;
}

div.loginComponent p.loginFormTitle {
  color: #FFDF65;
  text-align: center;
  font-weight: bold;
  font-size: 3rem;
  padding: 1rem;
}

div.loginComponent form.loginForm label.loginLabel {
  font-size: 1.2rem;
  display: block;
  color: #FFDF65;
  margin: 1rem 18%;
}

div.loginComponent form.loginForm input.loginInput {
  font-size: 1.2rem;
  width: 20rem;
  display: block;
  margin: 1rem 18%;
  background-color: #74EBD5;
  background-image: linear-gradient(212deg, #74EBD5 0%, #f5f564 100%);
}

span.errorMessage {
  font-size: 0.7rem;
  display: block;
  color: #D8000C;
  letter-spacing: 1px;
  padding: 5px;
  text-align: center;
  background-color: #FFBABA;
  border-radius: 6px;
  width: 60%;
  margin: 10px 20%;
  border: 1px solid #D8000C;
}

button.loginButton {
  text-transform: uppercase;
  text-align: center;
  font-weight: bold;
  border: 1px solid black;
  border-radius: 10px;
  background-color: #08AEEA;
  background-image: linear-gradient(43deg, #08AEEA 0%, #2AF598 100%);
  color: #010340;
  font-size: 1.5rem;
  padding: 0.5rem 1rem;
  margin: 1rem 35%;
  width: 30%
}

button.loginButton:hover {
  background-color: #08AEEA;
  background-image: linear-gradient(223deg, #08AEEA 0%, #2AF598 100%);
  cursor: pointer;
}

.invalidFiled {
  border: 3px solid red;
}

p.smallText {
  color: white;
  text-align: center;
  margin: 1rem;
}

p.smallText a {
  color: yellow;
}

</style>