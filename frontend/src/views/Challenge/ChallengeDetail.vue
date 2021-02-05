<template>
  <div class="challengeDetail">
    <Header title="챌린지 상세보기" />
    <Challenge
      theme="#패션"
      title="에코패셔니스타"
      participants="1,234명"
      description="패션 분야의 환경 보호를 실천하고, 모두가 인정하는 에코 패셔니스타가 되어보세요!"
    />
    <button @click="startChallenge">
      (participants + 1)번째 참여자 되기
    </button>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Challenge from "@/components/challenge/Challenge.vue";
import "@/components/css/challenge/challengeDetail.scss";

export default {
  name: "ChallengeDetail",
  components: {
    Header,
    Challenge,
  },
  methods: {
    startChallenge() {
      console.log("버튼 동작");
    },
  },
};
</script>
