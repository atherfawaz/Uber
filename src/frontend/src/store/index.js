import { createStore } from "vuex";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    userDets: {
      name: "",
      email: "",
    },
    isLoggedIn: false,
    rideBooked: false,
  },

  plugins: [createPersistedState()],

  mutations: {
    setUser: (state, email) => {
      state.userDets.email = email;
    },
    authenticated(state, auth) {
      state.isLoggedIn = auth;
    },
    setRideBooked(state, auth) {
      state.rideBooked = auth;
    },
  },

  actions: {
    register(context, data) {
      return axios.post("api/register", data);
    },

    login(context, data) {
      return axios.post("api/login", data);
    },

    getDetails() {
      return axios.post("api/getPassengerDetails", {
        email: this.state.userDets.email,
      });
    },
  },
});
