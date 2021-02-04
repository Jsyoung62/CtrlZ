<template>
  <div class="challengeList">
    <Header title="전체 챌린지" />
    <SideNav />
    <WeeklyChallenge />
    <SponsoredChallenge />
    <NormalChallenge />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import SideNav from "@/components/common/SideNav.vue";
import WeeklyChallenge from "@/components/challenge/WeeklyChallenge.vue";
import SponsoredChallenge from "@/components/challenge/SponsoredChallenge.vue";
import NormalChallenge from "@/components/challenge/NormalChallenge.vue";
import "@/components/css/challenge/challengeList.scss";

export default {
  name: "ChallengeList",
  components: {
    Header,
    SideNav,
    WeeklyChallenge,
    SponsoredChallenge,
    NormalChallenge,
  },
};
</script>
