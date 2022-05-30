<template>
  <div class="container">
    <p class="pageTitle">Edit movie</p>
    <form class="editForm" action="">
      <div class="row">
        <div class="col">
          <label for="title1">Title:</label>
          <input id="title1" v-model.trim="movie.title1" type="text"/>
        </div>
        <div class="col">
          <label for="title2">Second Title:</label>
          <input id="title2" v-model.trim="movie.title2" type="text"/>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <label for="duration">Duration:</label>
          <input id="duration" v-model="movie.duration" type="number"/>
        </div>
        <div class="col">
          <label for="year">Year:</label>
          <input id="year" v-model="movie.year" type="number"/>
        </div>
      </div>
      <div class="genresEdit">
        <input type="checkbox" id="actionEdit" name="actionEdit" value="ACTION" v-model="movie.genres"/>
        <label for="actionEdit">Action</label>
        <input type="checkbox" id="fantasyEdit" name="fantasyEdit" value="FANTASY" v-model="movie.genres"/>
        <label for="fantasyEdit">Fantasy</label>
        <input type="checkbox" id="comedyEdit" name="comedyEdit" value="COMEDY" v-model="movie.genres"/>
        <label for="comedyEdit">Comedy</label>
        <input type="checkbox" id="dramaEdit" name="dramaEdit" value="DRAMA" v-model="movie.genres"/>
        <label for="dramaEdit">Drama</label>
        <input type="checkbox" id="animationEdit" name="animationEdit" value="ANIMATION" v-model="movie.genres"/>
        <label for="animationEdit">Animation</label>
        <input type="checkbox" id="adventureEdit" name="adventureEdit" value="ADVENTURE" v-model="movie.genres"/>
        <label for="adventureEdit">Adventure</label>
        <input type="checkbox" id="romanceEdit" name="romanceEdit" value="ROMANCE" v-model="movie.genres"/>
        <label for="romanceEdit">Romance</label>
        <input type="checkbox" id="thrillerEdit" name="thrillerEdit" value="THRILLER" v-model="movie.genres"/>
        <label for="thrillerEdit">Thriller</label>
        <input type="checkbox" id="crimeEdit" name="crimeEdit" value="CRIME" v-model="movie.genres"/>
        <label for="crimeEdit">Crime</label>
        <input type="checkbox" id="documentaryEdit" name="documentaryEdit" value="DOCUMENTARY" v-model="movie.genres"/>
        <label for="documentaryEdit">Documentary</label>
        <input type="checkbox" id="serialEdit" name="serialEdit" value="SERIAL" v-model="movie.genres"/>
        <label for="serialEdit">Serial</label>
      </div>
      <div class="urlsEdit">
        <label for="imdbUrl">IMDb URL:</label>
        <input id="imdbUrl" type="text" v-model.trim="movie.imbdUrl"/>
        <label for="trailerUrl">Trailer URL:</label>
        <input id="trailerUrl" type="text" v-model.trim="movie.trailerUrl"/>
        <label for="posterUrl">Poster URL:</label>
        <input id="posterUrl" type="text" v-model.trim="movie.imbdUrl"/>
      </div>
      <div class="platformsEdit">
          <input type="checkbox" id="hboxmax" name="hboxmax" value="HBOMAX" v-model="movie.genres"/>
          <label for="hboxmax">HBOMAX</label>
          <input type="checkbox" id="disneyEdit" name="disneyEdit" value="DISNEY+" v-model="movie.genres"/>
          <label for="disneyEdit">DISNEY+</label>
          <input type="checkbox" id="netflixEdit" name="netflixEdit" value="NETFLIX" v-model="movie.genres"/>
          <label for="netflixEdit">NETFLIX</label>
          <input type="checkbox" id="mypcEdit" name="mypcEdit" value="MY-PC" v-model="movie.genres"/>
          <label for="mypcEdit">MY-PC</label>
      </div>
      <span>Description:</span>
      <textarea class="editMovieTextarea" v-model.trim="movie.description"></textarea>
    </form>
  </div>
</template>

<script>
export default {
  name: "EditMovie",
  data() {
    return {
      movie: {
        title1: null,
        title2: null,
        duration: null,
        year: null,
        genres: [],
        imbdUrl: null,
        trailerUrl: null,
        posterUrl: null,
        description: null,
      },
      genres: {}
    }
  }
}
</script>

<style scoped>

div.container {
  background-color: #010340;
  height: 100%;
  width: 100%;
}

div p.pageTitle {
  text-align: center;
  color: orange;
  font-size: 1.5rem;
  text-transform: uppercase;
  font-weight: bold;
  padding: 1rem;
}

form.editForm {
  margin: 0 2.0rem;
}

form.editForm div.row div.col {
  display: inline-block;
  width: 45%;
  margin: 1% 2.5%;
}

form.editForm div.row div.col label {
  color: orange;
  display: inline-block;
  margin: 0.5rem 0;
  text-transform: uppercase;
  width: 100%;
  text-align: center;
}

form.editForm div.row div.col input {
  border-radius: 6px;
  font-size: 1.2rem;
  width: 100%;
}

/* Genres */
form.editForm div.genresEdit,
form.editForm div.platformsEdit {
  display: flex;
  justify-content: space-around;
  padding: 0.4rem 1rem;
  flex-wrap: wrap;
}

form.editForm div.genresEdit label,
form.editForm div.platformsEdit label {
  color: #b6b8b6;
  padding: 1rem;
  margin: 0.3rem;
  text-transform: uppercase;
}

form.editForm div.genresEdit input[type="checkbox"],
form.editForm div.platformsEdit input[type="checkbox"] {
  display: none;
}

form.editForm div.genresEdit input[type="checkbox"] + *::before,
form.editForm div.platformsEdit input[type="checkbox"] + *::before {
  content: "";
  display: inline-block;
  vertical-align: bottom;
  width: 1rem;
  height: 1rem;
  margin-right: 0.3rem;
  border-radius: 10%;
  border-style: solid;
  border-width: 0.1rem;
  border-color: #b6b8b6;
  flex-shrink: 0;
}

form.editForm div.genresEdit input[type="checkbox"]:checked + *,
form.editForm div.platformsEdit input[type="checkbox"]:checked + * {
  color: orange;
}

form.editForm div.genresEdit input[type="checkbox"]:checked + *::before,
form.editForm div.platformsEdit input[type="checkbox"]:checked + *::before {
  content: "✓";
  color: orange;
  text-align: center;
  border-color: orange;
}

form.editForm div.genresEdit input[type="checkbox"] + *,
form.editForm div.platformsEdit input[type="checkbox"] + * {
  display: inline-flex;
  padding: 0.4rem 0;
}

/* URLs */

form.editForm div.urlsEdit {
  padding: 0.4rem 1rem;
}

form.editForm div.urlsEdit label {
  color: orange;
  display: inline-block;
  margin: 0.5rem 0;
  text-transform: uppercase;
  width: 100%;
  /*text-align: center;*/

}

form.editForm div.urlsEdit input {
  border-radius: 6px;
  font-size: 1.2rem;
  width: 100%;
}

form.editForm span {
  color: orange;
  display: inline-block;
  margin: 0.5rem 2.5%;
  text-transform: uppercase;
  width: 100%;
}

form.editForm .editMovieTextarea {
  margin: 0 1rem 1rem 1rem;
  border-radius: 6px;
  font-size: 1.2rem;
  width: 96%;
  height: 100px;
}

</style>