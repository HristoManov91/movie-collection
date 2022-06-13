<template>
  <div id="app">
    <header>
      <nav>
        <ul>
          <li>HOME</li>
          <li @click="addMovie">ADD MOVIE</li>
        </ul>
      </nav>
      <!--      <p>My favorite movies</p>-->
      <img src="./assets/image/welcome-image.jpg" alt="home-cinema-image">
    </header>
    <main class="container">
      <aside class="filter">
        <p>Filters</p>
        <span class="filterTitle">Genres:</span>
        <div v-for="(genre , i) in genres" :key="i">
          <input type="checkbox" :id="i" :name="genre" :value="genre">
          <label @click="addOrRemoveInGenresFilter(i)" :for="i">{{ genre }}</label>
        </div>
        <span class="filterTitle">Duration:</span>
        <div class="values">
          <span id="durationRange1">{{ this.durationFilter.minDuration }}</span>
          <span> &dash; </span>
          <span id="durationRange2">{{ this.durationFilter.maxDuration }}</span>
        </div>
        <div class="durationSliderTrack"></div>
        <input class="sliderRange" v-model="durationFilter.minDuration" id="durationSlider1" type="range" min="0"
               max="300"
               step="5">
        <input class="sliderRange" v-model="durationFilter.maxDuration" id="durationSlider2" type="range" min="0"
               max="300"
               step="5">
        <span class="ratingFilterTitle">IMDb rating:</span>
        <div class="values">
          <span id="ratingRange1">{{ this.ratingFilter.minRating }}</span>
          <span> &dash; </span>
          <span id="ratingRange2">{{ this.ratingFilter.maxRating }}</span>
        </div>
        <div class="ratingSliderTrack"></div>
        <input class="sliderRange" v-model="ratingFilter.minRating" id="ratingSlider1" type="range" min="0" max="10"
               step="0.1">
        <input class="sliderRange" v-model="ratingFilter.maxRating" id="ratingSlider2" type="range" min="0" max="10"
               step="0.1">
      </aside>
      <section class="catalogue">
        <p class="movies-title">Collection</p>
        <input class="searchInput" v-model.trim="searchTextFilter" type="text" placeholder="Search...">
        <ul class="movies">
          <MovieCard v-for="(movie , id) in moviesToShow" :key="id" :movie="movie" @clickDetails="clickDetails"/>
        </ul>
      </section>
    </main>
    <modal class="modalElement"
           name="movieDetailsModal"
           :resizable="false"
           :reset="true"
           width="860px"
           height="550px">
      <DetailsView :movie="this.movie" @deleteMovie="deletedMovie"/>
    </modal>
    <modal name="errorModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <ErrorModal :errorMessage="this.errorMessage"/>
    </modal>
    <modal name="successfulModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <SuccessfulModal :success-message="this.successMessage"/>
    </modal>
    <modal name="warningModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <WarningModal/>
    </modal>
    <modal class="addMovieModal"
           name="addMovieModal"
           :resizable="false"
           :reset="true"
           width="850px"
           height="auto"
           :scrollable="true">
      <AddMovie @addMovie="successAddMovie"/>
    </modal>
  </div>
</template>
<script>

import {MovieService} from "@/services/movie-service";
import {GenreService} from "@/services/genre-service";
import DetailsView from "@/components/DetailsView";
import MovieCard from "@/components/MovieCard";
import ErrorModal from "@/components/messages/ErrorModal";
import SuccessfulModal from "@/components/messages/SuccessfulModal";
import WarningModal from "@/components/messages/WarningModal";
import AddMovie from "@/components/AddMovie";

