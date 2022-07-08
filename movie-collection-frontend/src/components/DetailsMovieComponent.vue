<template>
  <div>
    <div class="overlay">

    </div>
    <div class="detailsView" v-if="movie">
      <img class="detailsPosterImage"
           :src="movie.posterUrl"
           alt="poster-image">
      <div class="detailsViewInfo">
        <p class="closeButton" @click="closeDetails">X</p>
        <p class="detailsViewTitle1">{{ movie.title1 }}</p>
        <p class="detailsViewTitle2">{{ movie.title2 }}</p>
        <ul class="detailsViewGenres">
          <li v-for="(genre , index) in movie.genres" :key="index">{{ genre }}</li>
        </ul>
        <textarea v-if="movie.description !== null && movie.description.length > 0"
                  :value="movie.description" readonly :rows="rowsDecl" cols="52" class="detailsViewDescription">
      </textarea>
        <button class="detailsViewButtonTrailer" @click="watchTrailer()">
          <font-awesome-icon icon="fa-solid fa-circle-play"/>
          WATCH TRAILER
        </button>
        <p class="detailsViewIMDb"><span class="imdbRating">IMDb</span> {{
            movie.rating ? movie.rating + '/10' : 'N/A'
          }}</p>
        <ul class="yearDurationAudioInfo">
          <li class="year">Year: {{ movie.year }}</li>
          <li class="duration">Duration: {{ movie.duration }}min</li>
          <li>Audio: {{ movie.bulgarianLanguage ? 'Българско' : 'Английско' }}</li>
        </ul>
        <ul class="platforms">
          <li v-for="(platform , index) in movie.platforms" :key="index">{{ platform }}</li>

        </ul>
        <ul class="detailsViewButtons">
          <li class="editButton" @click="showEdit(movie.movieId)">
            <font-awesome-icon icon="fa-solid fa-pen-to-square"/>
            EDIT
          </li>
          <li class="deleteButton" @click="deleteMovie(movie.movieId)">
            <font-awesome-icon icon="fa-solid fa-trash-can"/>
            DELETE
          </li>
        </ul>
      </div>
      <modal name="detailsErrorModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
        <ErrorModal :errorMessage="this.errorMessage"/>
      </modal>
      <modal name="detailsSuccessfulModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
        <SuccessfulModal :successMessage="this.successMessage"/>
      </modal>
    </div>
  </div>
</template>

<script>
import {MovieService} from "@/services/movie-service";
import ErrorModal from "@/components/messages/ErrorModal";
import SuccessfulModal from "@/components/messages/SuccessfulModal";

export default {
  name: "DetailsView",
  components: {
    ErrorModal,
    SuccessfulModal
  },
  created() {
    this.movieService.findMovieDetail(this.movieId).then((movieDetailsDto) => {
      if (movieDetailsDto.status === 'OK') {
        this.movie = movieDetailsDto.data;
      } else {
        this.errorMessage = 'Error in clickDetails!';
        this.showErrorModal();
        setTimeout(() => {
          this.hideErrorModal()
        }, 4000)
      }
    })
  },
  data() {
    return {
      movieService: new MovieService(),
      movie: null,
      descriptionRows: 0,
      errorMessage: null,
      successMessage: null
    }
  },
  methods: {
    showSuccessModal() {
      this.$modal.show('detailsSuccessfulModal');
    },
    hideSuccessModal() {
      this.$modal.hide('detailsSuccessfulModal');
    },
    showErrorModal() {
      this.$modal.show('detailsErrorModal');
    },
    hideErrorModal() {
      this.$modal.hide('detailsErrorModal');
    },
    watchTrailer() {
      window.open(this.movie.trailerUrl);
    },
    showEdit(movieId) {
      this.$router.push({name: 'edit', params: {movieId: movieId}})
    },
    deleteMovie(movieId) {

      this.movieService.deleteMovie(movieId).then((resp) => {
        if (resp.status === 'OK') {

          this.$router.push({name: 'movies'})

        } else {

          this.errorMessage = 'Error in delete movie!';
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 4000)
        }
      })
    },
    editedMovie() {
      this.successMessage = 'Successful film editing!'
      this.showSuccessModal();
      setTimeout(() => {
        this.hideSuccessModal()
      }, 4000)
    },
    closeDetails() {
      this.$router.push({name: 'movies'})
    }
  },
  computed: {
    rowsDecl: function () {
      if (this.movie.description != null) {
        let length = this.movie.description.length;
        return Math.ceil(length / 50);
      }
      return 0;
    },
    movieId() {
      return this.$route.params.movieId;
    },
  }
}
</script>

<style scoped>

div.overlay {
  position: absolute;
  background-color: #010340;
  opacity: 0.5;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 30;
}

