<template>
  <div>
    <div class="overlay">

    </div>
    <div class="container">
      <p class="closeButton" @click="closeEditMovie">X</p>
      <p class="pageTitle">Edit movie</p>
      <form @submit.prevent="saveEditMovie" class="editForm">
        <div class="row">
          <div class="col">
            <label for="title1">Title:</label>
            <input id="title1" v-model.trim="$v.editedMovie.title1.$model" type="text"/>
          </div>
          <div class="col">
            <label for="title2">Second Title:</label>
            <input id="title2" v-model.trim="$v.editedMovie.title2.$model" type="text"/>
          </div>
        </div>
        <div class="rowError">
          <div class="colError">
            <span v-if="!$v.editedMovie.title1.required" class="errorMessage"><font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.TITLE1_REQUIRED }}</span>
            <span v-else-if="!$v.editedMovie.title1.minLength || !$v.editedMovie.title1.maxLength" class="errorMessage"><font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.TITLE1_LENGTH }}</span>
          </div>
          <div class="colError">
            <span v-if="!$v.editedMovie.title2.minLength || !$v.editedMovie.title2.maxLength" class="errorMessage"><font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.TITLE2_LENGTH }}</span>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <label for="duration">Duration:</label>
            <input id="duration" v-model.number="$v.editedMovie.duration.$model" type="number"/>
          </div>
          <div class="col">
            <label for="year">Year:</label>
            <input id="year" v-model.number="$v.editedMovie.year.$model" type="number"/>
          </div>
        </div>
        <div class="rowError">
          <div class="colError">
            <span v-if="!$v.editedMovie.duration.required" class="errorMessage"><font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.DURATION_REQUIRED }}</span>
            <span v-else-if="!$v.editedMovie.duration.numeric" class="errorMessage"><font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.DURATION_NUMERIC }}</span>
            <span v-else-if="!$v.editedMovie.duration.between" class="errorMessage"><font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.DURATION_BETWEEN }}</span>
          </div>
          <div class="colError">
            <span v-if="!$v.editedMovie.year.required" class="errorMessage"><font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.YEAR_REQUIRED }}</span>
            <span v-else-if="!$v.editedMovie.year.numeric" class="errorMessage"><font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.YEAR_NUMERIC }}</span>
            <span v-else-if="!$v.editedMovie.year.between" class="errorMessage"><font-awesome-icon
                icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.YEAR_BETWEEN }}</span>
          </div>
        </div>
        <div class="genresEdit">
          <div v-for="(genre , i) in genres" :key="i">
            <input type="checkbox" :id="genre + 'Edit'" :name="genre + 'Edit'" :value="genre"
                   v-model="$v.editedMovie.genres.$model">
            <label :for="genre + 'Edit'">{{ genre }}</label>
          </div>
          <span v-if="!$v.editedMovie.genres.required || !$v.editedMovie.genres.minLength" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.GENRES_REQUIRED }}</span>
          <span v-if="!$v.editedMovie.genres.maxLength" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.GENRES_MAX_LENGTH }}</span>
        </div>
        <div class="urlsEdit">
          <label for="imdbUrl">IMDb URL:</label>
          <input id="imdbUrl" type="text" v-model.trim="$v.editedMovie.imdbUrl.$model"/>
          <span v-if="!$v.editedMovie.imdbUrl.url" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.IMDB_URL }}</span>
          <span v-else-if="!$v.editedMovie.imdbUrl.maxLength" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.IMDB_URL_MAX_LENGTH }}</span>
          <label for="trailerUrl">Trailer URL:</label>
          <input id="trailerUrl" type="text" v-model.trim="$v.editedMovie.trailerUrl.$model"/>
          <span v-if="!$v.editedMovie.trailerUrl.required" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.TRAILER_URL_REQUIRED }}</span>
          <span v-else-if="!$v.editedMovie.trailerUrl.url" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.TRAILER_URL }}</span>
          <span v-else-if="!$v.editedMovie.trailerUrl.maxLength" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.TRAILER_URL_MAX_LENGTH }}</span>
          <label for="posterUrl">Poster URL:</label>
          <input id="posterUrl" type="text" v-model.trim="$v.editedMovie.posterUrl.$model"/>
          <span v-if="!$v.editedMovie.posterUrl.required" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.POSTER_URL_REQUIRED }}</span>
          <span v-else-if="!$v.editedMovie.posterUrl.url" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.POSTER_URL }}</span>
          <span v-else-if="!$v.editedMovie.posterUrl.maxLength" class="errorMessage"><font-awesome-icon
              icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.POSTER_URL_MAX_LENGTH }}</span>
        </div>
        <div class="audioEdit">
          <input id="english" type="radio" v-model="editedMovie.bulgarianLanguage" :value="false"/>
          <label for="english">English Audio</label>
          <input id="bulgarian" type="radio" v-model="editedMovie.bulgarianLanguage" :value="true"/>
          <label for="bulgarian">Bulgarian Audio</label>
        </div>
        <span class="description">Description:</span>
        <textarea class="editMovieTextarea" v-model.trim="$v.editedMovie.description.$model"></textarea>
        <span v-if="!$v.editedMovie.description.maxLength" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.DESCRIPTION_MAX_LENGTH }}</span>
        <div class="platformsEdit">
          <div v-for="(platform , i) in platforms" :key="i">
            <input type="checkbox" :id="platform + 'Edit'" :name="platform + 'Edit'" :value="platform"
                   v-model="$v.editedMovie.platforms.$model">
            <label :for="platform + 'Edit'">{{ platform }}</label>
          </div>
        </div>
        <span v-if="!$v.editedMovie.platforms.maxLength" class="errorMessage"><font-awesome-icon
            icon="fa-solid fa-circle-exclamation"/> {{ this.constants.ERROR.PLATFORMS_MAX_LENGTH }}</span>
        <button class="saveButton">
          SAVE MOVIE
        </button>
      </form>
    </div>
    <modal name="editErrorModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <ErrorModal :errorMessage="this.errorMessage"/>
    </modal>
  </div>
