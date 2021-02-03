import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import ChallengeList from "@/views/challenge/ChallengeList.vue";
import ChallengeDetail from "@/views/challenge/ChallengeDetail.vue";
import Login from "../views/User/Login.vue";
import Register from "../views/User/Register.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/challenge/list",
    name: "ChallengeList",
    component: ChallengeList,
  },
  {
    path: "/challenge/detail",
    name: "ChallengeDetail",
    component: ChallengeDetail,
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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
