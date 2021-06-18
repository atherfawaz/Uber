import { createStore } from 'vuex';
//import axios from 'axios';

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
      console.log("YOLO:", data);
      fetch('http://localhost:8080/register', {
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
});
