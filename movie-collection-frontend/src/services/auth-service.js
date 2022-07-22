import axios from "axios";
const USER_CONTROLLER_BASE_URL = 'http://localhost:8081/users/'

class AuthService {

    login(user) {
        return axios.post(USER_CONTROLLER_BASE_URL + 'login' , user).then((resp) => {
                if (resp.data.accessToken){
                    localStorage.setItem('user' , JSON.stringify(resp.data))
                }

                return resp.data;
        })
    }

    logout(){
        localStorage.removeItem('user');
    }

    register(user){
        return axios.post(USER_CONTROLLER_BASE_URL + 'register' , user)
    }
}

export default new AuthService();