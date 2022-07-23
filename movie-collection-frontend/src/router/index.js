import Vue from 'vue'
import VueRouter from 'vue-router'

import moviesRoutes from "./movies"

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        name: 'home',
        component: () => import(/* webpackChunkName: "home" */ "@/components/HomeComponent"),
        children: [
            {
                path: "users/login",
                name: 'login',
                component: () => import(/* webpackChunkName: "login" */ "@/components/LoginComponent")
            },
            {
                path: "users/register",
                name: 'register',
                component: () => import(/* webpackChunkName: "register" */ "@/components/RegisterComponent")
            },
        ]
    },
    ...moviesRoutes,
    {path: "*", redirect: '/'}//ToDo може да направя да се показва errormodal или errorpage
];

const router = new VueRouter({
    routes,
    mode: 'history'
})

router.beforeEach((to, from, next) => {
    const publicPages = ['/users/login', '/users/register', '/'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
});

export default router;