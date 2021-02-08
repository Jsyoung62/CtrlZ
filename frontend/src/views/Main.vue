<template>
  <div class="main">
    <Header title="Ctrl Z" />
    <Navigation />
    <ScoreBoard />
    <BadgeBoard />
    <ChallengeBoard />
    <RecommendBorad />
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import ScoreBoard from "@/components/main/ScoreBoard.vue";
import BadgeBoard from "@/components/main/BadgeBoard.vue";
import ChallengeBoard from "@/components/main/ChallengeBoard.vue";
import RecommendBorad from "@/components/main/RecommendBoard.vue";
import "@/components/css/main/index.scss";

export default {
  name: "Main",
  components: {
    Header,
    Navigation,
    ScoreBoard,
    BadgeBoard,
    ChallengeBoard,
    RecommendBorad,
  },
};
</script>
