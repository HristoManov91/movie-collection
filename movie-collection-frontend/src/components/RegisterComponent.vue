<template>
  <div class="registerComponent">
    <p class="closeButton" @click="closeRegisterForm">X</p>
    <p class="registerFormTitle">Register Form</p>
    <form @submit.prevent="register" class="registerForm">

      <label class="registerLabel" for="registerUsername">Username:</label>
      <input v-model.trim="$v.username.$model" class="registerInput" type="text" name="registerUsername" id="registerUsername"
             :class="{invalidFiled: $v.username.$error}">
      <div class="registerError">
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

      <label class="registerLabel" for="registerPassword">Password:</label>
      <input v-model.trim="$v.password.$model" class="registerInput" type="password" name="registerPassword" id="registerPassword"
             :class="{invalidFiled: $v.password.$error}">
      <div class="registerError">
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

      <label class="registerLabel" for="confirmPassword">Confirm Password:</label>
      <input v-model.trim="$v.confirmPassword.$model" class="registerInput" type="password" name="confirmPassword"
             id="confirmPassword" :class="{invalidFiled: $v.confirmPassword.$error}">
      <div class="registerError">
        <span v-if="$v.confirmPassword.$dirty && !$v.confirmPassword.sameAs" class="errorMessage">
          <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.CONFIRM_PASSWORD }}
        </span>
      </div>

      <button class="registerButton">Register</button>
    </form>
  </div>
</template>

<script>
import {alphaNum, maxLength, minLength, required, sameAs} from "vuelidate/lib/validators";
import {Constants} from "@/constants/constants";

export default {
  name: "RegisterComponent",
  data() {
    return {
      constants: Constants,
      username: null,
      password: null,
      confirmPassword: null,
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
    },
    confirmPassword: {
      sameAs: sameAs('password')
    }
  },
  methods: {
    register() {
      console.log('register')
      //ToDo
    },
    closeRegisterForm(){
      this.$emit('closeRegisterForm')
    }
  }
}
</script>

<style scoped>

div.registerComponent {
  background-color: #010340;
  width: 100%;
  height: 100%;
}

div.registerComponent p.closeButton {
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

div.registerComponent p.closeButton:hover {
  cursor: pointer;
  border: 1px solid white;
}

div.registerError {
  height: 20px;
}

div.registerComponent p.registerFormTitle {
  color: #FFDF65;
  text-align: center;
  font-weight: bold;
  font-size: 3rem;
  padding: 1rem;
}

div.registerComponent form.registerForm label.registerLabel {
  font-size: 1.2rem;
  display: block;
  color: #FFDF65;
  margin: 1rem 18%;
}

div.registerComponent form.registerForm input.registerInput {
  font-size: 1.2rem;
  width: 20rem;
  display: block;
  margin: 1rem 18%;
  background-color: #4158D0;
  background-image: linear-gradient(270deg, #4158D0 0%, #C850C0 39%, #FFCC70 83%);
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

div.registerComponent button.registerButton {
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

div.registerComponent button.registerButton:hover {
  background-color: #08AEEA;
  background-image: linear-gradient(223deg, #08AEEA 0%, #2AF598 100%);
  cursor: pointer;
}

.invalidFiled {
  border: 3px solid red;
}

</style>