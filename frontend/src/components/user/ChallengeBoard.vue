<template>
  <div class="challenge">
    <!-- <Board title="진행중인 챌린지" :challenges="challenges" /> -->
  </div>
</template>
<script>
// import Board from "./Board.vue";
export default {
  name: "ChallengeBoard",
  components: {
    // Board,
  },
  data: () => {
    return {
      challenges: [
        {
          challengeId: 1,
          challengeName: "텀블러 사용하기",
          challengeMissionTotal: 3,
          achivedMission: 1,
        },
        {
          challengeId: 2,
          challengeName: "에코백 사용하기",
          challengeMissionTotal: 5,
          achivedMission: 3,
        },
      ],
    };
  },
};
</script>
