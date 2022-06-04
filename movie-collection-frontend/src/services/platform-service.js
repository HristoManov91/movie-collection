import axios from "axios";

const PLATFORM_CONTROLLER_BASE_URL = 'http://localhost:8081/platforms/'

export class PlatformService {

    async findAllPlatforms() {
        let platforms = {};

        await axios.get(PLATFORM_CONTROLLER_BASE_URL + 'all').then((resp) => {
            platforms.status = 'OK';
            platforms.data = resp.data;
        }).catch((err) => {
            platforms.status = 'ERROR'
            platforms.error = err.response.data;
        })

        return platforms;
    }
}