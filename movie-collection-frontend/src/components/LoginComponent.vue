<template>
  <div>
    <div class="overlay"></div>
    <div class="loginComponent">
      <p class="closeButton" @click="closeLoginForm">X</p>
      <p class="loginFormTitle">Login Form</p>
      <form @submit.prevent="login" class="loginForm">
        <label class="loginLabel" for="loginUsername">Username:</label>
        <input v-model.trim="$v.user.username.$model" class="loginInput" type="text" name="loginUsername"
               id="loginUsername"
               :class="{invalidFiled: $v.user.username.$error}">
        <div class="loginError">
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
        <label class="loginLabel" for="loginPassword">Password:</label>
        <input v-model.trim="$v.user.password.$model" class="loginInput" type="password" name="loginPassword"
               id="loginPassword"
               :class="{invalidFiled: $v.user.password.$error}">
        <div class="loginError">
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
        <button class="loginButton">LOGIN</button>
        <p class="smallText">If you don't have account
          <router-link tag="span" :to="{name: 'register'}">click here</router-link>
        </p>
      </form>
    </div>
    <modal name="loginErrorModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <ErrorModal :errorMessage="this.errorMessage"/>
    </modal>
  </div>
</template>

<script>
import {alphaNum, maxLength, minLength, required} from "vuelidate/lib/validators";
import {Constants} from "@/constants/constants";
import ErrorModal from "@/components/messages/ErrorModal";

export default {
  name: "LoginComponent",
  components: {
    ErrorModal,
  },
  data() {
    return {
      constants: Constants,
      user: {
        username: null,
        password: null
      },
      errorMessage: null,
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
      }
    }
  },
  methods: {
    showErrorModal() {
      this.$modal.show(this.constants.LOGIN_ERROR_MODAL);
    },
    hideErrorModal() {
      this.$modal.hide(this.constants.LOGIN_ERROR_MODAL);
    },
    login() {
      this.$v.$touch();

      if (this.$v.$invalid) {
        this.errorMessage = this.constants.ERROR.FILL_FORM_CORRECT_ERROR;

        this.showErrorModal();
        setTimeout(() => {
          this.hideErrorModal()
        }, 3000)

      } else {
        this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push({name: 'movies'});
              setTimeout(() => {
                this.$store.dispatch('auth/logout')
                this.$router.push({name: 'home'})
              }, 12 * 60 * 60 * 1000)
            },
            error => {
              this.errorMessage = error.response.data

              this.showErrorModal();
              setTimeout(() => {
                this.hideErrorModal()
              }, 3000)
            }
        );
      }
    },
    closeLoginForm() {
      this.$router.push({name: 'home'})
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push({name: 'movies'})
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

div.loginComponent {
  position: fixed;
  z-index: 40;
  background-color: #010340;
  width: 28rem;
  height: 28rem;

  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
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
  margin: 1rem 4rem;
}

div.loginComponent form.loginForm input.loginInput {
  font-size: 1.2rem;
  width: 20rem;
  display: block;
  margin: 1rem 4rem;
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

p.smallText span {
  color: #2AF598;
}

p.smallText span:hover {
  cursor: pointer;
  color: #e6fa29;
}

</style>