<template>
  <div class="movieContainer">
    <aside v-if="true" class="filter">
      <p class="filtersTitle">Filters</p>
      <span class="filterTitle">Genres:</span>
      <div ref="genres" v-for="(genre , i) in genres" :key="i">
        <input type="checkbox" :id="i" :name="genre" :value="genre">
        <label @click="addOrRemoveInGenresFilter(i)" :for="i">{{ genre }}</label>
      </div>
      <span class="filterTitle">Duration:</span>
      <div class="values">
        <span id="durationRange1">{{ this.durationFilter.minDuration }}</span>
        <span> &dash; </span>
        <span id="durationRange2">{{ this.durationFilter.maxDuration }}</span>
      </div>
      <div class="durationSliderTrack"></div>
      <input class="sliderRange" v-model="durationFilter.minDuration" id="durationSlider1" type="range" min="30"
             max="300"
             step="5">
      <input class="sliderRange" v-model="durationFilter.maxDuration" id="durationSlider2" type="range" min="30"
             max="300"
             step="5">
      <span class="ratingFilterTitle">IMDb rating:</span>
      <div class="values">
        <span id="ratingRange1">{{ this.ratingFilter.minRating }}</span>
        <span> &dash; </span>
        <span id="ratingRange2">{{ this.ratingFilter.maxRating }}</span>
      </div>
      <div class="ratingSliderTrack"></div>
      <input class="sliderRange" v-model="ratingFilter.minRating" id="ratingSlider1" type="range" min="0" max="10"
             step="0.5">
      <input class="sliderRange" v-model="ratingFilter.maxRating" id="ratingSlider2" type="range" min="0" max="10"
             step="0.5">
      <p id="clearButton" @click="clearFilters">Clear Filters</p>
    </aside>
    <section class="catalogue">
      <div class="catalogueHeader">
        <div>
          <label class="selectLabel" for="orderBy">Order by:</label>
          <select id="orderBy" class="orderBy" v-model="pagination.orderBy">
            <option class="selectOption" value="title1">Title ASC</option>
            <option class="selectOption" value="title1,desc">Title DESC</option>
            <option class="selectOption" value="duration">Duration ASC</option>
            <option class="selectOption" value="duration,desc">Duration DESC</option>
            <option class="selectOption" value="rating">Rating ASC</option>
            <option class="selectOption" value="rating,desc">Rating DESC</option>
            <option class="selectOption" value="year">Year ASC</option>
            <option class="selectOption" value="year,desc">Year DESC</option>
          </select>
        </div>
        <p class="movies-title">Collection</p>
        <input class="searchInput" v-model.trim="filterParams.searchText" type="text" placeholder="Search...">
      </div>
      <div class="spinner" v-if="isLoading">
        <img src="../assets/image/Spinner.svg" alt="Spinner">
      </div>
      <ul class="movies">
        <MovieCard v-for="(movie , id) in moviesToShow"
                   :key="id"
                   :movie="movie"/>
      </ul>
      <p class="emptyCollection" v-if="isEmptyCollection">
        THANK YOU FOR YOUR REGISTRATION :) <br/><br/>
        NOW IT'S TIME TO ADD YOUR FIRST MOVIE FROM THE BUTTON <span @click="addMovie()">ADD MOVIE</span></p>
      <MyPagination v-if="this.moviesToShow.length > 0" :pagination="this.pagination"
                    @changePerPage="changePerPage"
                    @changeCurrentPage="changeCurrentPage"
      />
    </section>
    <transition :name="transitionName" mode="out-in">
      <router-view/>
    </transition>
    <modal name="errorModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <ErrorModal :errorMessage="this.errorMessage"/>
    </modal>
    <modal name="successfulModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <SuccessfulModal :success-message="this.successMessage"/>
    </modal>
    <modal name="warningModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <WarningModal :warningMessage="this.warningMessage"/>
    </modal>
  </div>
</template>

<script>
import {MovieService} from "@/services/movie-service";
import MyPagination from "@/components/MyPagination";
import MovieCard from "@/components/MovieCard";
import ErrorModal from "@/components/messages/ErrorModal";
import SuccessfulModal from "@/components/messages/SuccessfulModal";
import WarningModal from "@/components/messages/WarningModal";
import {Constants} from "@/constants/constants";

