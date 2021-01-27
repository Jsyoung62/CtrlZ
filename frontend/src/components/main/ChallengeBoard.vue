<template>
  <div class="challenge">
    <Board title="진행중인 챌린지" />
  </div>
</template>
<script>
import Board from "./Board.vue";

export default {
  name: "ChallengeBoard",
  components: {
    Board,
  },
};
</script>
