import axios from "axios";

export class MovieService {

    async findAllMovies() {
        let movies = {};

        await axios.get('http://localhost:8081/movies/all').then((resp) => {
            movies.status = 'OK'
            console.log('data' , resp.data)
            movies.data = resp.data;
        }).catch((err) => {
            movies.status = 'ERROR';
            movies.error = err.response.data;
        })

        return movies;
    }


}


