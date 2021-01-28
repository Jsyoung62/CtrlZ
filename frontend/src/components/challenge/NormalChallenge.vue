<template>
  <div class="normalChallenge">
    <Challenge
      category="전체 챌린지 목록"
      theme="#패션"
      title="에코패셔니스타"
      participants="7,234명 도전 중"
    />
    <Challenge theme="#음식" title="친환경미식가" participants="7,234명 도전 중" />
    <Challenge theme="#생활" title="용기내챌린지" participants="7,234명 도전 중" />
    <Challenge theme="#활동" title="친환경활동가" participants="7,234명 도전 중" />
  </div>
</template>

<script>
import Challenge from "./Challenge.vue";

export default {
  name: "NormalChallenge",
  components: {
    Challenge,
  },
};
</script>
