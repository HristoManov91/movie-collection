import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";

Vue.use(Vuex)

export default new Vuex.Store ({
    state: {
        genres: [],
        platforms: []
    },
    getters: {
        getGenres(state) {
            return state.genres
        },
        getPlatforms(state) {
            return state.platforms;
        }
    },
    mutations: {
        setGenres(state , payload){
            state.genres = payload;
        },
        setPlatforms(state, payload){
            state.platforms = payload;
        }
    },
    actions: {
        async getGenres(context){
            const axiosResponse = await axios.get('http://localhost:8081/genres/all');
            context.commit('setGenres' , axiosResponse.data)
        },
        async getPlatforms(context){
            const axiosResponse = await axios.get('http://localhost:8081/platforms/all');
            context.commit('setPlatforms' , axiosResponse.data)
        }
    }
})