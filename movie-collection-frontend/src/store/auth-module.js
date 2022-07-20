import AuthService from '../services/auth-service';

const user = JSON.parse(localStorage.getItem('user'));

const initialState = user
    ? { status: { loggedIn: true }, user }
    : { status: { loggedIn: false }, user: null };

export const auth = {
    namespaced: true,
    state: initialState,
    actions: {
        login({ commit }, user) {
            return AuthService.login(user).then(
                user => {
                    console.log('loginSuccess', user)
                    commit('loginSuccess', user);
                    return Promise.resolve(user);
                },
                error => {
                    console.log('loginFailure' , error)
                    commit('loginFailure');
                    return Promise.reject(error);
                }
            );
        },
        logout({ commit }) {
            AuthService.logout();
            commit('logout');
        },
        register({ commit }, user) {
            return AuthService.register(user).then(
                response => {
                    console.log('registerSuccess')
                    console.log(Promise.resolve(response.data))
                    commit('registerSuccess');
                    return Promise.resolve(response.data);
                },
                error => {
                    console.log('registerFailure')
                    console.log(Promise.reject(error))
                    commit('registerFailure');
                    return Promise.reject(error);
                }
            );
        }
    },
    mutations: {
        loginSuccess(state, user) {
            state.status.loggedIn = true;
            state.user = user;
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        registerSuccess(state) {
            console.log('regStaBefore1: ' , state)
            state.status.loggedIn = false;
            console.log('regStaAfter1: ' , state)
        },
        registerFailure(state) {
            console.log('regStaBefore2: ' , state)
            state.status.loggedIn = false;
            console.log('regStaAfter2: ' , state)
        }
    }
};