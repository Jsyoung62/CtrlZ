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
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import Categories from "@/components/challenge/Categories.vue";
import "@/components/css/challenge/index.scss";
import "@/components/css/challenge/challengeList.scss";

export default {
  name: "ChallengeList",
  components: {
    Header,
    Navigation,
    Categories,
  },
  data: () => {
    return {
      challenges: [], // 현재 화면에 보여지는 챌린지 데이터
      dailyChallenges: [],
      foodChallenges: [],
      fashionChallenges: [],
      activityChallenges: [],
    };
  },
  created() {
    this.dailyChallenges = this.getChallenges("일상", "dailyChallenges");
    this.foodChallenges = this.getChallenges("음식", "foodChallenges");
    this.fashionChallenges = this.getChallenges("패션", "fashionChallenges");
    this.activityChallenges = this.getChallenges("활동", "activityChallenges");
  },
  methods: {
    // 챌린지 불러오기
    getChallenges(type, challengeName) {
      this.$axios({
        url: "/challenge/type",
        method: "GET",
        params: {
          challengeType: type,
        },
      })
        .then((response) => {
          const style = this.setStyle(type);
          this[challengeName] = response.data.map((challenge) => {
            return { ...challenge, style };
          });
          this.challenges = this.challenges.concat(this[challengeName]);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 챌린지 유형별로 스타일 구분
    setStyle(type) {
      if (type === "일상") {
        return "yellow";
      }
      if (type === "음식") {
        return "purple";
      }
      if (type === "패션") {
        return "pink";
      }
      if (type === "활동") {
        return "blue";
      }
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
