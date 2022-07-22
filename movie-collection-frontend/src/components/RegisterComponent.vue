<template>
  <div>
    <div class="overlay">

    </div>
    <div class="registerComponent">
      <p class="closeButton" @click="closeRegisterForm">X</p>
      <p class="registerFormTitle">Register Form</p>
      <form @submit.prevent="register" class="registerForm">

        <label class="registerLabel" for="registerUsername">Username:</label>
        <input v-model.trim="$v.user.username.$model" class="registerInput" type="text" name="registerUsername"
               id="registerUsername"
               :class="{invalidFiled: $v.user.username.$error}">
        <div class="registerError">
        <span v-if="$v.user.username.$dirty && !$v.user.username.required" class="errorMessage">
        <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.USERNAME_REQUIRED }}
        </span>
          <span v-else-if="!$v.user.username.alphaNum" class="errorMessage">
          <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.USERNAME_ALPHA_NUM }}
        </span>
          <span v-else-if="!$v.user.username.minLength || !$v.user.username.maxLength" class="errorMessage">
        <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.USERNAME_BETWEEN }}
        </span>
        </div>

        <label class="registerLabel" for="registerPassword">Password:</label>
        <input v-model.trim="$v.user.password.$model" class="registerInput" type="password" name="registerPassword"
               id="registerPassword"
               :class="{invalidFiled: $v.user.password.$error}">
        <div class="registerError">
        <span v-if="$v.user.password.$dirty && !$v.user.password.required" class="errorMessage">
        <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.PASSWORD_REQUIRED }}
        </span>
          <span v-else-if="!$v.user.password.alphaNum" class="errorMessage">
          <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.PASSWORD_ALPHA_NUM }}
        </span>
          <span v-else-if="!$v.user.password.minLength || !$v.user.password.maxLength" class="errorMessage">
        <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.PASSWORD_BETWEEN }}
        </span>
        </div>

        <label class="registerLabel" for="confirmPassword">Confirm Password:</label>
        <input v-model.trim="$v.user.confirmPassword.$model" class="registerInput" type="password" name="confirmPassword"
               id="confirmPassword" :class="{invalidFiled: $v.user.confirmPassword.$error}">
        <div class="registerError">
        <span v-if="$v.user.confirmPassword.$dirty && !$v.user.confirmPassword.sameAs" class="errorMessage">
          <font-awesome-icon icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.CONFIRM_PASSWORD }}
        </span>
        </div>
        <button class="registerButton">Register</button>
        <p class="smallText">If you have account
          <router-link tag="span" :to="{name: 'login'}">click here</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import {alphaNum, maxLength, minLength, required, sameAs} from "vuelidate/lib/validators";
import {Constants} from "@/constants/constants";
// import User from '../models/user';

export default {
  name: "RegisterComponent",
  data() {
    return {
      // user: new User('' , '' , ''),
      constants: Constants,
      submitted: false,
      successful: false,
      user: {
        username: null,
        password: null,
        confirmPassword: null,
      }
    }
  },
  validations: {
    user: {
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
    }
  },
  methods: {
    register() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;

      } else {
        this.$store.dispatch('auth/register', this.user).then(
            data => {
              this.message = data.message;
              // this.successful = true;
            },
            error => {
              this.message =
                  (error.response && error.response.data) ||
                  error.message ||
                  error.toString();
              // this.successful = false;
            }
        );
      }
    },
    closeRegisterForm() {
      this.$router.push({name: 'home'})
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push({name: 'movies'});
    }
  },
}
</script>

<style scoped>

div.overlay {
  position: fixed;
  background-color: #010340;
  opacity: 0.5;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 30;
}

div.registerComponent {
  background-color: #010340;
  position: fixed;
  width: 28rem;
  height: 36rem;
  z-index: 40;

  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
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
  margin: 1rem 4rem;
}

div.registerComponent form.registerForm input.registerInput {
  font-size: 1.2rem;
  width: 20rem;
  display: block;
  margin: 1rem 4rem;
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
  padding: 0.5rem 0.5rem;
  margin: 1rem 32%;
  width: 10rem
}

div.registerComponent button.registerButton:hover {
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

p.smallText span {
  color: #2AF598;
}

p.smallText span:hover {
  cursor: pointer;
  color: #e6fa29;
}
</style>