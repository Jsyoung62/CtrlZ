<template>
  <div class="inProgressChallenge">
    <Header title="진행중인 챌린지" />
    <Navigation />

    <div class="thumbnailWrapper">
      <img src="@/assets/mission.png" class="thumbnail" />
      <progress :value="progress" max="100"></progress>
    </div>
    <div class="contentsContainer">
      <ChallengeTitle :title="challengeName" :level="level" :challenge-type="challengeType" />

      <div class="status">
        <div class="wrapper">
          <p class="title">
            미션 횟수
          </p>
          <p>{{ challengeMissionTotal }}회</p>
        </div>

        <div class="wrapper">
          <p class="title">
            진행률
          </p>
          <p>{{ progress }}%</p>
        </div>

        <button>챌린지 상세보기</button>
      </div>

      <Missions :mission-total="challengeMissionTotal" />
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
      challengeName: "시작이 반이다",
      challengeType: "일상",
      level: "입문",
      challengeMissionTotal: 3,
      challengeClosed: 2,
    };
  },
  computed: {
    progress() {
      return Math.floor((this.challengeClosed / this.challengeMissionTotal) * 100);
    },
  },
};
</script>
