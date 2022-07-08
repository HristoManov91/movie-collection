import Vue from 'vue'
import VueRouter from 'vue-router'

import moviesRoutes from "./movies"

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        name: 'home',
        component: () => import(/* webpackChunkName: "home" */ "@/components/HomeComponent")
    },
    {
        path: "/login",
        name: 'login',
        component: () => import(/* webpackChunkName: "login" */ "@/components/LoginComponent")
    },
    {
        path: "/register",
        name: 'register',
        component: () => import(/* webpackChunkName: "register" */ "@/components/RegisterComponent")
    },
    ...moviesRoutes,
    {path: "*", redirect: '/'}//ToDo може да направя да се показва errormodal или errorpage
];

export default new VueRouter({
    routes,
    mode: 'history'
});