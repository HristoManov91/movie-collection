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
        <div>
          <div class="values">
            <span id="range1">{{this.durationFilter.minDuration}}</span>
            <span> &dash; </span>
            <span id="range2">{{this.durationFilter.maxDuration}}</span>
          </div>
          <div class="sliderTrack"></div>
          <input class="sliderRange" v-model="durationFilter.minDuration" id="slider1" type="range" min="0" max="300" step="5">
          <input class="sliderRange" v-model="durationFilter.maxDuration" id="slider2" type="range" min="0" max="300" step="5">
        </div>
<!--        <span class="filterTitle">IMDb rating:</span>-->
<!--        <div>-->

<!--        </div>-->
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
           width="800px"
           height="580px">
      <DetailsView :movie="this.movie"/>
    </modal>

  </div>
</template>
<script>

import {MovieService} from "@/services/movie-service";
import {GenreService} from "@/services/genre-service";
import DetailsView from "@/components/DetailsView";
import MovieCard from "@/components/MovieCard";

export default {
  name: 'App',
  components: {
    DetailsView,
    MovieCard,
  },
  mounted() {
    this.loadMovies();
    this.loadGenres();
    this.fillSlideColor();
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
        maxDuration: 300
      },
      minGap: 10,
    }
  },
  methods: {
    show() {
      this.$modal.show('movieDetailsModal');
    },
    hide() {
      this.$modal.hide('movieDetailsModal');
    },
    fillSlideColor() {
      //ToDo fix % calculate from 30 to 300
      let startPercent = ( this.durationFilter.minDuration / 300 ) * 100;
      let overPercent = ( this.durationFilter.maxDuration / 300 ) * 100;

      let sliderTrackElement = document.querySelector('.sliderTrack');
      sliderTrackElement.style.background = `linear-gradient(to right, white ${startPercent}% , orange ${startPercent}% , orange ${overPercent}% , white ${overPercent}%)`;
    },
    loadMovies() {
      this.movieService.findAllMovies().then((moviePreviewDto) => {
        if (moviePreviewDto.status === 'OK') {
          this.movies = moviePreviewDto.data;
          this.moviesToShow = this.movies;
        } else {
          alert('Error')
        }
      });
      // .finally() ToDo
    },
    loadGenres() {
      this.genreService.findAllGenres().then((resp) => {
        if (resp.status === 'OK') {
          this.genres = resp.data;
        } else {
          alert('Error')
        }
      })
    },
    clickDetails(movieId) {
      this.movieService.findMovieDetail(movieId).then((movieDetailsDto) => {
        if (movieDetailsDto.status === 'OK') {
          this.movie = movieDetailsDto.data;
          this.show();
        } else {
          alert('Error')
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
    },

  },
  watch: {
    genresFilter: function (newValue) {
      if (newValue.length === 0) {
        this.moviesToShow = this.movies;
      } else {
        this.moviesToShow = [];
        for (const movie of this.movies) {
          for (const newValueElement of newValue) {
            if (!this.moviesToShow.includes(movie) && movie.genres.includes(newValueElement)) {
              this.moviesToShow.push(movie);
            }
          }
        }
      }
    },
    durationFilter: {
      handler(value){
        if (typeof value.minDuration === 'string') {
          if (this.durationFilter.maxDuration - Number(this.durationFilter.minDuration) <= this.minGap){
            this.durationFilter.minDuration = this.durationFilter.maxDuration - this.minGap;
          }
        } else if (typeof value.maxDuration === 'string') {
          if (Number(this.durationFilter.maxDuration) - this.durationFilter.minDuration <= this.minGap){
            this.durationFilter.maxDuration = this.durationFilter.minDuration + this.minGap;
          }
        }
        this.fillSlideColor();
        this.moviesToShow = this.movies.filter((m) => {
          return m.duration >= this.durationFilter.minDuration && m.duration <= this.durationFilter.maxDuration;
        })
        console.log(this.moviesToShow);
      },
      deep: true
    }
  }
}
</script>

<style scoped>
@import url("./assets/css/main.css");
</style>
