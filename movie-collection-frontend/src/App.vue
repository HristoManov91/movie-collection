<template>
  <div id="app">
    <header>
      <NavigationComponent/>
      <img src="./assets/image/welcome-image.jpg" alt="home-cinema-image">
    </header>
    <main class="container">
      <transition name="fade" mode="out-in">
        <router-view/>
      </transition>
    </main>
    <FooterComponent />
  </div>
</template>
<script>

import NavigationComponent from "@/components/NavigationComponent";
import FooterComponent from "@/components/FooterComponent";


export default {
  name: 'App',
  components: {
    NavigationComponent,
    FooterComponent
  },
  mounted() {
    this.$store.dispatch('getGenres')
    this.$store.dispatch('getPlatforms')
  },
}
</script>

<style scoped>
@import url("./assets/css/main.css");

.fade-enter , .fade-leave-to {
  opacity: 0;
}

.fade-enter-active , .fade-leave.active {
  transition: all 1s ease;
}
</style>
