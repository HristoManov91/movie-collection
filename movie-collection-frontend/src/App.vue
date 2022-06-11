<template>
  <div id="app">
    <header>
      <p>My favorite movies</p>
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
        <ul class="movies">
          <MovieCard v-for="(movie , id) in moviesToShow" :key="id" :movie="movie" @clickDetails="clickDetails"/>
        </ul>
      </section>
    </main>
    <modal class="modalElement"
           name="movieDetailsModal"
           :resizable="false"
           :reset="true"
           width="830px"
           height="550px">
      <DetailsView :movie="this.movie"/>
    </modal>
    <modal name="errorModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <ErrorModal :errorMessage="this.errorMessage"/>
    </modal>
    <modal name="successfulModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <SuccessfulModal/>
    </modal>
    <modal name="warningModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <WarningModal />
    </modal>
  </div>
</template>
<script>

import {MovieService} from "@/services/movie-service";
import {GenreService} from "@/services/genre-service";
import DetailsView from "@/components/DetailsView";
import MovieCard from "@/components/MovieCard";
import ErrorModal from "@/components/modals/ErrorModal";
import SuccessfulModal from "@/components/modals/SuccessfulModal";
import WarningModal from "@/components/modals/WarningModal";

export default {
  name: 'App',
  components: {
    DetailsView,
    MovieCard,
    ErrorModal,
    SuccessfulModal,
    WarningModal
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
      errorMessage: null,
    }
  },
  methods: {
    showDetailsModal() {
      this.$modal.show('movieDetailsModal');
    },
    hide() {
      this.$modal.hide('movieDetailsModal');
    },
    showSuccessModal() {
      this.$modal.show('successfulModal');
    },
    hideSuccessModal(){
      this.$modal.hide('successfulModal');
    },
    showErrorModal() {
      this.$modal.show('errorModal');
    },
    hideErrorModal(){
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
          setTimeout(() => {this.hideErrorModal()} , 4000 )
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
          setTimeout(() => {this.hideErrorModal()} , 4000 )
        }
      })
    },
    clickDetails(movieId) {
      this.movieService.findMovieDetail(movieId).then((movieDetailsDto) => {
        if (movieDetailsDto.status === 'OK') {
          this.movie = movieDetailsDto.data;
          this.showDetailsModal();
        } else {
          this.errorMessage = 'Error in clickDetails!';
          this.showErrorModal();
          setTimeout(() => {this.hideErrorModal()} , 4000 )
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
      //ToDo make to load and other movie in database
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

      if (this.moviesToShow.length === 0){
        this.showWarningModal();
        setTimeout(() => {this.hideWarningModal()} , 4000 )
      }
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
    }
  }
}
</script>

<style scoped>
@import url("./assets/css/main.css");
</style>
