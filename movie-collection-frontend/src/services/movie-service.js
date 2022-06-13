import axios from "axios";

const MOVIES_CONTROLLER_BASE_URL = 'http://localhost:8081/movies/'

export class MovieService {

    async findAllMovies() {
        let movies = {};

        await axios.get(MOVIES_CONTROLLER_BASE_URL + 'all').then((resp) => {
            movies.status = 'OK'
            movies.data = resp.data;
        }).catch((err) => {
            movies.status = 'ERROR';
            movies.error = err.response.data;
        })

        return movies;
    }

    async addMovie(movieDto) {
        //ToDo може върне обект и да се отвори details
        let result = {};

        await axios.post(MOVIES_CONTROLLER_BASE_URL + 'new' , movieDto).then((response) => {
            result.status = 'OK';
            result.data = response.data;
        }).catch((err) => {
            result.status = 'ERROR';
            result.error = err.response.data;
            console.log(err);
        })

        return result;
    }

    async findMovieDetail(movieId) {
        let movie = {}

        await axios.get(MOVIES_CONTROLLER_BASE_URL + movieId).then((resp) => {
            movie.status = 'OK';
            movie.data = resp.data;
        }).catch((err) => {
            movie.status = 'ERROR';
            movie.error = err.response.data;
        })

        return movie;
    }

    async saveEditedMovie(editedMovieDto) {
        let movieDetails = {};

        await axios.post(MOVIES_CONTROLLER_BASE_URL + 'edit', editedMovieDto).then((resp) => {
            movieDetails.status = 'OK';
            movieDetails.data = resp.data;
        }).catch((err) => {
            movieDetails.status = 'ERROR';
            movieDetails.error = err.response.data;
        })

        return movieDetails;
    }

    async deleteMovie(movieId) {
        let deleteMovie = {};

        const params = {movieId : movieId};

        await axios.delete(MOVIES_CONTROLLER_BASE_URL + 'delete', {params}).then(() => {
            deleteMovie.status = 'OK';
        }).catch((err) => {
            deleteMovie.status = 'ERROR';
            deleteMovie.error = err.message;
        })

        return deleteMovie;
    }
}