export default {
  name: "MoviesComponent",
  components: {
    MyPagination,
    MovieCard,
    ErrorModal,
    SuccessfulModal,
    WarningModal
  },
  mounted() {
    this.loadMovies()
    this.fillDurationSlideColor();
    this.fillRatingSlideColor();
  },
  data() {
    return {
      constants: Constants,
      movieService: new MovieService(),
      moviesToShow: [],
      durationFilter: {
        minDuration: 30,
        maxDuration: 300,
        minGap: 10,
      },
      ratingFilter: {
        minRating: 0,
        maxRating: 10,
        minGap: 1,
      },
      pagination: {
        empty: null,
        first: null,
        last: null,
        currentPage: 1,
        perPage: 10,
        orderBy: 'year,desc',
        totalElements: null,
        totalPages: null,
      },
      filterParams: {
        minDuration: 30,
        maxDuration: 300,
        minRating: 1,
        maxRating: 10,
        searchText: '',
        genres: [],
      },
      statistics: {},
      errorMessage: null,
      successMessage: null,
      warningMessage: null,
      spinner: {
        // ToDo
        isLoading: false
      },
      transitionName: ''
    }
  },
  methods: {
    showSuccessModal() {
      this.$modal.show(this.constants.MOVIES_SUCCESSFUL_MODAL);
    },
    hideSuccessModal() {
      this.$modal.hide(this.constants.MOVIES_SUCCESSFUL_MODAL);
    },
    showErrorModal() {
      this.$modal.show(this.constants.MOVIES_ERROR_MODAL);
    },
    hideErrorModal() {
      this.$modal.hide(this.constants.MOVIES_ERROR_MODAL);
    },
    showWarningModal() {
      this.$modal.show(this.constants.MOVIES_WARNING_MODAL);
    },
    hideWarningModal() {
      this.$modal.hide(this.constants.MOVIES_WARNING_MODAL);
    },
    fillDurationSlideColor() {
      let startPercent = (this.durationFilter.minDuration / 270) * 100 - (30 * 0.27);
      let overPercent = (this.durationFilter.maxDuration / 270) * 100 - (30 * 0.27);

      let sliderTrackElement = document.querySelector('.durationSliderTrack');
      sliderTrackElement.style.background = `linear-gradient(to right, white ${startPercent}% , orange ${startPercent}% , orange ${overPercent}% , white ${overPercent}%)`;
    },
    fillRatingSlideColor() {
      let startPercent = (this.ratingFilter.minRating / 10) * 100;
      let overPercent = (this.ratingFilter.maxRating / 10) * 100;

      let sliderTrackElement = document.querySelector('.ratingSliderTrack');
      sliderTrackElement.style.background = `linear-gradient(to right, white ${startPercent}% , orange ${startPercent}% , orange ${overPercent}% , white ${overPercent}%)`;
    },
    loadMovies() {

      this.movieService.findAll(this.pagination.currentPage - 1, this.pagination.perPage, this.pagination.orderBy, this.filterParams).then((resp) => {
        if (resp.status === 'OK') {
          if (resp.data === '') {

            this.moviesToShow = [];
            this.warningMessage = this.constants.EMPTY_COLLECTION;

          } else {
            this.warningMessage = null;
            this.mapRespPagToPag(resp.data);
            this.moviesToShow = resp.data.content;
            if (this.pagination.empty) {
              this.warningMessage = this.constants.EMPTY_COLLECTION_WITH_FILTERS;
              this.showWarningModal()

              setTimeout(() => {
                this.hideWarningModal()
              }, 4000)
            }
          }
        } else {
          console.log('error' , resp)
          if (resp.error === 'Request failed with status code 401') {
            //ToDo error message
            this.$store.dispatch('auth/logout');
            this.$router.push({name: 'home'})

          } else {
            // ToDo
            this.errorMessage = resp.error;
            this.showErrorModal();
            setTimeout(() => {
              this.hideErrorModal()
            }, 4000)
          }
        }
      });
    },
    addOrRemoveInGenresFilter(index) {
      let genre = this.genres[index];
      let elementIndex = this.filterParams.genres.indexOf(genre);

      if (elementIndex === -1) {
        this.filterParams.genres.push(genre)
      } else {
        this.filterParams.genres.splice(elementIndex, 1);
      }
    },
    deletedMovie(isDeleted) {

      if (isDeleted) {
        this.successMessage = this.constants.DELETE_MOVIE_SUCCESSFUL;
        this.showSuccessModal();
        setTimeout(() => {
          this.hideSuccessModal()
        }, 4000)


        this.loadMovies();

      } else {

        this.errorMessage = this.constants.ERROR.DELETE_MOVIE;
        this.showErrorModal();
        setTimeout(() => {
          this.hideErrorModal()
        }, 3000)

      }
    },
    successAddMovie() {
      this.successMessage = this.constants.ADDED_MOVIE_SUCCESSFUL;
      this.showSuccessModal();
      setTimeout(() => {
        this.hideSuccessModal()
      }, 4000)

      this.loadMovies();
    },
    mapRespPagToPag(data) {
      this.pagination.empty = data.empty;
      this.pagination.first = data.first;
      this.pagination.last = data.last;
      this.pagination.currentPage = data.number + 1;
      this.pagination.perPage = data.size;
      this.pagination.totalElements = data.totalElements;
      this.pagination.totalPages = data.totalPages;
    },
    changePerPage(value) {
      this.pagination.perPage = value;
      this.pagination.currentPage = 1;
      this.loadMovies();
    },
    changeCurrentPage(value) {
      this.pagination.currentPage = value;
      this.loadMovies();
    },
    clearFilters() {
      this.pagination.orderBy = 'year,desc';

      this.durationFilter.minDuration = 30;
      this.durationFilter.maxDuration = 300;
      this.ratingFilter.minRating = 0;
      this.ratingFilter.maxRating = 10;
      this.$refs['genres'].forEach(el => el.childNodes[0].checked = false);

      this.filterParams.minDuration = 30;
      this.filterParams.maxDuration = 300;
      this.filterParams.minRating = 1;
      this.filterParams.maxRating = 10;
      this.filterParams.searchText = '';
      this.filterParams.genres = [];
    },
    addMovie() {
      this.$router.push({name: 'addMovie'})
    }
  },
  watch: {
    durationFilter: {
      handler(value) {
        if (typeof value.minDuration === 'string' && this.durationFilter.maxDuration - Number(this.durationFilter.minDuration) <= this.durationFilter.minGap) {

          this.durationFilter.minDuration = this.durationFilter.maxDuration - this.durationFilter.minGap;

        } else if (typeof value.maxDuration === 'string' && Number(this.durationFilter.maxDuration) - this.durationFilter.minDuration <= this.durationFilter.minGap) {

          this.durationFilter.maxDuration = this.durationFilter.minDuration + this.durationFilter.minGap;

        }

        this.filterParams.minDuration = Number(this.durationFilter.minDuration);
        this.filterParams.maxDuration = Number(this.durationFilter.maxDuration);

        this.fillDurationSlideColor();
        this.pagination.currentPage = 1;
        this.loadMovies();
      },
      deep: true
    },
    ratingFilter: {
      handler(value) {
        if (typeof value.minRating === 'string' && this.ratingFilter.maxRating - Number(this.ratingFilter.minRating) <= this.ratingFilter.minGap) {

          this.ratingFilter.minRating = this.ratingFilter.maxRating - this.ratingFilter.minGap;

        } else if (typeof value.maxRating === 'string' && Number(this.ratingFilter.maxRating) - this.ratingFilter.minRating <= this.ratingFilter.minGap) {

          this.ratingFilter.maxRating = this.ratingFilter.minRating + this.ratingFilter.minGap;

        }

        this.filterParams.minRating = Number(this.ratingFilter.minRating);
        this.filterParams.maxRating = Number(this.ratingFilter.maxRating);

        this.fillRatingSlideColor();
        this.pagination.currentPage = 1;
        this.loadMovies();
      },
      deep: true
    },
    "filterParams.searchText": {
      handler() {
        this.pagination.currentPage = 1;
        this.loadMovies();
      }
    },
    "filterParams.genres": {
      handler() {
        this.pagination.currentPage = 1;
        this.loadMovies();
      }
    },
    "pagination.orderBy": {
      handler() {
        this.loadMovies();
      }
    },
    '$route'(to, from) {
      const toAddress = to.name;
      const fromAddress = from.name;
      console.log('to', toAddress)
      console.log('from', fromAddress)

      if (toAddress === 'movies' && (fromAddress === 'addMovie' || fromAddress === 'details' || fromAddress === 'edit')) {
        console.log('reload')
        this.loadMovies();
      }

      switch (toAddress) {
        case 'addMovie':
          this.transitionName = 'fade'
          break;
        case 'details':
          this.transitionName = 'bounce';
          break;
        case 'edit':
          this.transitionName = 'bounce';
          break;
        case 'statistics':
          this.transitionName = 'fade'
          break;
      }
    }
  },
  computed: {
    genres() {
      return this.$store.getters.getGenres;
    },
    isLoading() {
      //ToDo
      return this.spinner.isLoading;
    },
    isEmptyCollection() {
      return this.warningMessage === this.constants.EMPTY_COLLECTION;
    }
  }
}
</script>

