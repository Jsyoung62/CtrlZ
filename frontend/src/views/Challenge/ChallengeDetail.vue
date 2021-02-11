<template>
  <div class="challengeDetail">
    <Header title="챌린지 상세보기" />
    <Navigation />

    <div class="thumbnailWrapper">
      <img src="@/assets/mission.png" class="thumbnail" />
      <progress v-show="isInprogress || isAchived" :value="progress" max="100"></progress>
    </div>

    <div class="challengeDetailContainer">
      <ChallengeTitle title="도전, 에코 패셔니스타" level="입문" challenge-type="일상" />
      <div class="countWrapper">
        <div>
          {{ achived | numberWithComma }}
          명
          <span>완료</span>
        </div>
        <div>
          {{ participants | numberWithComma }}
          명
          <span>도전중</span>
        </div>
      </div>

      <p class="description">
        {{ description }}
      </p>

      <p v-if="isInprogress" class="status">
        60%진행중
      </p>
      <p v-else-if="isAchived" class="status">
        {{ 3220 | numberWithComma }}
        번째로 완료했어요!
      </p>
      <button v-else class="startButton" @click="handleStartButton">
        {{ (achived + 1) | numberWithComma }}
        번째 참여자 되기
      </button>
    </div>

    <ChallengeFeed :images="images" />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import ChallengeTitle from "@/components/challenge/ChallengeTitle.vue";
import ChallengeFeed from "@/components/challenge/ChallengeFeed.vue";
import "@/components/css/challenge/challengeDetail.scss";

export default {
  name: "ChallengeDetail",
  components: {
    Header,
    Navigation,
    ChallengeTitle,
    ChallengeFeed,
  },
  filters: {
    numberWithComma(num) {
      return String(num).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  data: () => {
    return {
      achived: 13512,
      participants: 3592,
      description:
        "패션 분야의 환경 보호를 실천하고, 모두가 인정하는 에코 패셔니스타가 되어보세요!",
      images: [
        "http://i4a202.p.ssafy.io/img/dailymission.png",
        "http://i4a202.p.ssafy.io/img/dailymission.png",
        "http://i4a202.p.ssafy.io/img/dailymission.png",
        "http://i4a202.p.ssafy.io/img/dailymission.png",
      ],
      isInprogress: false,
      isAchived: false,
      challengeMissionTotal: 3,
      challengeClosed: 2,
    };
  },
  computed: {
    progress() {
      return Math.floor((this.challengeClosed / this.challengeMissionTotal) * 100);
    },
  },
  methods: {
    handleStartButton() {
      console.log("챌린지 참여");
    },
  },
};
</script>
