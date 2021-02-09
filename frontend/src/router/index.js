import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import Challenge from "../views/Challenge/Challenge.vue";
import ChallengeList from "../views/Challenge/ChallengeList.vue";
import ChallengeDetail from "../views/Challenge/ChallengeDetail.vue";
import InProgressChallenge from "../views/Challenge/InProgressChallenge.vue";
import Post from "../views/Post/Post.vue";
import Login from "../views/User/Login.vue";
import Register from "../views/User/Register.vue";
import FindPassword from "../views/User/FindPassword.vue";
import Profile from "../views/User/Profile.vue";
import ZBTI from "../views/Zbti/Zbti.vue";
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
    path: "/challenge",
    name: "Challenge",
    component: Challenge,
    children: [
      {
        path: "",
        name: "ChallengeList",
        component: ChallengeList,
      },
      {
        path: "detail",
        name: "ChallengeDetail",
        component: ChallengeDetail,
      },
      {
        path: "inprogress",
        name: "InProgressChallenge",
        component: InProgressChallenge,
      },
    ],
  },
  {
    path: "/post",
    name: "Post",
    component: Post,
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
    path: "/user/profile",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/zbti",
    name: "ZBTI",
    component: ZBTI,
    children: [
      {
        path: "",
        name: "ZBTIIntro",
        component: ZbtiIntro,
      },
      {
        path: "test",
        name: "ZbtiTest",
        component: ZbtiTest,
      },
      {
        path: "result",
        name: "ZbtiResult",
        component: ZbtiResult,
        props: true,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
