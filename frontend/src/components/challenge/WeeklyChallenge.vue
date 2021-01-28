<template>
  <div class="weeklyChallenge">
    <Challenge
      category="이번 주의 데일리 미션"
      title="2월 3주차 데일리 미션"
      participants="2,345명 도전 중"
    />
  </div>
</template>

<script>
import Challenge from "./Challenge.vue";

export default {
  name: "WeeklyChallenge",
  components: {
    Challenge,
  },
};
</script>
