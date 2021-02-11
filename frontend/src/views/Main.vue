<template>
  <div class="root">
    <Splash v-if="this.$store.state.splash" />

    <div v-else class="main">
      <Header title="Ctrl Z" />
      <Navigation />

      <router-link to="/challenge/daily">
        <Mission
          topic="데일리 미션"
          :title="dailyChallengeName"
          :participants="dailyChallengeParticipants"
          description="매일 데일리 미션을 수행하며 생활 속에서 꾸준히 제로웨이스트를 실천해보세요!"
          :challenge-image="dailyChallengeImage"
        />
      </router-link>

      <Mission
        topic="환경부와 런데이가 함께하는"
        :title="businessChallengeName"
        :participants="businessChallengeParticipants"
        description=""
        :challenge-image="businessChallengeImage"
      />
      <Challenges :challenges="challenges" />
    </div>
  </div>
</template>

<script>
import Splash from "@/components/main/Splash.vue";
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import Mission from "@/components/main/Mission.vue";
import Challenges from "@/components/main/Challenges.vue";
import "@/components/css/main/index.scss";
import axios from "axios";

axios.defaults.baseURL = "http://i4a202.p.ssafy.io:8888";

export default {
  name: "Main",
  components: {
    Splash,
    Header,
    Navigation,
    Mission,
    Challenges,
  },
  data: () => {
    return {
      businessChallengeName: "",
      businessChallengeImage: "",
      businessChallengeParticipants: "",
      dailyChallengeImage: "",
      dailyChallengeName: "",
      dailyChallengeParticipants: "",
      challenges: [],
    };
  },
  created() {
    axios({
      url: "/challenge/?challengeId=challenge_04",
      method: "GET",
    }).then((res) => {
      this.dailyChallengeName = res.data.challengeName;
      this.dailyChallengeImage = res.data.challengeImage;
      this.dailyChallengeParticipants = res.data.participants;
    });
    axios({
      url: "/challenge/?challengeId=challenge_03",
      method: "GET",
    }).then((res) => {
      this.businessChallengeName = res.data.challengeName;
      this.businessChallengeImage = res.data.challengeImage;
      this.businessChallengeParticipants = res.data.participants;
    });
    axios({
      url: "/challenge/all",
      method: "GET",
    }).then((res) => {
      this.challenges = res.data;
    });
  },
  mounted() {
    if (this.$store.state.splash) {
      window.setTimeout(() => {
        this.$store.commit("ENDSPLASH");
        this.$router.push("/zbti");
      }, 3500);
    }
  },
};
</script>
