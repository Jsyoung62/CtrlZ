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

      <DailyMission :data="dailyChallenge" />
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
import axios from "axios";

axios.defaults.baseURL = "http://i4a202.p.ssafy.io:8888";

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
      dailyChallenge: {
        imageURL: "",
        challengeName: "",
        challengeDescription: "",
      },
      sponseredChallenge: {
        imageURL: "",
        challengeName: "",
      },
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
    this.callDailyChallenge();
    this.callSponseredChallenge();
    this.callRecommendChallenges();
  },
  methods: {
    // 데일리 미션 정보 불러오기
    callDailyChallenge() {
      axios({
        url: "/challenge/",
        method: "GET",
        params: {
          challengeId: "1",
        },
      }).then((res) => {
        this.setDailyChallenge(res.data);
      });
    },
    setDailyChallenge(info) {
      this.dailyChallenge.imageURL = info.challengeImage;
      this.dailyChallenge.challengeName = info.challengeName;
      this.dailyChallenge.challengeDescription = info.challengeContent;
    },
    // 기업연계 챌린지 정보 불러오기
    callSponseredChallenge() {
      axios({
        url: "/challenge/",
        method: "GET",
        params: {
          challengeId: "2",
        },
      }).then((res) => {
        this.setSponseredChallenge(res.data);
      });
    },
    setSponseredChallenge(info) {
      this.sponseredChallenge.imageURL = info.challengeImage;
      this.sponseredChallenge.challengeName = info.challengeName;
    },
    // 추천 챌린지 정보 불러오기
    callRecommendChallenges() {
      axios({
        url: "/challenge/all",
        method: "GET",
      }).then((res) => {
        this.recommendChallenges = res.data;
      });
    },
  },
};
</script>
