<template>
  <div class="recommendChallenges">
    <p class="topic">
      추천 챌린지
    </p>
    <div class="challengeWrapper">
      <div v-for="(challenge, index) in challenges" :key="index" class="challenge">
        <img :src="challenge.challengeImage" class="thumbnail" alt="thumbnail" />
        <p class="category">
          <span>#{{ challenge.level }}</span>
          <span>#{{ challenge.challengeType }}</span>
        </p>
        <p class="challengeName">
          {{ challenge.challengeName }}
        </p>
        <p class="participants">
          {{ challenge.participants | numberWithComma }}
          명 도전 중
        </p>
      </div>
    </div>
  </div>
</template>
<script>
import "@/components/css/main/challenges.scss";

export default {
  name: "Challenges",
  filters: {
    numberWithComma(num) {
      return String(num).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  props: {
    challenges: {
      type: Array,
      required: true,
    },
  },
};
</script>
