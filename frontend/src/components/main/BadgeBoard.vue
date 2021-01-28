<template>
  <div class="badgeBoard">
    <Board title="나의 뱃지함" :badges="badegs" />
  </div>
</template>
<script>
import Board from "./Board.vue";

export default {
  name: "BadgeBoard",
  components: {
    Board,
  },
  data: () => {
    return {
      badegs: [
        {
          badgeId: 1,
          badgeName: "텀블러브유",
          badgeImage: "@/assets/badge.png",
          challengeFinishDate: "2020.1.28",
        },
        {
          badgeId: 2,
          badgeName: "명품에코백",
          badgeImage: "@/assets/badge.png",
          challengeFinishDate: "2020.1.28",
        },
      ],
    };
  },
};
</script>
