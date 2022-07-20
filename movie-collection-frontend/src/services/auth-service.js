import axios from "axios";
const USER_CONTROLLER_BASE_URL = 'http://localhost:8081/users/'

class AuthService {

    login(user) {
        return axios.post(USER_CONTROLLER_BASE_URL + 'login' , user).then((resp) => {
                if (resp.data.accessToken){
                    console.log('ok response' , resp)
                    localStorage.setItem('user' , JSON.stringify(resp.data))
                }
                console.log('response' , resp)
                return resp.data;
        })
    }

    logout(){
        localStorage.removeItem('user');
    }

    async register(user){
        console.log('user: ', user )
        await axios.post(USER_CONTROLLER_BASE_URL + 'register' , user).then((resp) => {
            console.log('resp:' , resp)
        })
    }
}

export default new AuthService();