<template>
  <div class="inProgressChallenge">
    <Header title="진행중인 챌린지" left-icon="chevron_left" />
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
        :missions="missions"
        :posts="completedMissions"
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
      challenge: {},
      missions: [],
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
    const challengeId = this.$route.params.challengeId;
    this.getChallengeInfo(challengeId);
    this.getMissions(challengeId);
    this.getFeed(challengeId);
  },
  methods: {
    // 챌린지 정보 불러오기
    getChallengeInfo(challengeId) {
      this.$axios({
        url: "/challenge/",
        method: "GET",
        params: {
          challengeId,
        },
      })
        .then((response) => {
          this.challenge = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 미션 정보 불러오기
    getMissions(challengeId) {
      this.$axios({
        url: "/mission/",
        method: "GET",
        params: {
          challengeId,
        },
      })
        .then((response) => {
          this.missions = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 챌린지에 모든 사용자가 올린 게시글 정보 불러오기
    getFeed(challengeId) {
      this.$axios({
        url: "/post/find/challenge/user",
        method: "GET",
        params: {
          challengeId,
          userId: this.$store.state.userInfo.userId,
        },
      })
        .then((response) => {
          if (response.data !== "") {
            this.completedMissions = response.data;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    handleDetailClick() {
      this.$router.push({
        name: "ChallengeDetail",
        params: {
          challengeId: this.$route.params.challengeId,
        },
      });
    },
  },
};
</script>
