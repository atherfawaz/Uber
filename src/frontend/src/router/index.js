import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Profile from "../views/Profile.vue";
import Report from "../views/Report.vue";
import BookRide from "../views/BookRide.vue";
import CurrentRide from "../views/CurrentRide.vue";
import Feedback from "../views/Feedback.vue";
import store from "../store/index";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    beforeEnter: (to, from, next) => {
      if (store.state.isLoggedIn == false) {
        next("/register");
      } else if (store.state.isLoggedIn == true) {
        next("/profile");
      } else {
        next();
      }
    },
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    beforeEnter: (to, from, next) => {
      if (store.state.isLoggedIn == true) {
        next("/profile");
      } else {
        next();
      }
    },
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    beforeEnter: (to, from, next) => {
      if (store.state.isLoggedIn == true) {
        next("/profile");
      } else {
        next();
      }
    },
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile,
    beforeEnter: (to, from, next) => {
      if (store.state.isLoggedIn == false) {
        next("/register");
      } else {
        next();
      }
    },
  },
  {
    path: "/report",
    name: "Report",
    component: Report,
    beforeEnter: (to, from, next) => {
      if (store.state.isLoggedIn == false) {
        next("/register");
      } else {
        next();
      }
    },
  },
  {
    path: "/bookride",
    name: "BookRide",
    component: BookRide,
    beforeEnter: (to, from, next) => {
      if (store.state.isLoggedIn == false) {
        next("/register");
      } else if (store.state.rideBooked == true) {
        next("/currentride");
      } else {
        next();
      }
    },
  },
  {
    path: "/currentride",
    name: "CurrentRide",
    component: CurrentRide,
    props: true,
    beforeEnter: (to, from, next) => {
      if (store.state.isLoggedIn == false) {
        next("/register");
      } else if (store.state.rideBooked == false) {
        next("/bookride");
      } else {
        next();
      }
    },
  },
  {
    path: "/feedback",
    name: "Feedback",
    component: Feedback,
    beforeEnter: (to, from, next) => {
      if (store.state.isLoggedIn == false) {
        next("/register");
      } else if (store.state.rideBooked == true) {
        next("/currentride");
      } else if (
        store.state.rideBooked == false &&
        from.name != "CurrentRide"
      ) {
        next("/bookride");
      } else {
        next();
      }
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
