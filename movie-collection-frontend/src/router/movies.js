
const moviesRoutes = [
    {
        path: "/movies",
        name: 'movies',
        component: () => import(/* webpackChunkName: "movies" */ "@/components/MoviesComponent"),
        children: [
            {
                path: "add-movie",
                name: 'addMovie',
                component: () => import(/* webpackChunkName: "addMovie" */ "@/components/AddMovieComponent")
            },
            {
                path: "details/:movieId",
                name: 'details',
                component: () => import(/* webpackChunkName: "details" */ "@/components/DetailsMovieComponent")
            },
            {
                path: "edit/:movieId",
                name: 'edit',
                props: true,
                component: () => import(/* webpackChunkName: "edit" */ "@/components/EditMovieComponent")
            },
            {
                path: "statistics",
                name: 'statistics',
                component: () => import(/* webpackChunkName: "statistics" */ "@/components/StatisticsViewComponent")
            },
        ]
    },
]

export default moviesRoutes;