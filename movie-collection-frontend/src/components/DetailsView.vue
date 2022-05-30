<template>
  <div class="detailsView">
    <img class="detailsPosterImage"
         src="https://i.ibb.co/FDGqCmM/papers-co-ag74-interstellar-wide-space-film-movie-art-33-iphone6-wallpaper.jpg"
         alt="poster-image">
    <div class="detailsViewInfo">
      <p class="detailsViewTitle1">The lord of the rings</p>
      <p class="detailsViewTitle2">The return of the king</p>
      <ul class="detailsViewGenres">
        <li>ACTION</li>
        <li>FANTASY</li>
        <li>ACTION</li>
      </ul>
      <p class="detailsViewDescription">
        lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
        magna aliqua. Ut enim adipiscing
      </p>
      <button class="detailsViewButtonTrailer" ref="#">
        <font-awesome-icon icon="fa-solid fa-circle-play"/>
        WATCH TRAILER
      </button>
      <p class="detailsViewIMDb">IMDb: 7.3</p>
      <ul class="yearDurationAudioInfo">
        <li class="year">Year: 2005</li>
        <li class="duration">Duration: 180min</li>
        <li>Audio: Българско</li>
      </ul>
      <ul class="platforms">
        <li>HBOMAX</li>
        <li>DISNEY</li>
        <li>My PC</li>
      </ul>
<!--      <div class="detailsViewButtons">-->
        <ul class="detailsViewButtons">
          <li class="editButton" @click="show()">
            <font-awesome-icon icon="fa-solid fa-pen-to-square"/>
            EDIT
          </li>
          <li class="deleteButton" >
            <font-awesome-icon icon="fa-solid fa-trash-can"/>
            DELETE
          </li>
        </ul>
<!--      </div>-->
    </div>
    <modal class="movieEditModal" name="movieEditModal" :resizable="false" :reset="true" width="850px" height="auto" :scrollable="true">
      <EditMovie/>
    </modal>
  </div>
</template>

<script>
import EditMovie from "@/components/EditMovie";

export default {
  name: "DetailsView",
  components: {
    EditMovie
  },
  methods: {
    show () {
      this.$modal.show('movieEditModal');
    },
    hide () {
      this.$modal.hide('movieEditModal');
    },
  },
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

/*.detailsView :first-child {
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}

.detailsView :last-child {
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}*/

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
}

.detailsViewInfo p.detailsViewTitle2 {
  margin: 1rem;
  font-size: 1.7rem;
  text-transform: uppercase;
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

.detailsViewInfo p.detailsViewDescription {
  margin: 1.5rem 2rem;
}

.detailsViewInfo button.detailsViewButtonTrailer {
  margin: 1rem 2rem;
  font-size: 1rem;
  text-transform: uppercase;
  color: orange;
  border: 1px solid orange;
  background: rgba(32, 32, 32, 0.9);
  border-radius: 4px;
  display: inline-block;
}

.detailsViewInfo button.detailsViewButtonTrailer:hover {
  cursor: pointer;
  color: rgba(32, 32, 32, 0.9);
  background: orange;
}

.detailsViewInfo .detailsViewIMDb {
  margin: 1rem 2rem;
  display: inline-block;
  font-size: 1.5rem;
}

ul.detailsViewButtons li {
  display: inline-block;
  font-size: 1rem;
  margin: 1.5rem 2rem;
  padding: 0.6rem 1.5rem;
  border-radius: 10px;
  text-align: center;
  text-transform: uppercase;
  font-weight: bold;
}

ul.detailsViewButtons li.deleteButton {
  color: white;
  background-color: red;
}

ul.detailsViewButtons li.editButton {
  color: black;
  background-color: white;
}

ul.detailsViewButtons li.deleteButton:hover {
  cursor: pointer;
  color: red;
  background-color: white;
}

ul.detailsViewButtons li.editButton:hover {
  cursor: pointer;
  background-color: greenyellow;
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
  background: rgb(252, 186, 3 , 0.4);
}

</style>