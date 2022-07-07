<template>
  <div id="app">
    <header>
      <NavigationComponent />
      <img src="./assets/image/welcome-image.jpg" alt="home-cinema-image">
    </header>
    <main class="container">
      <router-view />
      <!--      <aside v-if="true" class="filter">
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
      <section v-if="true" class="catalogue">
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
        <ul class="movies">
          <MovieCard v-for="(movie , id) in moviesToShow"
                     :key="id"
                     :movie="movie"
                     @clickDetails="clickDetails"/>
        </ul>
        <MyPagination :pagination="this.pagination"
                      @changePerPage="changePerPage"
                      @changeCurrentPage="changeCurrentPage"
        />
      </section>-->
    </main>
    <!--    <modal class="modalElement"
           name="movieDetailsModal"
           :resizable="false"
           :reset="true"
           :clickToClose="false"
           width="860px"
           height="550px">
      <DetailsView :movie="this.movie" @deleteMovie="deletedMovie" @closeDetails="hideDetailsModal"/>
    </modal>-->
    <!--    <modal name="errorModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <ErrorModal :errorMessage="this.errorMessage"/>
    </modal>-->
    <!--    <modal name="successfulModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <SuccessfulModal :success-message="this.successMessage"/>
    </modal>
    <modal name="warningModal" :shiftX="1" :shiftY="0" :height="0" :width="0">
      <WarningModal/>
    </modal>
    <modal class="addMovieModal"
           name="addMovieModal"
           :resizable="false"
           :reset="true"
           :clickToClose="false"
           width="850px"
           height="auto"
           :scrollable="true">
      <AddMovie @addMovie="successAddMovie" @closeAddMovie="hideAddMovieModal"/>
    </modal>
    <modal name="statisticsModal"
           class="statisticsModal"
           :resizable="false"
           :reset="true"
           :clickToClose="false"
           width="600px"
           height="auto"
           :scrollable="true">
      <StatisticsView :statistics="this.statistics" @closeStatistics="hideStatisticsModal"/>
    </modal>
    <modal class="modalElement"
           name="loginForm"
           :resizable="false"
           :reset="true"
           :clickToClose="false"
           width="500px"
           height="450px">
      <LoginComponent @closeLoginForm="closeLoginForm"/>
    </modal>
    <modal class="modalElement"
           name="registerForm"
           :resizable="false"
           :reset="true"
           :clickToClose="false"
           width="500px"
           height="550px">
      <RegisterComponent @closeRegisterForm="closeRegisterForm"/>
    </modal>-->
  </div>
</template>
<script>

// import {MovieService} from "@/services/movie-service";
// import {GenreService} from "@/services/genre-service";
// import DetailsView from "@/components/DetailsMovieComponent";
// import MovieCard from "@/components/MovieCard";
// import ErrorModal from "@/components/messages/ErrorModal";
// import SuccessfulModal from "@/components/messages/SuccessfulModal";
// import WarningModal from "@/components/messages/WarningModal";
// import MyPagination from "@/components/MyPagination";
import NavigationComponent from "@/components/NavigationComponent";



