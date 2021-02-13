<template>
  <div class="challengeList">
    <Header title="전체 챌린지" />
    <Navigation />

    <Categories
      :daily-length="dailyChallenges.length"
      :food-length="foodChallenges.length"
      :fashion-length="fashionChallenges.length"
      :activity-length="activityChallenges.length"
      @division="handleDivision"
    />
    <div class="challengeContainer">
      <div
        v-for="challenge in challenges"
        :key="challenge.challengeId"
        class="challenge"
        @click="handleChallengeClick(challenge.challengeId)"
      >
        <div class="side" :class="challenge.style"></div>

        <img :src="challenge.challengeImage" class="thumbnail" />
        <div class="infoWrapper">
          <p class="category">
            <span>#{{ challenge.levelId }}</span>
            <span>#{{ challenge.challengeType }}</span>
          </p>
          <p class="challengeName">
            {{ challenge.challengeName }}
          </p>
          <p class="participants">
            {{ challenge.participants }}
            명 도전 중
          </p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import Categories from "@/components/challenge/Categories.vue";
import "@/components/css/challenge/index.scss";
import "@/components/css/challenge/challengeList.scss";

axios.defaults.baseURL = "http://i4a202.p.ssafy.io:8888";

export default {
  name: "ChallengeList",
  components: {
    Header,
    Navigation,
    Categories,
  },
  data: () => {
    return {
      challenges: [],
      dailyChallenges: [],
      foodChallenges: [],
      fashionChallenges: [],
      activityChallenges: [],
    };
  },
  created() {
    // 전체 챌린지 데이터 조회
    axios({
      url: "/challenge/all",
      method: "GET",
      params: {
        challengeId: this.challengeId,
      },
    })
      .then((response) => {
        this.challenges = response.data.map((challenge) => {
          let style = "";
          if (challenge.challengeType === "일상") {
            style = "yellow";
          } else if (challenge.challengeType === "음식") {
            style = "purple";
          } else if (challenge.challengeType === "패션") {
            style = "pink";
          } else if (challenge.challengeType === "활동") {
            style = "blue";
          }
          return { ...challenge, style };
        });

        this.divisionChallenges();
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    // 유형별로 챌린지 나누기
    divisionChallenges() {
      this.dailyChallenges = this.challenges.filter(
        (challenge) => challenge.challengeType === "일상"
      );

      this.foodChallenges = this.challenges.filter(
        (challenge) => challenge.challengeType === "음식"
      );

      this.fashionChallenges = this.challenges.filter(
        (challenge) => challenge.challengeType === "패션"
      );

      this.activityChallenges = this.challenges.filter(
        (challenge) => challenge.challengeType === "활동"
      );
    },
    // 해당 챌린지 상세보기로 이동
    handleChallengeClick(challengeId) {
      this.$router.push({
        name: "ChallengeDetail",
        params: {
          challengeId,
        },
      });
    },
    // 카테고리별로 분류
    handleDivision(type) {
      this.challenges = this[type];
    },
  },
};
</script>
