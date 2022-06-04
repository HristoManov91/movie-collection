import axios from "axios"

const GENRES_CONTROLLER_BASE_URL = 'http://localhost:8081/genres/'

export class GenreService {

    async findAllGenres() {
        let genres = {};

        await axios.get(GENRES_CONTROLLER_BASE_URL + 'all').then((resp) => {
            genres.status = 'OK';
            genres.data = resp.data;
        }).catch((err) => {
            genres.status = 'ERROR';
            genres.error = err.response.data;
        });

        return genres;
    }
}