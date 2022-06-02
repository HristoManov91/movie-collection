<template>
  <div id="app">
    <header>
      <!--      <nav>-->
      <!--        <ul>-->
      <!--          <li ref="#">MOVIES</li>-->
      <!--          <li ref="#">SERIALS</li>-->
      <!--        </ul>-->
      <!--      </nav>-->
      <p>My favorite movies</p>
      <img src="./assets/image/welcome-image.jpg" alt="home-cinema-image">
    </header>
    <main class="container">
      <aside class="filter">
        <p>Filters:</p>
        <input type="checkbox" id="action" name="action" value="ACTION">
        <label for="action">ACTION</label><br>
        <input type="checkbox" id="comedy" name="comedy" value="COMEDY">
        <label for="comedy">COMEDY</label><br>
        <input type="checkbox" id="fantasy" name="fantasy" value="FANTASY">
        <label for="fantasy">FANTASY</label><br>
        <input type="checkbox" id="drama" name="drama" value="DRAMA">
        <label for="drama">DRAMA</label><br>
      </aside>
      <section class="catalogue">
        <p class="movies-title">Collection</p>
        <ul class="movies">
          <MovieCard v-for="(movie , id) in movies" :key="id" :movie="movie" @clickDetails="clickDetails"/>
          <!--          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">Avengers</h3>
              <h5 class="title2">Infinity War</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" @click="watchTrailer">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">Avengers</h3>
              <h5 class="title2">Infinity War</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" @click="watchTrailer">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">Avengers</h3>
              <h5 class="title2">Infinity War</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" @click="watchTrailer">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">Avengers</h3>
              <h5 class="title2">Infinity War</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" @click="watchTrailer">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">Avengers</h3>
              <h5 class="title2">Infinity War</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" @click="watchTrailer">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">The Lord of The Rings</h3>
              <h5 class="title2">The Return of The King</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">The Lord of The Rings</h3>
&lt;!&ndash;              <h5 class="title2">The Return of The King</h5>&ndash;&gt;
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">The Lord of The Rings</h3>
              <h5 class="title2">The Return of The King</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">The Lord of The Rings</h3>
              <h5 class="title2">The Return of The King</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">The Lord of The Rings</h3>
              <h5 class="title2">The Return of The King</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">The Lord of The Rings</h3>
              <h5 class="title2">The Return of The King</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">The Lord of The Rings</h3>
              <h5 class="title2">The Return of The King</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="title1">The Lord of The Rings</h3>
              <h5 class="title2">The Return of The King</h5>
              <p class="duration">
                <font-awesome-icon icon="fa-clock"/>
                180 min.
              </p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                TRAILER
              </button>
              <button class="button-details" @click="show()">
                DETAILS
              </button>
            </div>
          </li>-->
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

import DetailsView from "@/components/DetailsView";
import {MovieService} from "@/services/movie-service";
import MovieCard from "@/components/MovieCard";

export default {
  name: 'App',
  components: {
    DetailsView,
    MovieCard
  },
  mounted() {
    console.log('mounted')
    this.loadMovies();
  },
  data() {
    return {
      movieService: new MovieService(),
      movies: {},
      movie: {
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
    }
  },
  methods: {
    show () {
      this.$modal.show('movieDetailsModal');
    },
    hide () {
      this.$modal.hide('movieDetailsModal');
    },
    watchTrailer () {
      window.open("https://www.youtube.com/watch?v=pLvovWcmJ-k");
    },
    loadMovies: function () {
      this.movieService.findAllMovies().then((moviePreviewDto) => {
        if (moviePreviewDto.status === 'OK'){
          this.movies = moviePreviewDto.data;
          console.log('movies' , this.movies);
        } else {
          alert('Error')
        }
      });
      // .finally() ToDo

    },
    clickDetails: function (movieId) {
      this.movieService.findMovieDetail(movieId).then((movieDetailsDto) => {
        if (movieDetailsDto.status === 'OK') {
          this.movie = movieDetailsDto.data;
          this.show();
        } else {
          alert('Error')
        }
      })
    }
  },
}
</script>

<style scoped>
@import url("./assets/css/main.css");
</style>
