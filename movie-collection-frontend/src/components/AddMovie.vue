<template>
  <div class="container">
    <p class="pageTitle">Add movie</p>
    <p class="closeButton" @click="closeAddMovie">X</p>
    <form @submit.prevent="saveMovie" class="addForm">
      <div class="row">
        <div class="col">
          <label for="title1">Title:</label>
          <input id="title1" v-model.trim="$v.newMovie.title1.$model" type="text"
                 :class="{invalidFiled: $v.newMovie.title1.$error}"/>
        </div>
        <div class="col">
          <label for="title2">Second Title:</label>
          <input id="title2" v-model.trim="$v.newMovie.title2.$model" type="text"
                 :class="{invalidFiled: $v.newMovie.title2.$error}"/>
        </div>
      </div>
      <div class="rowError">
        <div class="colError">
          <span v-if="$v.newMovie.title1.$dirty && !$v.newMovie.title1.required" class="errorMessage">
            <font-awesome-icon icon="fa-solid fa-circle-exclamation"/>{{ this.constants.error.TITLE1_REQUIRED }}
          </span>
          <span v-else-if="!$v.newMovie.title1.minLength || !$v.newMovie.title1.maxLength" class="errorMessage">
            <font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/>
            {{ this.constants.error.TITLE1_LENGTH }}
          </span>
        </div>
        <div class="colError">
          <div v-if="!$v.newMovie.title2.minLength || !$v.newMovie.title2.maxLength" class="errorMessage">
            <font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/>
            {{ this.constants.error.TITLE2_LENGTH }}
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <label for="duration">Duration:</label>
          <input id="duration" v-model.number="$v.newMovie.duration.$model" type="number"
                 :class="{invalidFiled: $v.newMovie.duration.$error}"/>
        </div>
        <div class="col">
          <label for="year">Year:</label>
          <input id="year" v-model.number="$v.newMovie.year.$model" type="number"
                 :class="{invalidFiled: $v.newMovie.year.$error}"/>
        </div>
      </div>
      <div class="rowError">
        <div class="colError">
          <span v-if="$v.newMovie.duration.$dirty && !$v.newMovie.duration.required" class="errorMessage">
            <font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/>
            {{ this.constants.error.DURATION_REQUIRED }}
          </span>
          <span v-else-if="!$v.newMovie.duration.numeric" class="errorMessage">
            <font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/>
            {{ this.constants.error.DURATION_NUMERIC }}
          </span>
          <span v-else-if="!$v.newMovie.duration.between" class="errorMessage">
            <font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/>
            {{ this.constants.error.DURATION_BETWEEN }}
          </span>
        </div>
        <div class="colError">
          <span v-if="$v.newMovie.year.$dirty && !$v.newMovie.year.required" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.YEAR_REQUIRED }}</span>
          <span v-else-if="!$v.newMovie.year.numeric" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.YEAR_NUMERIC }}</span>
          <span v-else-if="!$v.newMovie.year.between" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.YEAR_BETWEEN }}</span>
        </div>
      </div>
      <div class="genresAdd">
        <div v-for="(genre , i) in genres" :key="i" >
          <input type="checkbox" :id="genre + 'Edit'" :name="genre + 'Edit'" :value="genre"
                 v-model="$v.newMovie.genres.$model" >
          <label :for="genre + 'Edit'">{{ genre }}</label>
        </div>
        <span v-if="$v.newMovie.genres.$dirty && (!$v.newMovie.genres.required || !$v.newMovie.genres.minLength)"
              class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.GENRES_REQUIRED }}</span>
      </div>
      <div class="urlsAdd">
        <label for="imdbUrl">IMDb URL:</label>
        <input id="imdbUrl" type="text" v-model.trim="$v.newMovie.imdbUrl.$model"
               :class="{invalidFiled: $v.newMovie.imdbUrl.$error}"/>
        <span v-if="!$v.newMovie.imdbUrl.url" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.IMDB_URL }}</span>
        <span v-else-if="!$v.newMovie.imdbUrl.maxLength" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.IMDB_URL_MAX_LENGTH }}</span>
        <label for="trailerUrl">Trailer URL:</label>
        <input id="trailerUrl" type="text" v-model.trim="$v.newMovie.trailerUrl.$model"
               :class="{invalidFiled: $v.newMovie.trailerUrl.$error}"/>
        <span v-if="$v.newMovie.trailerUrl.$dirty && !$v.newMovie.trailerUrl.required" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.TRAILER_URL_REQUIRED }}</span>
        <span v-else-if="!$v.newMovie.trailerUrl.url" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.TRAILER_URL }}</span>
        <span v-else-if="!$v.newMovie.trailerUrl.maxLength" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.TRAILER_URL_MAX_LENGTH }}</span>
        <label for="posterUrl">Poster URL:</label>
        <input id="posterUrl" type="text" v-model.trim="$v.newMovie.posterUrl.$model"
               :class="{invalidFiled: $v.newMovie.posterUrl.$error}"/>
        <span v-if="$v.newMovie.posterUrl.$dirty && !$v.newMovie.posterUrl.required" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.POSTER_URL_REQUIRED }}</span>
        <span v-else-if="!$v.newMovie.posterUrl.url" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.POSTER_URL }}</span>
        <span v-else-if="!$v.newMovie.posterUrl.maxLength" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.POSTER_URL_MAX_LENGTH }}</span>
      </div>
      <div class="audioAdd">
        <input id="english" type="radio" v-model="$v.newMovie.bulgarianLanguage.$model" :value="false"/>
        <label for="english">English Audio</label>
        <input id="bulgarian" type="radio" v-model="$v.newMovie.bulgarianLanguage.$model" :value="true"/>
        <label for="bulgarian">Bulgarian Audio</label>
        <span v-if="$v.newMovie.bulgarianLanguage.$dirty && !$v.newMovie.bulgarianLanguage.required"
              class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.LANGUAGE_REQUIRED }} </span>
      </div>
      <span class="description">Description:</span>
      <textarea class="addMovieTextarea" v-model.trim="$v.newMovie.description.$model"
                :class="{invalidFiled: $v.newMovie.description.$error}"></textarea>
      <span v-if="!$v.newMovie.description.maxLength" class="errorMessage"><font-awesome-icon
          icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.DESCRIPTION_MAX_LENGTH }}</span>
      <div class="platformsAdd">
        <div v-for="(platform , i) in platforms" :key="i">
          <input type="checkbox" :id="platform + 'Edit'" :name="platform + 'Edit'" :value="platform"
                 v-model="$v.newMovie.platforms.$model">
          <label :for="platform + 'Edit'">{{ platform }}</label>
        </div>
      </div>
      <span v-if="$v.newMovie.platforms.$dirty && (!$v.newMovie.platforms.required || !$v.newMovie.platforms.minLength)"
            class="errorMessage"><font-awesome-icon
          icon="fa-solid fa-circle-exclamation"/> {{ this.constants.error.PLATFORMS_REQUIRED }}</span>
      <button class="saveButton">
        SAVE MOVIE
      </button>
    </form>
    <modal name="newErrorModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <ErrorModal :errorMessage="this.errorMessage"/>
    </modal>
    <modal name="newSuccessfulModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <SuccessfulModal :success-message="this.successMessage"/>
    </modal>
  </div>
