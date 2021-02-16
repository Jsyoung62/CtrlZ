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
      <Missions />
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
      userId: "",
      challengeId: "",
      challenge: {},
      challengeStatus: {},
    };
  },
  computed: {
    progress() {
      return Math.floor(
        (this.challengeStatus.challengeMissionCurrent / this.challenge.challengeMissionTotal) * 100
      );
    },
  },
  created() {
    this.userId = this.$store.state.userInfo.userId;
    this.challengeId = this.$route.params.challengeId;

    // 데일리미션은 데일리미션 상세보기 페이지로 이동
    if (this.challengeId === "1") {
      this.$router.push({ name: "DailyMission" });
    }

    this.getChallengeInfo();
    this.getUserChallengeStatus();
  },
  methods: {
    // 챌린지 정보 불러오기
    getChallengeInfo() {
      this.$axios({
        url: "/challenge/",
        method: "GET",
        params: {
          challengeId: this.challengeId,
        },
      })
        .then((response) => {
          this.challenge = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 현재 사용자의 해당 챌린지 참여 현황 조회
    getUserChallengeStatus() {
      this.$axios({
        url: `/challenge/status/${this.challengeId}/${this.userId}`,
        method: "GET",
      })
        .then((response) => {
          this.challengeStatus = response.data;
        })
        .catch((error) => {
          console.error(error);
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
