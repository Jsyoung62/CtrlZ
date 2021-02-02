import Vue from "vue";
import VueRouter from "vue-router";
import ChallengeList from "@/views/challenge/ChallengeList.vue";
import ChallengeDetail from "@/views/challenge/ChallengeDetail.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/challengelist",
    name: "ChallengeList",
    component: ChallengeList,
  },
  {
    path: "/challengedetail",
    name: "ChallengeDetail",
    component: ChallengeDetail,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
