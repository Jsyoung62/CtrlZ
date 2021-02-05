<template>
  <div class="normalChallenge">
    <h2 class="topic">
      전체 챌린지
    </h2>
    <div class="listBox">
      <Challenge category="#패션" title="에코패셔니스타" participants="7,234명 도전 중" />
      <Challenge category="#음식" title="친환경 미식가" participants="7,234명 도전 중" />
      <Challenge category="#생활" title="용기내 챌린지" participants="7,234명 도전 중" />
      <Challenge category="#활동" title="친환경 활동가" participants="7,234명 도전 중" />
      <Challenge category="#입문 #생활" title="텀블러의 제왕" participants="7,234명 도전 중" />
      <Challenge category="#입문 #패션" title="에코백 마스터" participants="7,234명 도전 중" />
    </div>
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
