<template>
  <div class="inProgressChallenge">
    <Header title="진행중인 챌린지" />
    <Navigation />

    <div class="thumbnailWrapper">
      <img :src="challenge.challengeImage" class="thumbnail" />
      <progress :value="progress" max="100"></progress>
    </div>
    <div class="contentsContainer">
      <ChallengeTitle
        :title="challenge.challengeName"
        :level="challenge.levelId"
        :challenge-type="challenge.challengeType"
      />

      <div class="status">
        <div class="wrapper">
          <p class="title">
            미션 횟수
          </p>
          <p>{{ challenge.challengeMissionTotal }}회</p>
        </div>

        <div class="wrapper">
          <p class="title">
            진행률
          </p>
          <p>{{ progress }}%</p>
        </div>

        <button @click="handleDetailClick">
          챌린지 상세보기
        </button>
      </div>

      <Missions
        :mission-total="challenge.challengeMissionTotal"
        :challenge-id="challengeId"
        :missions="missions"
      />
    </div>
  </div>
</template>
<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import ChallengeTitle from "@/components/challenge/ChallengeTitle.vue";
import Missions from "@/components/challenge/Missions";
import "@/components/css/challenge/inProgressChallenge.scss";
import axios from "axios";

axios.defaults.baseURL = "http://i4a202.p.ssafy.io:8888";

export default {
  name: "InProgressChallenge",
  components: {
    Header,
    Navigation,
    ChallengeTitle,
    Missions,
  },
  data: () => {
    return {
      challenge: "",
      missions: "",
      challengeId: "3",
      completedMissions: [],
    };
  },
  computed: {
    progress() {
      return Math.floor(
        (this.completedMissions.length / this.challenge.challengeMissionTotal) * 100
      );
    },
  },
  created() {
    axios({
      url: "/challenge/",
      method: "GET",
      params: {
        challengeId: this.challengeId,
      },
    }).then((response) => {
      this.challenge = response.data;
    });
    axios({
      url: "/mission/",
      method: "GET",
      params: {
        challengeId: this.challengeId,
      },
    }).then((response) => {
      this.missions = response.data;
    });
    axios({
      url: "/post/find/challenge/user",
      method: "GET",
      params: {
        challengeId: this.challengeId,
        userId: this.$store.state.userInfo.userId,
      },
    }).then((response) => {
      this.completedMissions = response.data;
    });
  },
  methods: {
    handleDetailClick() {
      this.$router.push({
        name: "ChallengeDetail",
        params: {
          challengeId: this.challengeId,
        },
      });
    },
  },
};
</script>
