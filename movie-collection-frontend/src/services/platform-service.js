import axios from "axios";
import authHeader from "@/services/auth-header";

const PLATFORM_CONTROLLER_BASE_URL = 'http://localhost:8081/platforms/'

export class PlatformService {

    async findAllPlatforms() {
        let token = authHeader();
        let platforms = {};

        await axios.get(PLATFORM_CONTROLLER_BASE_URL + 'all' , {
            headers: {'Authorization': token.Authorization}
        }).then((resp) => {
            platforms.status = 'OK';
            platforms.data = resp.data;
        }).catch((err) => {
            platforms.status = 'ERROR'
            platforms.error = err.response.data;
        })

        return platforms;
    }
}