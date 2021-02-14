import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../views/Main.vue";
import NotFound from "../views/404NotFound.vue";
import ErrorPage from "../views/Error.vue";
import Challenge from "../views/Challenge/Challenge.vue";
import ChallengeList from "../views/Challenge/ChallengeList.vue";
import ChallengeDetail from "../views/Challenge/ChallengeDetail.vue";
import InProgressChallenge from "../views/Challenge/InProgressChallenge.vue";
import DailyMission from "../views/Challenge/DailyMission.vue";
import Post from "../views/Post/Post.vue";
import PostUpload from "../views/Post/PostUpload.vue";
import Comment from "../views/Post/Comment.vue";
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
    path: "/404",
    name: "404",
    component: NotFound,
  },
  {
    path: "/error",
    name: "Error",
    component: ErrorPage,
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
        path: ":challengeId",
        name: "ChallengeDetail",
        component: ChallengeDetail,
      },
      {
        path: "inprogress",
        name: "InProgressChallenge",
        component: InProgressChallenge,
      },
      {
        path: "daily",
        name: "DailyMission",
        component: DailyMission,
      },
    ],
  },
  {
    path: "/post",
    name: "Post",
    component: Post,
  },
  {
    path: "/post/upload",
    name: "PostUpload",
    component: PostUpload,
    props: true,
  },
  {
    path: "/comment/:postId",
    name: "Comment",
    component: Comment,
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