<style scoped>
@import url("../assets/css/main.css");

div.spinner {
  margin-top: 20%;
  margin-left: 25%;
}

div.movieContainer {
  display: grid;
  grid-template-columns: 200px 1fr;
  grid-template-areas:
    "aside section";
  position: relative;
}

aside.filter {
  display: inline-block;
}

aside.filter p.filtersTitle {
  text-align: center;
  font-size: 1.5rem;
  background-color: orange;
  margin: 0.5rem;
  padding: 0.5rem;
  border-radius: 10px;
  letter-spacing: 1px;
}

#clearButton {
  display: inline-block;
  font-size: 1rem;
  margin: 3rem 1.7rem;
  padding: 0.5rem 0.5rem;
  border-radius: 10px;
  text-align: center;
  text-transform: uppercase;
  font-weight: bold;
  color: black;
  background-color: #FFE53B;
  background-image: linear-gradient(225deg, #FFE53B 0%, #FF2525 74%);
}

#clearButton:hover {
  cursor: pointer;
  background-color: #FFE53B;
  background-image: linear-gradient(45deg, #FFE53B 0%, #FF2525 74%);
}

/* Genres Filter */

aside.filter span.filterTitle,
aside.filter span.ratingFilterTitle {
  background-color: black;
  color: orange;
  border-radius: 5px;
  font-size: 1.35rem;
  display: flex;
  justify-content: center;
  padding: 3px 5px;
  margin: 20px 30px;
  letter-spacing: 1px;
}

