<template>
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
      <button class="button-trailer" ref="#">
        <font-awesome-icon icon="fa-solid fa-circle-play"/>
        TRAILER
      </button>
      <button class="button-details" @click="showModal = true">
        DETAILS
      </button>
    </div>
  </li>
</template>

<script>
export default {
  name: "MovieCard"
}
</script>

<style scoped>

</style>