<template>
  <li class="movie-card">
    <img
        :src="movie.posterUrl"
        class="movie-poster"/>
    <div class="movie-info">
      <h3 class="title1">{{ movie.title1 }}</h3>
      <h5 v-if="movie.title2 != null" class="title2">{{ movie.title2 }}</h5>
      <p class="duration">
        <font-awesome-icon icon="fa-clock"/>
        {{ movie.duration }} min.
      </p>
      <p class="rating"><span class="imdbRating">IMDb</span> {{ movie.rating ? movie.rating + '/10' : 'N/A' }}</p>
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
    watchTrailer() {
      window.open(this.movie.trailerUrl);
    },
    clickDetails(movieId) {
      this.$router.push({name: 'details' , params: { movieId: movieId}})
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

li.movie-card {
  position: relative;
  overflow: hidden;
  border-radius: 10px;
  height: 21rem;
  width: 14rem;
}

li.movie-card:hover {
  box-shadow: rgba(0, 0, 0, 0.56) 0px 22px 70px 4px;
  cursor: grab;
}

li.movie-card::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #0d1015;
  opacity: 0.1;
}

.movie-poster {
  width: 100%;
  height: 100%;
}

.movie-info {
  width: 100%;
  position: absolute;
  bottom: -68px;
  left: 0;
  z-index: 10;
  color: orange;
  padding: 0.5rem 0.8rem;
  transition: bottom 300ms ease-in-out;
  background: rgba(32, 32, 32, 0.9);
}

.movie-info:hover {
  bottom: 0;
}

.movie-info h3.title1{
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
  margin-bottom: 0.3em;
  font-size: 1.3rem;
}
.movie-info h5.title2{
  text-transform: uppercase;
  margin-bottom: 0.3em;
  font-size: 0.9rem;
}

.movie-info .duration,
.movie-info .rating {
  font-size: 1rem;
  display: inline-block;
  margin: 0.5rem 0.3rem;
}

.button-trailer ,
.button-details {
  margin: 0.3rem 0.5rem;
  text-transform: uppercase;
  color: orange;
  border:1px solid orange;
  background: rgba(32, 32, 32, 0.9);
  border-radius: 4px;
  display: inline-block;
}

.button-trailer:hover ,
.button-details:hover {
  cursor: pointer;
  color: black;
  border: none;
  background-color: #FAD961;
  background-image: linear-gradient(62deg, #FAD961 0%, #F76B1C 100%);
}
</style>