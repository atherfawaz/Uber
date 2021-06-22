import { createStore } from "vuex";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    username: "",
    email: "",
    rating: 0.0,
    balance: 0.0,
    isLoggedIn: false,
    rideBooked: false,
  },

  plugins: [createPersistedState()],

  getters: {
    getName: (state) => {
      return state.username;
    },

    getRating: (state) => {
      return state.rating;
    },

    getBalance: (state) => {
      return state.balance;
    },

    getEmail: (state) => {
      return state.email;
    },
  },

  mutations: {
    setUser: (state, payload) => {
      state.email = payload.email;
      state.username = payload.name;
      state.rating = payload.rating;
      state.balance = payload.balance;
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
        email: this.state.email,
      });
    },
  },
});
