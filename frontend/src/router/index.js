import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import ChallengeList from "@/views/Challenge/ChallengeList.vue";
import ChallengeDetail from "@/views/Challenge/ChallengeDetail.vue";
import InProgressChallenge from "@/views/Challenge/InProgressChallenge.vue";
import Login from "../views/User/Login.vue";
import Register from "../views/User/Register.vue";
import FindPassword from "../views/User/FindPassword.vue";
import ZbtiIntro from "../views/Zbti/ZbtiIntro.vue";
import ZbtiTest from "../views/Zbti/ZbtiTest.vue";
import ZbtiResult from "../views/Zbti/ZbtiResult.vue";

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
    path: "/challenge/inprogress",
    name: "InProgressChallenge",
    component: InProgressChallenge,
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
    path: "/find",
    name: "FindPassword",
    component: FindPassword,
  },
  {
    path: "/zbti",
    name: "ZbtiIntro",
    component: ZbtiIntro,
  },
  {
    path: "/zbti/test",
    name: "ZbtiTest",
    component: ZbtiTest,
  },
  {
    path: "/zbti/result",
    name: "ZbtiResult",
    component: ZbtiResult,
    props: true,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
