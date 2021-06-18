import Vue from "vue";
import { Vuex, createStore } from "vuex";

Vue.use(Vuex);

export default createStore({
  state: {
    rideDets: {
      from: String,
      to: String,
      type: String,
    },
  },

  getters: {},

  mutations: {},

  actions: {},
});