</template>

<script>

import {GenreService} from "@/services/genre-service";
import {PlatformService} from "@/services/platform-service";
import {MovieService} from "@/services/movie-service";
import {Constants} from "@/constants/constants";
import ErrorModal from "@/components/messages/ErrorModal";
import SuccessfulModal from "@/components/messages/SuccessfulModal";
import {between, maxLength, minLength, numeric, required, url} from "vuelidate/lib/validators";

export default {
  name: "AddMovie",
  components: {
    ErrorModal,
    SuccessfulModal
  },
  mounted() {
    this.loadGenres();
    this.loadPlatforms();
  },
  data() {
    return {
      constants: Constants,
      genreService: new GenreService(),
      platformService: new PlatformService(),
      movieService: new MovieService(),
      newMovie: {
        title1: null,
        title2: null,
        genres: [],
        duration: null,
        year: null,
        imdbUrl: null,
        trailerUrl: null,
        posterUrl: null,
        platforms: [],
        bulgarianLanguage: null,
        description: null,
      },
      genres: {},
      platforms: {},
      errorMessage: null,
      successMessage: null,
    }
  },
  validations: {
    newMovie: {
      title1: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(50),
      },
      title2: {
        // ToDo empty imput validate
        minLength: minLength(2),
        maxLength: maxLength(50),
      },
      duration: {
        required,
        numeric,
        between: between(30, 300)
      },
      year: {
        required,
        numeric,
        between: between(1950, 2100)
      },
      genres: {
        required,
        minLength: minLength(1)
      },
      platforms: {
        required,
        minLength: minLength(1)
      },
      imdbUrl: {
        // ToDo empty imput validate
        url,
        maxLength: maxLength(254)
      },
      trailerUrl: {
        required,
        maxLength: maxLength(254),
        url
      },
      posterUrl: {
        required,
        maxLength: maxLength(254),
        url
      },
      description: {
        // ToDo empty imput validate
        maxLength: maxLength(254)
      },
      bulgarianLanguage: {
        required
      }
    },
  },
  methods: {
    showSuccessModal() {
      this.$modal.show('newSuccessfulModal');
    },
    hideSuccessModal() {
      this.$modal.hide('newSuccessfulModal');
    },
    showErrorModal() {
      this.$modal.show('newErrorModal');
    },
    hideErrorModal() {
      this.$modal.hide('newErrorModal');
    },
    loadGenres() {
      this.genreService.findAllGenres().then((resp) => {
        if (resp.status === 'OK') {
          this.genres = resp.data;
        } else {
          this.errorMessage = 'Fill form genres!';
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 4000)
        }
      })
    },
    loadPlatforms() {
      this.platformService.findAllPlatforms().then((resp) => {
        if (resp.status === 'OK') {
          this.platforms = resp.data;
        } else {
          this.errorMessage = 'Errors in platforms!';
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 3000)
        }
      })
    },
    saveMovie() {
      this.$v.newMovie.$touch();
      if (!this.$v.newMovie.$invalid) {

      console.log('ok')
        this.movieService.addMovie(this.newMovie).then((response) => {
          if (response.status === 'OK'){
            this.$emit('addMovie');
          } else {
            this.errorMessage = response.error.message;
            this.showErrorModal();
            setTimeout(() => {this.hideErrorModal()} , 4000 )
          }
        })
      }
    },
    closeAddMovie() {
      this.$emit('closeAddMovie');
    }
  }
}
</script>

