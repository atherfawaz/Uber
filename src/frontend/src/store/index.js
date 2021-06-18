import { createStore } from 'vuex';

export default createStore({
  state: {
    userDets: {
      name: String,
      pass: String,
      email: String,
    },
  },

  getters: {},

  mutations: {
    setUser(name, pass, email) {
      this.userDets.name = name;
      this.userDets.pass = pass;
      this.userDets.email = email;
    }
  },

  actions: {
    register(context, data) {
      fetch('api/register', {
        mode: 'no-cors',
        method: 'POST',
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
      }).then(res => {
        console.log("Res: ", res);
      });
    }
  },
  login(context, data) {
    fetch('api/login', {
      mode: 'no-cors',
      method: 'POST',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data)
    }).then(res => {
      console.log("Res: ", res);
    });
  }
});
