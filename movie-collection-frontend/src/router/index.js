import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginComponent from "@/components/LoginComponent";
import RegisterComponent from "@/components/RegisterComponent";
import DetailsViewComponent from "@/components/DetailsMovieComponent";
import EditMovieComponent from "@/components/EditMovieComponent";
import StatisticsViewComponent from "@/components/StatisticsViewComponent";
import AddMovieComponent from "@/components/AddMovieComponent";
import MoviesComponent from "@/components/MoviesComponent";
import HomeComponent from "@/components/HomeComponent";

Vue.use(VueRouter)

const routes = [
    {path: "/" , component: HomeComponent},
    {path:"/movies" , name: 'movies' , component: MoviesComponent},
    {path: "/movies/login" , name: 'login' , component: LoginComponent},
    {path: "/movies/register" , name: 'register', component: RegisterComponent},
    {path: "/movies/add-movie" , name: 'addMovie' , component: AddMovieComponent},
    {path: "/movies/details/:movieId" , name: 'details' , component: DetailsViewComponent},
    {path: "/movies/edit/:movieId" , name: 'edit' , component: EditMovieComponent},
    {path: "/movies/statistics" , name: 'statistics' , component: StatisticsViewComponent},
    {path: "*" , redirect: '/'}
];

export default new VueRouter({
    routes,
    mode: 'history'
});