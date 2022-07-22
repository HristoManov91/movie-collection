<template>
  <div>
    <div class="overlay">

    </div>
    <div class="statsContainer" v-if="this.statistics">
      <p class="closeButton" @click="closeStatistics">X</p>
      <p class="title">Statistics</p>
      <p>Total movies: <span>{{ statistics.totalMovies }} </span> movies.</p>
      <p>Total duration: <span>{{ statistics.totalDurations }}</span> min.</p>
      <p class="title">By Genres</p>
      <p>Action: <span>{{ statistics.actionMovies }}</span> movies.</p>
      <p>Comedy: <span>{{ statistics.comedyMovies }}</span> movies.</p>
      <p>Drama: <span>{{ statistics.dramaMovies }}</span> movies.</p>
      <p>Mystery: <span>{{ statistics.mysteryMovies }}</span> movies.</p>
      <p>Sci-Fi: <span>{{ statistics.sciFiMovies }}</span> movies.</p>
      <p>Animation: <span>{{ statistics.animationMovies }}</span> movies.</p>
      <p>Adventure: <span>{{ statistics.adventureMovies }}</span> movies.</p>
      <p>Fantasy: <span>{{ statistics.fantasyMovies }}</span> movies.</p>
      <p>Romance: <span>{{ statistics.romanceMovies }}</span> movies.</p>
      <p>Thriller: <span>{{ statistics.thrillerMovies }}</span> movies.</p>
      <p>Crime: <span>{{ statistics.crimeMovies }}</span> movies.</p>
      <p>Documentary: <span>{{ statistics.documentaryMovies }}</span> movies.</p>
      <p>Serial: <span>{{ statistics.serialMovies }}</span> movies.</p>
    </div>
  </div>
</template>

<script>

import {MovieService} from "@/services/movie-service";

export default {
  name: "StatisticsView",
  created() {

    this.moviesService.getStatistics().then((resp) => {

      if (resp.status === 'OK') {
        this.statistics = resp.data;

      } else {
        // ToDo fix error
        this.errorMessage = 'We have problem with server,please try again later!'
        this.showErrorModal();
        setTimeout(() => {
          this.hideErrorModal()
        }, 4000)

      }
    })
  },
  data() {
    return {
      statistics: null,
      moviesService: new MovieService()
    }
  },
  methods: {
    closeStatistics() {
      this.$router.push({name: 'movies'})
    }
  }
}
</script>

<style scoped>

div.overlay {
  position: fixed;
  background-color: #010340;
  opacity: 0.5;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 30;
}

div.statsContainer {
  background-color: #0D2082;
  height: 43rem;
  width: 35rem;
  position: fixed;
  z-index: 40;

  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

div.statsContainer p {
  text-align: center;
  font-size: 1.3rem;
  font-weight: bold;
  color: orange;
  padding: 0.5rem 0;
  letter-spacing: 1px;
}

div.statsContainer p.title {
  color: white;
  font-size: 2rem;
  letter-spacing: 2px;
  text-decoration: underline;
}

div.statsContainer span {
  color: #FFDF65;
}

div.statsContainer p.closeButton {
  position: absolute;
  color: white;
  background-color: red;
  padding: 0.3rem 0.7rem;
  font-size: 2rem;
  right: 0;
  top: 0;
  text-align: center;
  font-family: "Helvetica", serif;
  font-weight: bold;
}

div.statsContainer p.closeButton:hover {
  cursor: pointer;
  border: 1px solid white;
}
</style>