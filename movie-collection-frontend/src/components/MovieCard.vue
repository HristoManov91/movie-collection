<template>
  <li class="movie-card">
    <img
        :src="movie.posterUrl"
        class="movie-poster"/>
    <div class="movie-info">
      <h3 class="title1">{{movie.title1}}</h3>
      <h5 v-if="movie.title2 != null" class="title2">{{movie.title2}}</h5>
      <p class="duration">
        <font-awesome-icon icon="fa-clock"/>
        {{ movie.duration }} min.
      </p>
      <p class="rating"><span class="imdbRating">IMDb</span> {{ movie.rating ? movie.rating : 'N/A'}}/10</p>
      <button class="button-trailer" @click="watchTrailer">
        <font-awesome-icon icon="fa-solid fa-circle-play"/>
        TRAILER
      </button>
      <button class="button-details" @click="clickDetails(movie.id)">
        DETAILS
      </button>
    </div>
  </li>
</template>

<script>
export default {
  name: "MovieCard",
  props: {
    movie: {
      id: {
        type: Number,
        required: true
      },
      posterUrl: {
        type: String,
        required: true
      },
      title1: {
        type: String,
        required: true
      },
      title2: String,
      duration: {
        type: Number,
        required: true
      },
      rating: {
        type: Number,
        required: true
      },
      trailerUrl: {
        type: String,
        required: true
      }
    }
  },
  methods: {
    watchTrailer () {
      window.open(this.movie.trailerUrl);
    },
    clickDetails(movieId) {
      this.$emit('clickDetails' , movieId);
    }
  }
}
</script>

<style scoped>

span.imdbRating {
  background-color: #ffd52a;
  background-image: linear-gradient(90deg, #ffd52a 30%, #ffffff 50%, #ffd52a 70%);
  color: black;
  font-weight: bold;
  padding: 2px;
  border: 2px solid black;
  border-radius: 10px;
}

</style>