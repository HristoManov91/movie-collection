<template>
  <div id="app">
    <header>
      <nav>
        <ul>
          <li ref="#">HOME</li>
          <li ref="#">MOVIE</li>
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
        <div class="movie-card">
          <img
              src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
              class="movie-img"/>
          <ul class="text-movie-cont">
            <li>Interstellar</li>
            <li>Duration: 180 min.</li>
            <li>IMBD: 7.3</li>
            <li ref="#">WATCH TRAILER</li>
          </ul>
        </div>
        <div class="movie-card">
          <img
              src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
              class="movie-img"/>
          <ul class="text-movie-cont">
            <li>Interstellar</li>
            <li>Duration: 180 min.</li>
            <li>IMBD: 7.3</li>
            <li ref="#">WATCH TRAILER</li>
          </ul>
        </div>
        <div class="movie-card">
          <img
              src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
              class="movie-img"/>
          <ul class="text-movie-cont">
            <li>Interstellar</li>
            <li>Duration: 180 min.</li>
            <li>IMBD: 7.3</li>
            <li ref="#">WATCH TRAILER</li>
          </ul>
        </div>
        <div class="movie-card">
          <img
              src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
              class="movie-img"/>
          <div class="text-movie-cont">
            <p>Interstellar</p>
            <p>Duration: 180 min.</p>
            <p>IMBD: 7.3</p>
            <button ref="#">WATCH TRAILER</button>
          </div>
        </div>
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
