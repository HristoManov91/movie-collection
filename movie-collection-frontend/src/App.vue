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
<!--              <h5 class="title2">The Return of The King</h5>-->
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
          </li>
        </ul>
      </section>
    </main>
    <modal class="modalElement"
           name="movieDetailsModal"
           :resizable="false"
           :reset="true"
           width="800px"
           height="580px">
      <DetailsView @showEdit="hide()"/>
    </modal>

  </div>
</template>
<script>

import DetailsView from "@/components/DetailsView";

export default {
  name: 'App',
  components: {
    DetailsView
  },
  data() {
    return {

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
    }
  },
}
</script>

<style scoped>
@import url("./assets/css/main.css");
</style>