.detailsView {
  display: grid;
  grid-template-columns: 23rem 1fr;
  grid-template-areas:
    "img div";
  width: 55rem;
  height: 35rem;
  position: absolute;
  background-color: #010229;
  z-index: 40;

  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.detailsView img.detailsPosterImage {
  width: 100%;
  height: 100%;
}

.detailsViewInfo {
  color: orange;
}

.detailsViewInfo p.detailsViewTitle1 {
  margin: 1rem 2.3rem;
  font-size: 2.5rem;
  font-weight: bold;
  text-transform: uppercase;
  text-align: center;
}

.detailsViewInfo p.detailsViewTitle2 {
  margin: 1rem;
  font-size: 1.7rem;
  text-transform: uppercase;
  text-align: center;
}

.detailsViewInfo ul.detailsViewGenres,
.detailsViewInfo ul.yearDurationAudioInfo,
.detailsViewInfo ul.platforms,
.detailsViewInfo ul.detailsViewButtons {
  display: flex;
  justify-content: space-around;
  margin: 0 1rem;
  flex-wrap: wrap;
}

.detailsViewInfo ul.detailsViewGenres li {
  display: inline-block;
  font-size: 0.8rem;
  margin: 0.5rem 0.5rem;
  color: #010340;
  background-color: orange;
  padding: 0.2rem 0.7rem;
  border-radius: 7px;
  text-align: center;
  text-transform: uppercase;
  font-weight: bold;
}

.detailsViewInfo textarea.detailsViewDescription {
  height: auto;
  color: orange;
  /*width: 85%;*/
  margin: 1rem 2rem;
  border: 1px solid orange;
  padding: 0.5rem;
  border-radius: 5px;
  /*box-sizing: border-box;*/
  /*font-size: inherit;*/
  /*outline: none;*/
  background-color: #010229;
  resize: none;
}

.detailsViewInfo button.detailsViewButtonTrailer {
  margin: 1rem 2.5rem;
  font-size: 1rem;
  text-transform: uppercase;
  color: orange;
  border: 1px solid orange;
  background: rgba(32, 32, 32, 0.9);
  border-radius: 5px;
  display: inline-block;
  padding: 0.4rem;
}

.detailsViewInfo button.detailsViewButtonTrailer:hover {
  cursor: pointer;
  color: rgba(32, 32, 32, 0.9);
  background: orange;
}

.detailsViewInfo .detailsViewIMDb {
  margin: 0.5rem 3rem;
  display: inline-block;
  font-size: 1.5rem;
}

span.imdbRating {
  background-color: #ffd52a;
  background-image: linear-gradient(90deg, #ffd52a 30%, #ffffff 50%, #ffd52a 70%);

  color: black;
  font-weight: bold;
  padding: 2px 6px;
  border: 2px solid black;
  border-radius: 10px;
}

ul.detailsViewButtons li {
  display: inline-block;
  font-size: 1rem;
  margin: 1.3rem 1rem;
  padding: 0.6rem 1.5rem;
  border-radius: 10px;
  text-align: center;
  text-transform: uppercase;
  font-weight: bold;
}

ul.detailsViewButtons li.deleteButton {
  color: black;
  background-color: #FFE53B;
  background-image: linear-gradient(225deg, #FFE53B 0%, #FF2525 74%);

}

ul.detailsViewButtons li.editButton {
  color: black;
  background-color: #08AEEA;
  background-image: linear-gradient(43deg, #08AEEA 0%, #2AF598 100%);
}

ul.detailsViewButtons li.deleteButton:hover {
  cursor: pointer;
  color: black;
  background-color: #FFE53B;
  background-image: linear-gradient(45deg, #FFE53B 0%, #FF2525 74%);

}

ul.detailsViewButtons li.editButton:hover {
  cursor: pointer;
  background-color: #08AEEA;
  background-image: linear-gradient(243deg, #08AEEA 0%, #2AF598 100%);

}

.detailsViewInfo ul.yearDurationAudioInfo {
  margin: 0.5rem 0;
}

.detailsViewInfo ul.yearDurationAudioInfo li {
  display: inline-block;
  margin: 0.5rem 1rem;
}

.detailsViewInfo ul.platforms li {
  display: inline-block;
  font-size: 0.8rem;
  margin: 0.5rem 0.5rem;
  color: #010340;
  background-color: orange;
  padding: 0.2rem 0.7rem;
  border-radius: 7px;
  text-align: center;
  text-transform: uppercase;
  font-weight: bold;
}

.movieEditModal {
  background: rgb(252, 186, 3, 0.4);
}

input:focus, textarea:focus, select:focus {
  outline: none;
}

div.detailsView p.closeButton {
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

div.detailsView p.closeButton:hover {
  cursor: pointer;
  border: 1px solid white;
}
</style>