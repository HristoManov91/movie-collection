<template>
  <div class="detailsView">
    <img class="detailsPosterImage"
         :src="movie.posterUrl"
         alt="poster-image">
    <div class="detailsViewInfo">
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
      <p class="detailsViewIMDb">IMDb: 7.3</p>
      <ul class="yearDurationAudioInfo">
        <li class="year">Year: {{ movie.year }}</li>
        <li class="duration">Duration: {{ movie.duration }}min</li>
        <li>Audio: {{ movie.bulgarianLanguage ? 'Българско' : 'Английско' }}</li>
      </ul>
      <ul class="platforms">
        <li v-for="(platform , index) in movie.platforms" :key="index">{{ platform }}</li>

      </ul>
      <ul class="detailsViewButtons">
        <li class="editButton" @click="showEditModal()">
          <font-awesome-icon icon="fa-solid fa-pen-to-square"/>
          EDIT
        </li>
        <li class="deleteButton" @click="deleteMovie(movie.movieId)">
          <font-awesome-icon icon="fa-solid fa-trash-can"/>
          DELETE
        </li>
      </ul>
    </div>
    <modal class="movieEditModal" name="movieEditModal" :resizable="false" :reset="true" width="850px" height="auto"
           :scrollable="true">
      <EditMovie :movie="movie" @editMovie="editedMovie"/>
    </modal>
    <modal name="detailsErrorModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <ErrorModal :errorMessage="this.errorMessage"/>
    </modal>
    <modal name="detailsSuccessfulModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <SuccessfulModal :successMessage="this.successMessage"/>
    </modal>
  </div>
</template>

<script>
import EditMovie from "@/components/EditMovie";
import {MovieService} from "@/services/movie-service";
import ErrorModal from "@/components/modals/ErrorModal";
import SuccessfulModal from "@/components/modals/SuccessfulModal";

export default {
  name: "DetailsView",
  components: {
    EditMovie,
    ErrorModal,
    SuccessfulModal
  },
  props: {
    movie: {
      movieId: {
        type: Number,
        required: true
      },
      title1: String,
      title2: String,
      genres: {
        type: {},
      },
      duration: Number,
      year: Number,
      rating: Number,
      imdbUrl: String,
      trailerUrl: String,
      posterUrl: String,
      platforms: {
        type: {},
      },
      bulgarianLanguage: Boolean,
      description: String,
    }
  },
  data() {
    return {
      movieService: new MovieService(),
      descriptionRows: 0,
      errorMessage: null,
      successMessage: null
    }
  },
  methods: {
    showSuccessModal() {
      this.$modal.show('detailsSuccessfulModal');
    },
    hideSuccessModal(){
      this.$modal.hide('detailsSuccessfulModal');
    },
    showErrorModal() {
      this.$modal.show('detailsErrorModal');
    },
    hideErrorModal(){
      this.$modal.hide('detailsErrorModal');
    },
    showEditModal() {
      this.$modal.show('movieEditModal');
    },
    hideEditModal() {
      this.$modal.hide('movieEditModal');
    },
    watchTrailer() {
      window.open(this.movie.trailerUrl);
    },
    deleteMovie(movieId){

      this.movieService.deleteMovie(movieId).then((resp) => {
        if (resp.status === 'OK') {


          // this.successMessage = 'The movie was successfully deleted!';
          // this.showSuccessModal();
          // setTimeout(() => {this.hideSuccessModal()} , 4000 )
          this.$emit('deleteMovie' , true);

        } else {

          // this.errorMessage = 'We could not delete the movie!'
          // this.showErrorModal();
          // setTimeout(() => {this.hideErrorModal()} , 3000 )
          this.$emit('deleteMovie' , false);
        }
      })
    },
    editedMovie() {
      this.hideEditModal();
      this.successMessage = 'Successful film editing!'
      this.showSuccessModal();
      setTimeout(() => {this.hideSuccessModal()} , 4000 )
    },
  },
  computed: {
    rowsDecl: function() {
      if (this.movie.description != null) {
        let length = this.movie.description.length;
        return Math.ceil(length / 50);
      }
      return 0;
    },
  }
}
</script>

<style scoped>

.detailsView {
  display: grid;
  grid-template-columns: 20rem 1fr;
  grid-template-areas:
    "img div";
  width: 100%;
  height: 100%;
  position: absolute;
  background-color: #010229;
}

.detailsView img.detailsPosterImage {
  width: 100%;
  height: 100%;
  position: relative;
  z-index: 10;
}

.detailsViewInfo {
  color: orange;
}

.detailsViewInfo p.detailsViewTitle1 {
  margin: 1rem;
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
  margin: 1.5rem 2rem;
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
  margin: 0.5rem 3rem;
  font-size: 1rem;
  text-transform: uppercase;
  color: orange;
  border: 1px solid orange;
  background: rgba(32, 32, 32, 0.9);
  border-radius: 5px;
  display: inline-block;
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

ul.detailsViewButtons li {
  display: inline-block;
  font-size: 1rem;
  margin: 2.5rem 2rem;
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
  margin: 1rem 0;
}

.detailsViewInfo ul.yearDurationAudioInfo li {
  display: inline-block;
  margin: 0.5rem 1rem;
}

.detailsViewInfo ul.platforms li {
  display: inline-block;
  font-size: 0.8rem;
  margin: 0.5rem 2rem;
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

</style>