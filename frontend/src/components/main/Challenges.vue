<template>
  <div class="recommendChallenges">
    <p class="topic">
      추천 챌린지
    </p>
    <div class="challengeWrapper">
      <div v-for="(challenge, index) in challenges" :key="index" class="challenge">
        <img src="@/assets/ch_example.jpg" class="thumbnail" alt="thumbnail" />
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
  data: () => {
    return {
      challenges: [
        {
          level: "입문",
          challengeType: "생활",
          challengeName: "시작이 반이다",
          participants: 13723,
        },
        {
          level: "고급",
          challengeType: "생활",
          challengeName: "플라스틱 다이어트",
          participants: 2988,
        },
      ],
    };
  },
};
</script>
