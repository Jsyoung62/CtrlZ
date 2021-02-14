<template>
  <div class="root">
    <Splash v-if="this.$store.state.splash" />

    <div v-else class="main">
      <Header title="Ctrl Z" right-icon="notifications_none" />
      <Navigation />

      <Challenges
        :sponsered-challenge="sponseredChallenge"
        :recommend-challenges="recommendChallenges"
      />

      <DailyMission />
    </div>
  </div>
</template>

<script>
import Splash from "@/components/main/Splash.vue";
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import Challenges from "@/components/main/Challenges.vue";
import DailyMission from "@/components/main/DailyMission.vue";
import "@/components/css/main/index.scss";

export default {
  name: "Main",
  components: {
    Splash,
    Header,
    Navigation,
    Challenges,
    DailyMission,
  },
  data: () => {
    return {
      sponseredChallenge: {},
      recommendChallenges: [],
    };
  },
  created() {
    // 첫 접속시 스플래시 화면 3.5초 지속 후 zbti테스트 페이지로 이동
    if (this.$store.state.splash) {
      window.setTimeout(() => {
        this.$store.commit("ENDSPLASH");
        this.$router.push("/zbti");
      }, 3500);
    }

    // 데이터 불러오기
    this.callSponseredChallenge();
    this.callRecommendChallenges();
  },
  methods: {
    // 기업연계 챌린지 정보 불러오기
    callSponseredChallenge() {
      this.$axios({
        url: "/challenge/type",
        method: "GET",
        params: {
          challengeType: "기업",
        },
      }).then((res) => {
        this.sponseredChallenge = res.data[0];
      });
    },
    // 추천 챌린지 정보 불러오기
    callRecommendChallenges() {
      this.$axios({
        url: "/challenge/all",
        method: "GET",
      }).then((res) => {
        this.recommendChallenges = res.data;
      });
    },
  },
};
</script>
