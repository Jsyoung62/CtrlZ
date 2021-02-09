<template>
  <div class="main">
    <Header title="Ctrl Z" />
    <Navigation />

    <Mission
      topic="데일리 미션"
      :title="dailyChallengeName"
      participants="3123"
      description="매일 데일리 미션을 수행하며 생활 속에서 꾸준히 제로웨이스트를 실천해보세요!"
      :challenge-image="dailyChallengeImage"
    />
    <Mission
      topic="환경부와 런데이가 함께하는"
      :title="businessChallengeName"
      participants="17211"
      description=""
      :challenge-image="businessChallengeImage"
    />
    <Challenges :challenges="challenges" />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import Mission from "@/components/main/Mission.vue";
import Challenges from "@/components/main/Challenges.vue";
import "@/components/css/main/index.scss";
import axios from "axios";

axios.defaults.baseURL = "https://i4a202.p.ssafy.io:8888";

export default {
  name: "Main",
  components: {
    Header,
    Navigation,
    Mission,
    Challenges,
  },
  data: () => {
    return {
      businessChallengeName: "",
      businessChallengeImage: "",
      dailyChallengeImage: "",
      dailyChallengeName: "",
      participants: "",
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
    });
    axios({
      url: "/challenge/?challengeId=challenge_03",
      method: "GET",
    }).then((res) => {
      this.businessChallengeName = res.data.challengeName;
      this.businessChallengeImage = res.data.challengeImage;
    });
    axios({
      url: "/challenge/all",
      method: "GET",
    }).then((res) => {
      this.challenges = res.data;
      console.log(this.challenges);
    });
  },
};
</script>
