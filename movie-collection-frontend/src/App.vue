<template>
  <div id="app">
    <header>
      <p>My favorite movies</p>
      <img src="./assets/image/welcome-image.jpg" alt="home-cinema-image">
    </header>
    <main class="container">
      <aside class="filter">
        <p>Filters:</p>
        <div v-for="(genre , i) in genres" :key="i">
          <input type="checkbox" :id="i" :name="genre" :value="genre">
          <label @click="addOrRemoveInFilters(i)" :for="i">{{ genre }}</label><br>
        </div>
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
      filters: [],
    }
  },
  methods: {
    show() {
      this.$modal.show('movieDetailsModal');
    },
    hide() {
      this.$modal.hide('movieDetailsModal');
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
    addOrRemoveInFilters(index) {
      let genre = this.genres[index];
      let elementIndex = this.filters.indexOf(genre);

      if (elementIndex === -1) {
        this.filters.push(genre)
      } else {
        this.filters.splice(elementIndex, 1);
      }
    },
  },
  watch: {
    filters: function (newValue) {
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
  }
}
</script>

<style scoped>
@import url("./assets/css/main.css");
</style>
