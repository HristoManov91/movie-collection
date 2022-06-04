<template>
  <div class="container">
    <p class="pageTitle">Edit movie</p>
    <form class="editForm">
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
          <span v-if="!$v.editedMovie.title1.required" class="errorMessage">Title is required and cannot be empty string!</span>
          <span v-else-if="!$v.editedMovie.title1.minLength || !$v.editedMovie.title1.maxLength" class="errorMessage">Length must be between 2 and 50 characters!</span>
        </div>
        <div class="colError">
          <span v-if="!$v.editedMovie.title2.minLength || !$v.editedMovie.title2.maxLength" class="errorMessage">Length must be between 2 and 50 characters!</span>
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
          <span v-if="!$v.editedMovie.duration.required" class="errorMessage">Duration is required!</span>
          <span v-else-if="!$v.editedMovie.duration.numeric" class="errorMessage">Duration must be contains only numbers!</span>
          <span v-else-if="!$v.editedMovie.duration.between" class="errorMessage">Duration must be between 30 and 300 minutes!</span>
        </div>
        <div class="colError">
          <span v-if="!$v.editedMovie.year.required" class="errorMessage">Year is required!</span>
          <span v-else-if="!$v.editedMovie.year.numeric" class="errorMessage">Year must be contains only numbers!</span>
          <span v-else-if="!$v.editedMovie.year.between" class="errorMessage">Year must be between 1950 and 2100 year!</span>
        </div>
      </div>
      <div class="genresEdit" >
        <div v-for="(genre , i) in genres" :key="i">
          <input type="checkbox" :id="genre + 'Edit'" :name="genre + 'Edit'" :value="genre"
                 v-model="$v.editedMovie.genres.$model">
          <label :for="genre + 'Edit'">{{ genre }}</label>
        </div>
        <span v-if="!$v.editedMovie.genres.required || !$v.editedMovie.genres.minLength" class="errorMessage">Genres is required! You must be select at least one genre!</span>
      </div>
      <div class="urlsEdit">
        <label for="imdbUrl">IMDb URL:</label>
        <input id="imdbUrl" type="text" v-model.trim="$v.editedMovie.imdbUrl.$model"/>
        <span v-if="!$v.editedMovie.imdbUrl.url" class="errorMessage">IMDb URL must be a valid URL!</span>
        <span v-else-if="!$v.editedMovie.imdbUrl.maxLength" class="errorMessage">IMDb URL max length must be 254 characters!</span>
        <label for="trailerUrl">Trailer URL:</label>
        <input id="trailerUrl" type="text" v-model.trim="$v.editedMovie.trailerUrl.$model"/>
        <span v-if="!$v.editedMovie.trailerUrl.required" class="errorMessage">Trailer URL is required!</span>
        <span v-else-if="!$v.editedMovie.trailerUrl.url" class="errorMessage">Trailer URL must be a valid URL!</span>
        <span v-else-if="!$v.editedMovie.trailerUrl.maxLength" class="errorMessage">Trailer URL max length must be 254 characters!</span>
        <label for="posterUrl">Poster URL:</label>
        <input id="posterUrl" type="text" v-model.trim="$v.editedMovie.posterUrl.$model"/>
        <span v-if="!$v.editedMovie.posterUrl.required" class="errorMessage">Poster URL is required!</span>
        <span v-else-if="!$v.editedMovie.posterUrl.url" class="errorMessage">Poster URL must be a valid URL!</span>
        <span v-else-if="!$v.editedMovie.posterUrl.maxLength" class="errorMessage">Poster URL max length must be 254 characters!</span>
      </div>
      <div class="audioEdit">
        <input id="english" type="radio" v-model="editedMovie.bulgarianLanguage" :value="false"/>
        <label for="english">English Audio</label>
        <input id="bulgarian" type="radio" v-model="editedMovie.bulgarianLanguage" :value="true"/>
        <label for="bulgarian">Bulgarian Audio</label>
      </div>
      <span class="description">Description:</span>
      <textarea class="editMovieTextarea" v-model.trim="$v.editedMovie.description.$model"></textarea>
      <span v-if="!$v.editedMovie.description.maxLength" class="errorMessage">Description text max length must be 254 characters!</span>
      <div class="platformsEdit">
        <div v-for="(platform , i) in platforms" :key="i">
          <input type="checkbox" :id="platform + 'Edit'" :name="platform + 'Edit'" :value="platform"
                 v-model="$v.editedMovie.platforms.$model">
          <label :for="platform + 'Edit'">{{ platform }}</label>
        </div>
      </div>
      <span v-if="!$v.editedMovie.platforms.required || !$v.editedMovie.platforms.minLength" class="errorMessage">Platforms is required! You must be select at least one platform!</span>
      <button class="saveButton" @click="saveEditMovie()">
        SAVE MOVIE
      </button>
    </form>
  </div>
</template>

<script>

import {GenreService} from "@/services/genre-service";
import {PlatformService} from "@/services/platform-service";
import {MovieService} from "@/services/movie-service";
import {between, maxLength, minLength, numeric, required, url} from "vuelidate/lib/validators";

export default {
  name: "EditMovie",
  mounted() {
    this.loadGenres();
    this.loadPlatforms();
    this.loadMovieData();
  },
  props: {
    movie: {
      movieId: {
        type: Number,
        required: true
      },
      title1: String,
      title2: String,
      genres: {
        type: {},
      },
      duration: Number,
      year: Number,
      rating: Number,
      imdbUrl: String,
      trailerUrl: String,
      posterUrl: String,
      platforms: {
        type: {},
      },
      bulgarianLanguage: Boolean,
      description: String,
    }
  },
  data() {
    return {
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
      genres: {},
      platforms: {}
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
        between: between(30 , 300)
      },
      year: {
        required,
        numeric,
        between: between(1950 , 2100)
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
    }
  },
  methods: {
    loadGenres() {
      this.genreService.findAllGenres().then((resp) => {
        if (resp.status === 'OK') {
          this.genres = resp.data;
        } else {
          alert('Error in genres')
        }
      })
    },
    loadPlatforms() {
      this.platformService.findAllPlatforms().then((resp) => {
        if (resp.status === 'OK') {
          this.platforms = resp.data;
        } else {
          alert('Error in platforms')
        }
      })
    },
    loadMovieData() {
      this.editedMovie = this.movie;
    },
    saveEditMovie() {
      console.log('editMovie' , this.editedMovie)
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
  display:inline-block;
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
  color: white;
  letter-spacing: 1px;
  padding: 5px;
  text-align: center;
  background-color: red;
  border-radius: 6px;
  width: 80%;
  margin: 10px 10%;
}

</style>