aside.filter span.ratingFilterTitle {
  margin-top: 50px;
}

aside.filter label {
  color: #DFE0DF;
}

aside.filter input[type="checkbox"] {
  display: none;
}

aside.filter input[type="checkbox"] + *::before {
  content: "";
  display: inline-block;
  vertical-align: bottom;
  width: 1rem;
  height: 1rem;
  margin-right: 0.5rem;
  border-radius: 10%;
  border-style: solid;
  border-width: 0.1rem;
  border-color: #DFE0DF;
  flex-shrink: 0;
}

aside.filter input[type="checkbox"]:checked + * {
  color: orange;
  font-weight: bold;
}

aside.filter input[type="checkbox"]:checked + *::before {
  content: "✓";
  color: orange;
  text-align: center;
  border-color: orange;
}

aside.filter input[type="checkbox"] + * {
  display: inline-flex;
  padding: 0.3rem 1rem;
}

aside.filter input[type="checkbox"] + *:hover {
  cursor: pointer;
  color: white;
}

aside.filter input[type="checkbox"]:checked + *:hover {
  color: orange;
}

/* Duration + Rating Filter */

aside.filter div.durationSliderTrack,
aside.filter div.ratingSliderTrack {
  width: 180px;
  height: 5px;
  background-color: white;
  position: absolute;
  margin: 8px 10px;
  border-radius: 5px;
}

