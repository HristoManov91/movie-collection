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

// routes.beforeEach((to, from, next) => {
//     const publicPages = ['/users/login', '/users/register', '/'];
//     console.log('to' , to)
//     console.log('from' , from)
//     console.log('next' , next)
//     const authRequired = !publicPages.includes(to.path);
//     console.log('authRequired' , authRequired)
//     const loggedIn = localStorage.getItem('user');
//     console.log('loggedIn' , loggedIn)
//     // trying to access a restricted page + not logged in
//     // redirect to login page
//     if (authRequired && !loggedIn) {
//         next('/login');
//     } else {
//         next();
//     }
// });

export default new VueRouter({
    routes,
    mode: 'history'
});