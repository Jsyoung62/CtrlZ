<template>
  <div class="challengeDetail">
    <Header title="챌린지 상세보기" />
    <button @click="startChallenge">
      (participants + 1)번째 참여자 되기
    </button>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import "@/components/css/challenge/challengeDetail.scss";

export default {
  name: "ChallengeDetail",
  components: {
    Header,
  },
  methods: {
    startChallenge() {
      console.log("버튼 동작");
    },
  },
};
</script>
