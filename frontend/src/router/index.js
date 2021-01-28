import Vue from "vue";
import VueRouter from "vue-router";
import About from "../views/About.vue";
import ChallengeList from "@/views/challenge/ChallengeList.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/about",
    name: "About",
    component: About,
  },
  {
    path: "/challengelist",
    name: "ChallengeList",
    component: ChallengeList,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