export default {
  name: 'App',
  components: {
    DetailsView,
    MovieCard,
    ErrorModal,
    SuccessfulModal,
    WarningModal,
    AddMovie
  },
  mounted() {
    this.loadMovies();
    this.loadGenres();
    this.fillDurationSlideColor();
    this.fillRatingSlideColor();
  },
  data() {
    return {
      movieService: new MovieService(),
      genreService: new GenreService(),
      movies: [],
      moviesToShow: [],
      movie: {
        movieId: {
          required: true,
          type: Number
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
      },
      genres: [],
      genresFilter: [],
      durationFilter: {
        minDuration: 0,
        maxDuration: 300,
        minGap: 10,
      },
      ratingFilter: {
        minRating: 0,
        maxRating: 10,
        minGap: 1,
      },
      searchTextFilter: '',
      errorMessage: null,
      successMessage: null
    }
  },
  methods: {
    showAddMovieModal() {
      this.$modal.show('addMovieModal');
    },
    hideAddMovieModal() {
      this.$modal.hide('addMovieModal');
    },
    showDetailsModal() {
      this.$modal.show('movieDetailsModal');
    },
    hideDetailsModal() {
      this.$modal.hide('movieDetailsModal');
    },
    showSuccessModal() {
      this.$modal.show('successfulModal');
    },
    hideSuccessModal() {
      this.$modal.hide('successfulModal');
    },
    showErrorModal() {
      this.$modal.show('errorModal');
    },
    hideErrorModal() {
      this.$modal.hide('errorModal');
    },
    showWarningModal() {
      this.$modal.show('warningModal');
    },
    hideWarningModal() {
      this.$modal.hide('warningModal');
    },
    fillDurationSlideColor() {
      //ToDo fix % calculate from 30 to 300
      let startPercent = (this.durationFilter.minDuration / 300) * 100;
      let overPercent = (this.durationFilter.maxDuration / 300) * 100;

      let sliderTrackElement = document.querySelector('.durationSliderTrack');
      sliderTrackElement.style.background = `linear-gradient(to right, white ${startPercent}% , orange ${startPercent}% , orange ${overPercent}% , white ${overPercent}%)`;
    },
    fillRatingSlideColor() {
      let startPercent = (this.ratingFilter.minRating / 10) * 100;
      let overPercent = (this.ratingFilter.maxRating / 10) * 100;

      let sliderTrackElement = document.querySelector('.ratingSliderTrack');
      sliderTrackElement.style.background = `linear-gradient(to right, white ${startPercent}% , orange ${startPercent}% , orange ${overPercent}% , white ${overPercent}%)`;
    },
    loadMovies() {
      this.movieService.findAllMovies().then((moviePreviewDto) => {
        if (moviePreviewDto.status === 'OK') {
          this.movies = moviePreviewDto.data;
          this.moviesToShow = this.movies;
        } else {
          this.errorMessage = 'Error in loadMovies!';
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 4000)
        }
      });
      // .finally() ToDo
    },
    loadGenres() {
      this.genreService.findAllGenres().then((resp) => {
        if (resp.status === 'OK') {
          this.genres = resp.data;
        } else {
          this.errorMessage = 'Error in loadGenres!';
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 4000)
        }
      })
    },
    caseInsensitiveCheck(word1, word2) {
      word1 = word1.toLowerCase();
      word2 = word2.toLowerCase();
      return word1.includes(word2);
    },
    addMovie() {
      this.showAddMovieModal();
    },
    clickDetails(movieId) {
      this.movieService.findMovieDetail(movieId).then((movieDetailsDto) => {
        if (movieDetailsDto.status === 'OK') {
          this.movie = movieDetailsDto.data;
          this.showDetailsModal();
        } else {
          this.errorMessage = 'Error in clickDetails!';
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 4000)
        }
      })
    },
    addOrRemoveInGenresFilter(index) {
      let genre = this.genres[index];
      let elementIndex = this.genresFilter.indexOf(genre);

      if (elementIndex === -1) {
        this.genresFilter.push(genre)
      } else {
        this.genresFilter.splice(elementIndex, 1);
      }

      this.filtersMovies();
    },
    filtersMovies() {
      //ToDo make to load and other movies in database
      this.moviesToShow = [];

      if (this.genresFilter.length === 0) {
        this.moviesToShow = this.movies;
      } else {
        this.moviesToShow = this.movies.filter(m => {
          for (const genre of this.genresFilter) {
            if (m.genres.includes(genre)) {
              return true;
            }
          }
          return false;
        });
      }

      this.moviesToShow = this.moviesToShow.filter((m) => {
        return m.duration >= this.durationFilter.minDuration && m.duration <= this.durationFilter.maxDuration;
      });

      this.moviesToShow = this.moviesToShow.filter((m) => {
        return m.rating >= this.ratingFilter.minRating && m.rating <= this.ratingFilter.maxRating;
      })

      this.moviesToShow = this.moviesToShow.filter((m) => {
        if (m.title2 !== null) {
          return this.caseInsensitiveCheck(m.title1, this.searchTextFilter)
              || this.caseInsensitiveCheck(m.title2, this.searchTextFilter);
        } else {
          return this.caseInsensitiveCheck(m.title1, this.searchTextFilter);
        }
      })

      if (this.moviesToShow.length === 0) {
        this.showWarningModal();
        setTimeout(() => {
          this.hideWarningModal()
        }, 4000)
      }
    },
    deletedMovie(isDeleted) {

      if (isDeleted) {
        this.hideDetailsModal();
        this.successMessage = 'The movie was successfully deleted!';
        this.showSuccessModal();
        setTimeout(() => {
          this.hideSuccessModal()
        }, 4000)

        this.loadMovies();
        this.filtersMovies();

      } else {

        this.errorMessage = 'We could not delete the movie!'
        this.showErrorModal();
        setTimeout(() => {
          this.hideErrorModal()
        }, 3000)

      }
    },
    successAddMovie(){
      this.hideAddMovieModal();
      this.successMessage = 'The movie was successfully added!';
      this.showSuccessModal();
      setTimeout(() => {this.hideSuccessModal()}, 4000)

      this.loadMovies();
      this.filtersMovies();
    }
  },
  watch: {
    durationFilter: {
      handler(value) {
        if (typeof value.minDuration === 'string' && this.durationFilter.maxDuration - Number(this.durationFilter.minDuration) <= this.durationFilter.minGap) {

          this.durationFilter.minDuration = this.durationFilter.maxDuration - this.durationFilter.minGap;

        } else if (typeof value.maxDuration === 'string' && Number(this.durationFilter.maxDuration) - this.durationFilter.minDuration <= this.durationFilter.minGap) {

          this.durationFilter.maxDuration = this.durationFilter.minDuration + this.durationFilter.minGap;

        }

        this.fillDurationSlideColor();
        this.filtersMovies();
      },
      deep: true
    },
    ratingFilter: {
      handler(value) {
        if (typeof value.minRating === 'string' && this.ratingFilter.maxRating - Number(this.ratingFilter.minRating) <= this.ratingFilter.minGap) {

          this.ratingFilter.minRating = this.ratingFilter.maxRating - this.ratingFilter.minGap;

        } else if (typeof value.maxRating === 'string' && Number(this.ratingFilter.maxRating) - this.ratingFilter.minRating <= this.ratingFilter.minGap) {

          this.ratingFilter.maxRating = this.ratingFilter.minRating + this.ratingFilter.minGap;

        }

        this.fillRatingSlideColor();
        this.filtersMovies();
      },
      deep: true
    },
    searchTextFilter() {
      this.filtersMovies();
    }
  }
}
</script>

<style scoped>
@import url("./assets/css/main.css");
</style>
