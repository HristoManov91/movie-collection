import Vue from 'vue'
import VueRouter from 'vue-router'
import EditMovie from "@/components/EditMovie";
import AddMovie from "@/components/AddMovie";
import StatisticsView from "@/components/StatisticsView";
import DetailsView from "@/components/DetailsView";
import App from "@/App";
import LoginComponent from "@/components/LoginComponent";
import RegisterComponent from "@/components/RegisterComponent";

Vue.use(VueRouter)

const routes = [
    {path: "/register" , component: RegisterComponent},
    {path: "/login" , component: LoginComponent},
    {path: "/movies" , name: 'home' , component: App},
    {path: "/movies/edit/:movieId" , name: "edit", component: EditMovie},
    {path: "/movies/add" , name: "add", component: AddMovie},
    {path: "/movies/statistics" , name: "statistics", component: StatisticsView},
    {path: "/movies/details/:movieId" , name: "details", component: DetailsView},
];

const router = new VueRouter({
    routes ,
    mode: 'history'
});

export default router;