export default {
  name: 'App',
  components: {
    NavigationComponent
    // DetailsView,
    // MovieCard,
    // ErrorModal,
    // SuccessfulModal,
    // WarningModal,
    // MyPagination,
  },
  mounted() {
    // this.loadMovies()
    // this.loadGenres();
    // this.fillDurationSlideColor();
    // this.fillRatingSlideColor();
    this.$store.dispatch('getGenres')
    this.$store.dispatch('getPlatforms')
  },
  data() {
    return {
      // movieService: new MovieService(),
      // genreService: new GenreService(),
      // moviesToShow: [],
      // movie: {
      //   movieId: {
      //     required: true,
      //     type: Number
      //   },
      //   title1: String,
      //   title2: String,
      //   genres: {
      //     type: {},
      //   },
      //   duration: Number,
      //   year: Number,
      //   rating: Number,
      //   imdbUrl: String,
      //   trailerUrl: String,
      //   posterUrl: String,
      //   platforms: {
      //     type: {},
      //   },
      //   bulgarianLanguage: Boolean,
      //   description: String,
      // },
      // // genres: [],
      // durationFilter: {
      //   minDuration: 30,
      //   maxDuration: 300,
      //   minGap: 10,
      // },
      // ratingFilter: {
      //   minRating: 0,
      //   maxRating: 10,
      //   minGap: 1,
      // },
      // pagination: {
      //   empty: null,
      //   first: null,
      //   last: null,
      //   currentPage: 1,
      //   perPage: 10,
      //   orderBy: 'year,desc',
      //   totalElements: null,
      //   totalPages: null,
      // },
      // filterParams: {
      //   minDuration: 30,
      //   maxDuration: 300,
      //   minRating: 1,
      //   maxRating: 10,
      //   searchText: '',
      //   genres: [],
      // },
      // statistics: {},
      // errorMessage: null,
      // successMessage: null,
    }
  },
  methods: {
  /*  showAddMovieModal() {
      this.$modal.show('addMovieModal');
    },*/
   /* hideAddMovieModal() {
      console.log('hide')
      this.$modal.hide('addMovieModal');
    },*/
   /* showDetailsModal() {
      this.$modal.show('movieDetailsModal');
    },*/
    /*hideDetailsModal() {
      this.$modal.hide('movieDetailsModal');
    },*/
   /* showSuccessModal() {
      this.$modal.show('successfulModal');
    },*/
   /* hideSuccessModal() {
      this.$modal.hide('successfulModal');
    },*/
    /*showErrorModal() {
      this.$modal.show('errorModal');
    },*/
    /*hideErrorModal() {
      this.$modal.hide('errorModal');
    },*/
    /*showWarningModal() {
      this.$modal.show('warningModal');
    },*/
   /* hideWarningModal() {
      this.$modal.hide('warningModal');
    },*/
   /* showStatisticsModal() {
      this.$modal.show('statisticsModal')
    },*/
    /*hideStatisticsModal() {
      this.$modal.hide('statisticsModal');
    },*/
    /*showLoginForm() {
      this.$modal.show('loginForm')
    },*/
/*    closeLoginForm() {
      this.$modal.hide('loginForm');
    },*/
  /*  showRegisterForm() {
      this.$modal.show('registerForm')
    },*/
    /*closeRegisterForm() {
      this.$modal.hide('registerForm');
    },*/
/*    fillDurationSlideColor() {
      let startPercent = (this.durationFilter.minDuration / 270) * 100 - (30 * 0.27);
      let overPercent = (this.durationFilter.maxDuration / 270) * 100 - (30 * 0.27);

      let sliderTrackElement = document.querySelector('.durationSliderTrack');
      sliderTrackElement.style.background = `linear-gradient(to right, white ${startPercent}% , orange ${startPercent}% , orange ${overPercent}% , white ${overPercent}%)`;
    },*/
/*    fillRatingSlideColor() {
      let startPercent = (this.ratingFilter.minRating / 10) * 100;
      let overPercent = (this.ratingFilter.maxRating / 10) * 100;

      let sliderTrackElement = document.querySelector('.ratingSliderTrack');
      sliderTrackElement.style.background = `linear-gradient(to right, white ${startPercent}% , orange ${startPercent}% , orange ${overPercent}% , white ${overPercent}%)`;
    },*/
/*    loadMovies() {

      this.movieService.findAllMoviesWithParams(this.pagination.currentPage - 1, this.pagination.perPage, this.pagination.orderBy, this.filterParams).then((resp) => {
        if (resp.status === 'OK') {
          this.mapRespPagToPag(resp.data);
          this.moviesToShow = resp.data.content;
          if (this.pagination.empty) {
            this.showWarningModal()

            setTimeout(() => {
              this.hideWarningModal()
            }, 4000)
          }
        } else {
          this.errorMessage = 'Error in loadMovies!';
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 4000)
        }
      });
      // .finally() ToDo
    },*/
/*    loadGenres() {
      this.genreService.findAllGenres().then((resp) => {
        if (resp.status === 'OK') {
          this.genres = resp.data;
        } else {
          this.errorMessage = 'Error in loadGenres!';
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 4000)
        }
      })
    },*/
/*    addMovie() {
      this.$router.push()
    },*/
/*    clickDetails(movieId) {
      this.movieService.findMovieDetail(movieId).then((movieDetailsDto) => {
        if (movieDetailsDto.status === 'OK') {
          this.movie = movieDetailsDto.data;
          this.showDetailsModal();
        } else {
          this.errorMessage = 'Error in clickDetails!';
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 4000)
        }
      })
    },*/
/*    addOrRemoveInGenresFilter(index) {
      let genre = this.genres[index];
      let elementIndex = this.filterParams.genres.indexOf(genre);

      if (elementIndex === -1) {
        this.filterParams.genres.push(genre)
      } else {
        this.filterParams.genres.splice(elementIndex, 1);
      }
    },*/
/*    deletedMovie(isDeleted) {

      if (isDeleted) {
        this.hideDetailsModal();
        this.successMessage = 'The movie was successfully deleted!';
        this.showSuccessModal();
        setTimeout(() => {
          this.hideSuccessModal()
        }, 4000)


        this.loadMovies();

      } else {

        this.errorMessage = 'We could not delete the movie!'
        this.showErrorModal();
        setTimeout(() => {
          this.hideErrorModal()
        }, 3000)

      }
    },*/
/*    successAddMovie() {
      this.hideAddMovieModal();
      this.successMessage = 'The movie was successfully added!';
      this.showSuccessModal();
      setTimeout(() => {
        this.hideSuccessModal()
      }, 4000)

      this.loadMovies();
    },*/
/*    mapRespPagToPag(data) {
      this.pagination.empty = data.empty;
      this.pagination.first = data.first;
      this.pagination.last = data.last;
      this.pagination.currentPage = data.number + 1;
      this.pagination.perPage = data.size;
      this.pagination.totalElements = data.totalElements;
      this.pagination.totalPages = data.totalPages;
    },*/
/*    changePerPage(value) {
      this.pagination.perPage = value;
      this.pagination.currentPage = 1;
      this.loadMovies();
    },*/
/*    changeCurrentPage(value) {
      this.pagination.currentPage = value;
      this.loadMovies();
    },*/
/*    getStatistics() {
      console.log('before')
      this.movieService.getStatistics().then((resp) => {

        console.log('after')
        if (resp.status === 'OK') {
          this.statistics = resp.data;
          this.showStatisticsModal();

        } else {

          this.errorMessage = 'We have problem with server,please try again later!'
          this.showErrorModal();
          setTimeout(() => {
            this.hideErrorModal()
          }, 4000)

        }
      })
    },*/
/*    clearFilters() {
      this.pagination.orderBy = 'year,desc';

      this.durationFilter.minDuration = 30;
      this.durationFilter.maxDuration = 300;
      this.ratingFilter.minRating = 0;
      this.ratingFilter.maxRating = 10;
      this.$refs['genres'].forEach(el => console.log(el.childNodes[0].checked = false));

      this.filterParams.minDuration = 30;
      this.filterParams.maxDuration = 300;
      this.filterParams.minRating = 1;
      this.filterParams.maxRating = 10;
      this.filterParams.searchText = '';
      this.filterParams.genres = [];
    },*/
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
    }
  },
  computed: {
    genres() {
      return this.$store.getters.getGenres;
    },
  }
}
</script>

<style scoped>
@import url("./assets/css/main.css");
</style>
