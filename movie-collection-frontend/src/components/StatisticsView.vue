<template>
  <div class="statsContainer">
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
</template>

<script>

export default {
  name: "StatisticsView",
  props: {
    statistics: {
      totalMovies: Number,
      totalDurations: Number,
      actionMovies: Number,
      comedyMovies: Number,
      dramaMovies: Number,
      mysteryMovies: Number,
      sciFiMovies: Number,
      animationMovies: Number,
      adventureMovies: Number,
      fantasyMovies: Number,
      romanceMovies: Number,
      thrillerMovies: Number,
      crimeMovies: Number,
      documentaryMovies: Number,
      serialMovies: Number
    }
  }
}
</script>

<style scoped>

div.statsContainer {
  background-color: #0D2082;
  height: 100%;
  width: 100%;
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
</style>