</template>

<script>

import {GenreService} from "@/services/genre-service";
import {PlatformService} from "@/services/platform-service";
import {MovieService} from "@/services/movie-service";
import {between, maxLength, minLength, numeric, required, url} from "vuelidate/lib/validators";
import ErrorModal from "@/components/messages/ErrorModal";
import {Constants} from "@/constants/constants";

export default {
  name: "EditMovie",
  components: {
    ErrorModal
  },
  props: {
    movieId: {
      type: Number,
      required: true
    }
  },
  created() {
    this.movieService.findMovieDetail(this.movieId).then((movieDetailsDto) => {
      if (movieDetailsDto.status === 'OK') {
        this.editedMovie = movieDetailsDto.data;
      } else {
        this.errorMessage = 'Error in clickDetails!';
        this.showErrorModal();
        setTimeout(() => {
          this.hideErrorModal()
        }, 4000)
      }
    })
  },
  data() {
    return {
      constants: Constants,
      genreService: new GenreService(),
      platformService: new PlatformService(),
      movieService: new MovieService(),
      editedMovie: {
        movieId: null,
        title1: null,
        title2: null,
        duration: null,
        year: null,
        genres: [],
        platforms: [],
        imdbUrl: null,
        trailerUrl: null,
        posterUrl: null,
        description: null,
        bulgarianLanguage: null
      },
      errorMessage: null,
      successMessage: null
    }
  },
  validations: {
    editedMovie: {
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
        minLength: minLength(1),
        maxLength: maxLength(4)
      },
      platforms: {
        maxLength: maxLength(4)
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
    }
  },
  methods: {
    showErrorModal() {
      this.$modal.show('editErrorModal');
    },
    hideErrorModal() {
      this.$modal.hide('editErrorModal');
    },
    saveEditMovie() {
      if (this.$v.$invalid) {

        this.errorMessage = 'Fill in the form correctly!'
        this.showErrorModal();
        setTimeout(() => {
          this.hideErrorModal()
        }, 3000)

      } else {

        let editedMovieDto = this.editedMovie;

        this.movieService.saveEditedMovie(editedMovieDto).then((resp) => {

          if (resp.status === 'OK') {

            this.editedMovie = resp.data;
            this.$router.push({name: 'details', params: {movieId: this.editedMovie.movieId}})

          } else {

            if (resp.error === 'Request failed with status code 401') {
              //ToDo error message
              this.$store.dispatch('auth/logout');
              this.$router.push({name: 'home'})

            } else {

              this.errorMessage = resp.error.message;
              this.showErrorModal();
              setTimeout(() => {
                this.hideErrorModal()
              }, 4000)

              //ToDo error
              this.router.push({name: 'movies'})
            }
          }
        })
      }
    },
    closeEditMovie() {
      this.$router.push({name: 'details', params: {movieId: this.editedMovie.movieId}});
    },
  },
  computed: {
    genres() {
      return this.$store.getters.getGenres;
    },
    platforms() {
      return this.$store.getters.getPlatforms;
    },
  }
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

div.container {
  background-color: #010340;
  height: 40rem;
  width: 70rem;
  position: fixed;
  z-index: 40;
  overflow-y: scroll;
  overflow-x: hidden;

  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
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
  font-family: "Helvetica", serif;
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

form.editForm {
  margin: 0 2.0rem;
}

form.editForm div.rowError div.colError {
  display: inline-block;
  width: 45%;
  margin: 0 2.5%;
  font-size: 0.7rem;
}

form.editForm div.row div.col {
  display: inline-block;
  width: 45%;
  margin: 0.5% 2.5%;
}

form.editForm div.row div.col label {
  color: orange;
  display: inline-block;
  margin: 0.5rem 0;
  text-transform: uppercase;
  width: 100%;
  text-align: center;
}

form.editForm div.row div.col input {
  border-radius: 6px;
  font-size: 1.2rem;
  width: 100%;
  text-align: center;
  background-color: #74EBD5;
  background-image: linear-gradient(212deg, #74EBD5 0%, #f5f564 100%);
}

/* Genres */

form.editForm div.genresEdit,
form.editForm div.platformsEdit,
form.editForm div.audioEdit {
  display: flex;
  justify-content: space-around;
  padding: 0.4rem 1rem;
  flex-wrap: wrap;
}

form.editForm div.genresEdit div {
  display: inline-block;
  margin: 0 0.5rem;
}

form.editForm div.genresEdit label,
form.editForm div.platformsEdit label,
form.editForm div.audioEdit label {
  color: #b6b8b6;
  padding: 1rem;
  margin: 0.3rem;
  text-transform: uppercase;
}

form.editForm div.genresEdit input[type="checkbox"],
form.editForm div.platformsEdit input[type="checkbox"],
form.editForm div.audioEdit input[type="radio"] {
  display: none;
}

form.editForm div.genresEdit input[type="checkbox"] + *::before,
form.editForm div.platformsEdit input[type="checkbox"] + *::before,
form.editForm div.audioEdit input[type="radio"] + *::before {
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

form.editForm div.genresEdit input[type="checkbox"]:checked + *,
form.editForm div.platformsEdit input[type="checkbox"]:checked + *,
form.editForm div.audioEdit input[type="radio"]:checked + * {
  color: orange;
}

form.editForm div.genresEdit input[type="checkbox"]:checked + *::before,
form.editForm div.platformsEdit input[type="checkbox"]:checked + *::before,
form.editForm div.audioEdit input[type="radio"]:checked + *::before {
  content: "✓";
  color: orange;
  text-align: center;
  border-color: orange;
}

form.editForm div.genresEdit input[type="checkbox"] + *,
form.editForm div.platformsEdit input[type="checkbox"] + *,
form.editForm div.audioEdit input[type="radio"] + * {
  display: inline-flex;
  padding: 0.4rem 0;
}

form.editForm div.genresEdit input[type="checkbox"] + *:hover,
form.editForm div.platformsEdit input[type="checkbox"] + *:hover,
form.editForm div.audioEdit input[type="radio"] + *:hover {
  color: white;
  cursor: pointer;
}

form.editForm div.genresEdit input[type="checkbox"]:checked + *:hover,
form.editForm div.platformsEdit input[type="checkbox"]:checked + *:hover,
form.editForm div.audioEdit input[type="radio"]:checked + *:hover {
  color: orange;
  cursor: pointer;
}

/* URLs */

form.editForm div.urlsEdit {
  padding: 0.4rem 1rem;
}

form.editForm div.urlsEdit label {
  color: orange;
  display: inline-block;
  margin: 0.5rem 0;
  text-transform: uppercase;
  width: 100%;
  /*text-align: center;*/

}

form.editForm div.urlsEdit input {
  border-radius: 6px;
  font-size: 1.2rem;
  width: 100%;
  background-color: #74EBD5;
  background-image: linear-gradient(212deg, #74EBD5 0%, #f5f564 100%);

}

form.editForm span.description {
  color: orange;
  display: inline-block;
  margin: 0.5rem 2.5%;
  text-transform: uppercase;
  width: 100%;
}

form.editForm .editMovieTextarea {
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

input:focus, textarea:focus, select:focus {
  outline: none;
}
</style>