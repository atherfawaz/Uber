import { createStore } from "vuex";

export default createStore({
  state: {
    userDets: {
      name: String,
      email: String,
    },
  },

  getters: {},

  mutations: {
    setUser(name, pass, email) {
      this.userDets.name = name;
      this.userDets.pass = pass;
      this.userDets.email = email;
    },
  },

  actions: {
    register(context, data) {
      return new Promise((resolve, reject) => {
        fetch("api/register", {
          mode: "no-cors",
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data),
        }).then((res) => {
          if (res.ok) {
            resolve("OK");
          } else {
            reject("!OK");
          }
        });
      });
    },

    login(context, data) {
      return new Promise((resolve, reject) => {
        fetch("api/login", {
          mode: "no-cors",
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data),
        }).then((res) => {
          if (res.ok) {
            resolve("OK");
          } else {
            reject("!OK");
          }
        });
      });
    },
  },
});
