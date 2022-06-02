import axios from "axios";

const MOVIE_API_BASE_URL = 'http://localhost:8081/'

export class MovieService {

    async findAllMovies() {
        let movies = {};

        await axios.get(MOVIE_API_BASE_URL + 'movies/all').then((resp) => {
            movies.status = 'OK'
            movies.data = resp.data;
        }).catch((err) => {
            movies.status = 'ERROR';
            movies.error = err.response.data;
        })

        return movies;
    }


    async findMovieDetail(movieId) {
        let movie = {}

        // const params = {movieId : movieId};

        await axios.get(MOVIE_API_BASE_URL + 'movies/' + movieId).then((resp) => {
            movie.status = 'OK';
            movie.data = resp.data;
            console.log(movie)
        }).catch((err) => {
            movie.status = 'ERROR';
            movie.error = err.response.data;
        })

        return movie;
    }
}