<style scoped>

div.container {
  background-color: #010340;
  height: 100%;
  width: 100%;
}

div.container p.closeButton {
  position: absolute;
  color: white;
  background-color: red;
  padding: 0.3rem 0.7rem;
  font-size: 2rem;
  right: 0;
  top: 0;
  text-align: center;
  font-family: "Helvetica",serif;
  font-weight: bold;
}

div.container p.closeButton:hover {
  cursor: pointer;
  border: 1px solid white;
}

div p.pageTitle {
  text-align: center;
  color: orange;
  font-size: 1.5rem;
  text-transform: uppercase;
  font-weight: bold;
  padding: 1rem;
}

form.addForm {
  margin: 0 2.0rem;
}

form.addForm div.rowError div.colError {
  display: inline-block;
  width: 45%;
  margin: 0 2.5%;
  font-size: 0.7rem;
}

form.addForm div.row div.col {
  display: inline-block;
  width: 45%;
  margin: 0.5% 2.5%;
}

form.addForm div.row div.col label {
  color: orange;
  display: inline-block;
  margin: 0.5rem 0;
  text-transform: uppercase;
  width: 100%;
  text-align: center;
}

form.addForm div.row div.col input {
  border-radius: 6px;
  font-size: 1.2rem;
  width: 100%;
  text-align: center;
  background-color: #74EBD5;
  background-image: linear-gradient(212deg, #74EBD5 0%, #f5f564 100%);
}

/* Genres */

form.addForm div.genresAdd,
form.addForm div.platformsAdd,
form.addForm div.audioAdd {
  display: flex;
  justify-content: space-around;
  padding: 0.4rem 1rem;
  flex-wrap: wrap;
}

form.addForm div.genresAdd div {
  display: inline-block;
  margin: 0 0.5rem;
}

form.addForm div.genresAdd label,
form.addForm div.platformsAdd label,
form.addForm div.audioAdd label {
  color: #b6b8b6;
  padding: 1rem;
  margin: 0.3rem;
  text-transform: uppercase;
}

form.addForm div.genresAdd input[type="checkbox"],
form.addForm div.platformsAdd input[type="checkbox"],
form.addForm div.audioAdd input[type="radio"] {
  display: none;
}

form.addForm div.genresAdd input[type="checkbox"] + *::before,
form.addForm div.platformsAdd input[type="checkbox"] + *::before,
form.addForm div.audioAdd input[type="radio"] + *::before {
  content: "";
  display: inline-block;
  vertical-align: bottom;
  width: 1rem;
  height: 1rem;
  margin-right: 0.3rem;
  border-radius: 10%;
  border-style: solid;
  border-width: 0.1rem;
  border-color: #b6b8b6;
  flex-shrink: 0;
}

form.addForm div.genresAdd input[type="checkbox"]:checked + *,
form.addForm div.platformsAdd input[type="checkbox"]:checked + *,
form.addForm div.audioAdd input[type="radio"]:checked + * {
  color: orange;
}

form.addForm div.genresAdd input[type="checkbox"]:checked + *::before,
form.addForm div.platformsAdd input[type="checkbox"]:checked + *::before,
form.addForm div.audioAdd input[type="radio"]:checked + *::before {
  content: "✓";
  color: orange;
  text-align: center;
  border-color: orange;
}

form.addForm div.genresAdd input[type="checkbox"] + *,
form.addForm div.platformsAdd input[type="checkbox"] + *,
form.addForm div.audioAdd input[type="radio"] + * {
  display: inline-flex;
  padding: 0.4rem 0;
}

form.addForm div.genresAdd input[type="checkbox"] + *:hover,
form.addForm div.platformsAdd input[type="checkbox"] + *:hover,
form.addForm div.audioAdd input[type="radio"] + *:hover {
  color: white;
  cursor: pointer;
}

form.addForm div.genresAdd input[type="checkbox"]:checked + *:hover,
form.addForm div.platformsAdd input[type="checkbox"]:checked + *:hover,
form.addForm div.audioAdd input[type="radio"]:checked + *:hover {
  color: orange;
  cursor: pointer;
}

/* URLs */

form.addForm div.urlsAdd {
  padding: 0.4rem 1rem;
}

form.addForm div.urlsAdd label {
  color: orange;
  display: inline-block;
  margin: 0.5rem 0;
  text-transform: uppercase;
  width: 100%;
  /*text-align: center;*/

}

form.addForm div.urlsAdd input {
  border-radius: 6px;
  font-size: 1.2rem;
  width: 100%;
  background-color: #74EBD5;
  background-image: linear-gradient(212deg, #74EBD5 0%, #f5f564 100%);

}

form.addForm span.description {
  color: orange;
  display: inline-block;
  margin: 0.5rem 2.5%;
  text-transform: uppercase;
  width: 100%;
}

form.addForm .addMovieTextarea {
  margin: 0 1rem 1rem 1rem;
  border-radius: 6px;
  font-size: 1.2rem;
  width: 96%;
  height: 100px;
  background-color: #74EBD5;
  background-image: linear-gradient(212deg, #74EBD5 0%, #f5f564 100%);

}

button.saveButton {
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

button.saveButton:hover {
  background-color: #08AEEA;
  background-image: linear-gradient(223deg, #08AEEA 0%, #2AF598 100%);
  cursor: pointer;
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
  width: 80%;
  margin: 10px 10%;
  border: 1px solid #D8000C;
}

.invalidFiled {
  border: 3px solid red;
}

input:focus, textarea:focus, select:focus {
  outline: none;
}
</style>