<template>
  <div id="app">
    <header>
      <nav>
        <ul>
          <li ref="#">HOME</li>
          <li ref="#">MOVIES</li>
          <li ref="#">SERIALS</li>
        </ul>
      </nav>
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
        <h2>Movies</h2>
        <ul class="movies">
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="name">Interstellar</h3>
              <p class="duration">Duration: 180 min.</p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                WATCH TRAILER
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="name">The Lord of The Rings: The return of the King</h3>
                <p class="duration">
                  <font-awesome-icon icon="fa-clock"/>
                  180 min.
                </p>
                <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                WATCH TRAILER
              </button>
            </div>
          </li>
          <li class="movie-card">
            <img
                src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
                class="movie-poster"/>
            <div class="movie-info">
              <h3 class="name">Interstellar</h3>
              <p class="duration">Duration: 180 min.</p>
              <p class="rating">IMDb: 7.3</p>
              <button class="button-trailer" ref="#">
                <font-awesome-icon icon="fa-solid fa-circle-play"/>
                WATCH TRAILER
              </button>
            </div>
          </li>
        </ul>
      </section>
    </main>
  </div>
</template>

<script>

export default {
  name: 'App',

}
</script>

<style scoped>
@import url("./assets/css/main.css");
</style>
