import { createStore } from "vuex";
import axios from "axios";

export default createStore({
  state: {
    userDets: {
      name: "",
      email: "",
    },
  },

  getters: {},

  mutations: {
    setUser: (state, email) => {
      state.userDets.email = email;
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
