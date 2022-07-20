<template>
  <div>
    <nav>
      <ul>
        <router-link tag="li" :to="{name: 'home'}">HOME</router-link>
        <router-link v-if="currentUser" tag="li" :to="{name: 'movies'}">MOVIES</router-link>
        <router-link v-if="currentUser" tag="li" :to="{name: 'addMovie'}">ADD MOVIE</router-link>
        <router-link v-if="currentUser" tag="li" :to="{name: 'statistics'}">STATISTICS</router-link>
      </ul>
      <ul>
        <router-link v-if="!currentUser" tag="li" :to="{name: 'login'}">LOGIN</router-link>
        <router-link v-if="!currentUser" tag="li" :to="{name: 'register'}">REGISTER</router-link>
        <!-- ToDo -->
        <li v-if="currentUser" @click="logOut">LOGOUT</li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  name: "NavigationComponent",
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  }
}
</script>

<style scoped>

header nav {
  background-color: #0d1015;
  height: 3rem;
  padding-top: 5px;
  padding-bottom: 5px;
  display: flex;
  justify-content: space-between;
}

header nav ul {
  display: flex;
  justify-content: space-around;
}

header nav li {
  font-size: 1.2rem;
  color: white;
  display: inline-block;
  margin: 0 0.5rem;
  padding: 0.4rem;
  border-radius: 3px;
  font-weight: bold;
  letter-spacing: 1px;
}

header nav li:hover {
  cursor: pointer;
  color: orange;
}
</style>