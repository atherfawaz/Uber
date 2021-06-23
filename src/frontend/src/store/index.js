import { createStore } from "vuex";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    // user dets
    username: "",
    email: "",
    rating: 0.0,
    balance: 0.0,

    // random
    isLoggedIn: false,
    rideBooked: false,

    // driver details
    driverName: "",
    carName: "",
    carLicense: "",
    driverRating: 0.0,

    // ride dets
    to: "",
    from: "",
    type: "",
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

    getTo: (state) => {
      return state.to;
    },
    getFrom: (state) => {
      return state.from;
    },
    getType: (state) => {
      return state.type;
    },
    getDriverName: (state) => {
      return state.driverName;
    },
    getDriverRating: (state) => {
      return state.driverRating;
    },
    getCar: (state) => {
      return state.carName;
    },
    getLicense: (state) => {
      return state.carLicense;
    },
  },

  mutations: {
    setUser: (state, payload) => {
      state.email = payload.email;
      state.username = payload.name;
      state.rating = payload.rating;
      state.balance = payload.balance;
    },
    setRideDets: (state, payload) => {
      state.to = payload.to;
      state.from = payload.from;
      state.type = payload.type;
    },
    setDriverDets: (state, payload) => {
      state.driverName = payload.name;
      state.carName = payload.car;
      state.carLicense = payload.license;
      state.driverRating = payload.rating;
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

    getPassengerDetails() {
      return axios.post("api/getPassengerDetails", {
        email: this.state.email,
      });
    },

    getVehicleDetails() {
      return axios.post("api/getVehicleDetails");
    },
  },
});