aside.filter input.sliderRange {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  width: 200px;
  outline: none;
  position: absolute;
  background-color: transparent;
  pointer-events: none;
}

aside.filter input[type="range"]::-webkit-slider-runnable-track {
  -webkit-appearance: none;
  height: 5px;
}

aside.filter input[type="range"]::-moz-range-track {
  -moz-appearance: none;
  height: 5px;
}

aside.filter input[type="range"]::-ms-track {
  appearance: none;
  height: 5px;
}

aside.filter input[type="range"]::-webkit-slider-thumb {
  -webkit-appearance: none;
  height: 0.9rem;
  width: 0.9rem;
  background-color: orange;
  cursor: pointer;
  border-radius: 10px;
  pointer-events: auto;
}

aside.filter input[type="range"]::-moz-range-thumb {
  -webkit-appearance: none;
  height: 0.9rem;
  width: 0.9rem;
  background-color: orange;
  cursor: pointer;
  border-radius: 50%;
  pointer-events: auto;
}

aside.filter input[type="range"]::-ms-thumb {
  appearance: none;
  height: 0.9rem;
  width: 0.9rem;
  background-color: orange;
  cursor: pointer;
  border-radius: 50%;
  pointer-events: auto;
}

aside.filter input[type="range"]:active::-webkit-slider-thumb {
  background-color: orange;
  border: 3px solid black;
}

aside.filter div.values {
  background-color: orange;
  width: 80px;
  position: relative;
  margin: 10px 63px;
  padding: 5px 0;
  text-align: center;
  color: black;
  font-weight: bold;
  font-size: 18px;
  border-radius: 3px;
}

aside.filter div.values:before {
  content: "";
  position: absolute;
  height: 0;
  width: 0;
  border-top: 8px solid orange;
  border-left: 8px solid transparent;
  border-right: 8px solid transparent;
  margin: auto;
  bottom: -8px;
  left: 0;
  right: 0;
}

/* Collection */

.movies {
  margin: 10px auto;
  width: 95%;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 2rem;
}

div.catalogueHeader {
  display: flex;
  justify-content: space-between;
  margin: 1rem 3rem;
}

div.catalogueHeader div {
  background-color: black;
  border-radius: 10px;
}

div.catalogueHeader label.selectLabel {
  color: orange;
  font-size: 1rem;
  padding: 0.2rem;
  margin: 0.7rem 0.1rem;
  text-transform: uppercase;
  font-weight: bold;
}

div.catalogueHeader select.orderBy {
  color: white;
  margin: 0.7rem 0;
  border: none;
  background-color: black;
  text-transform: uppercase;
}

div.catalogueHeader select.orderBy:hover {
  cursor: pointer;
}

.movies-title {
  font-size: 1.5rem;
  background-color: black;
  padding: 0.5rem 3rem;
  border-radius: 10px;
  display: inline-block;
  color: orange;
  letter-spacing: 1px;
}

input.searchInput {
  width: 200px;
  font-weight: bold;
  color: orange;
  height: 2.3rem;
  border: 1px solid black;
  border-radius: 10px;
  display: inline-block;
  padding: 0.5rem;
  background: black url("../assets/image/search-img.svg") no-repeat scroll 175px 6px;
}

p.emptyCollection {
  color: black;
  font-size: 3rem;
  letter-spacing: 1px;
  font-weight: bold;
  text-align: center;
  margin-top: 3rem;
}

p.emptyCollection span {
  cursor: pointer;
  color: white;
}

p.emptyCollection span:hover {
  color: #0D2082;
}

.fade-enter, .fade-leave-to {
  z-index: 10;
  opacity: 0;
}

.fade-enter-active, .fade-leave.active {
  z-index: 10;
  transition: all .5s ease-in-out;
}

.bounce-enter-active {
  z-index: 10;
  animation: bounce-in 0.5s;

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.bounce-leave-active {
  z-index: 10;
  animation: bounce-in 0.5s reverse;

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1.25);
  }
  100% {
    transform: scale(1);
  }
}
</style>