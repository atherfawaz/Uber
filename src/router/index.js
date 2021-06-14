import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Profile from "../views/Profile.vue";
import Report from "../views/Report.vue";
import BookRide from "../views/BookRide.vue";
import CurrentRide from "../views/CurrentRide.vue";
import Feedback from "../views/Feedback.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/report",
    name: "Report",
    component: Report,
  },
  {
    path: "/bookride",
    name: "BookRide",
    component: BookRide,
  },
  {
    path: "/bookride",
    name: "BookRide",
    component: BookRide,
  },
  {
    path: "/currentride",
    name: "CurrentRide",
    component: CurrentRide,
  },
  {
    path: "/feedback",
    name: "Feedback",
    component: Feedback,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
