<template>
  <div class="homeContainer">
    <transition name="fade" mode="out-in">
      <router-view/>
    </transition>
    <p class="homeTitle">Welcome to the Movies Collection site!</p>
    <p class="homeDescription">Here you can make your collection of favorite movies with information about them and
      when you wonder when to watch a movie you can choose.</p>
    <p class="homeInfo">You will have a choice of different filters to search by such as genres, movie length,
      rating and free text (search in the movie title). You can also change the order in which they are displayed.
      You can add and remove movies, edit them, see detailed information about them and
      statistics about your collection.</p>
    <div class="imageContainer">
      <img class="selectedPicture" :src="require(`../assets/image/${selectedImage}`)" alt="image">
    </div>
    <div class="imageRow">
      <div class="imageColumn">
        <img class="smallImage" src="../assets/image/default.png" alt="Collection image" @click="clickImg('default.png');">
      </div>
      <div class="imageColumn">
        <img class="smallImage" src="../assets/image/add-movie.png" alt="Add movie image" @click="clickImg('add-movie.png');">
      </div>
      <div class="imageColumn">
        <img class="smallImage" src="../assets/image/movie-details.png" alt="Edit movie image" @click="clickImg('movie-details.png');">
      </div>
      <div class="imageColumn">
        <img class="smallImage" src="../assets/image/edit-movie.png" alt="Details movie image" @click="clickImg('edit-movie.png');">
      </div>
      <div class="imageColumn">
        <img class="smallImage" src="../assets/image/statistics.png" alt="Statistics movie image" @click="clickImg('statistics.png');">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeComponent",
  data() {
    return {
      imgSrc: 'default.png'
    }
  },
  methods: {
    clickImg(imgName) {
      this.imgSrc = imgName;
    }
  },
  computed: {
    selectedImage (){
      return this.imgSrc;
    }
  }
}
</script>

<style scoped>

div.homeContainer {
  padding: 1rem;
}

p.homeTitle {
  text-align: center;
  font-size: 5rem;
  padding: 1rem;
  color: #010340;
  font-weight: bold;
  letter-spacing: 1px;
}

p.homeDescription {
  font-size: 2rem;
  padding: 1rem 2rem;
  justify-content: space-between;
  text-align: center;
}

p.homeInfo {
  font-size: 1.5rem;
  padding: 1rem 2rem;
  justify-content: space-between;
  text-align: center;
}

div.imageContainer {
  height: 30rem;
  display: flex;
  justify-content: center;
}

div.imageRow {
  width: 100%;
  height: 6rem;
  margin-top: 0.5rem;
  justify-content: center;
  display: flex;
}


div.imageRow div.imageColumn {
  float: left;
  height: 6rem;
  width: 9rem;
  padding: 10px;
}

div.imageRow div.imageColumn img.smallImage {
  height: 5.5rem;
  width: 8.5rem;
}

div.imageRow div.imageColumn img.smallImage:hover {
  border: 2px solid white;
  cursor: pointer;
}

img.selectedPicture {
  height: 29rem;
  border: 3px solid black;
}

.fade-enter , .fade-leave-to {
  opacity: 0;
}

.fade-enter-active , .fade-leave.active {
  transition: all 1s ease;
}
</style>