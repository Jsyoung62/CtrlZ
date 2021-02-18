<template>
  <div class="challenge">
    <div class="boardHeader">
      <h1>완료된 챌린지</h1>
      <p>({{ challenges.length }}개)</p>
      <span class="material-icons" @click="onClickExpand">
        {{ expand }}
      </span>
    </div>

    <div v-show="showBoard" class="challengeBoard">
      <div
        v-for="(challenge, index) in challenges"
        :key="challenge.challengeId"
        @click="handleChallengeClick(challenge.challengeId)"
      >
        <p class="challengeName">
          {{ challenge.challengeName }}
        </p>
        <div class="leaf">
          <LeafOn v-for="index_a in challenge.challengeMissionCurrent" :key="index_a" />
        </div>
        <div class="leaf">
          <LeafOff v-for="index_na in challenge.missionNonAchieve" :key="index_na" />
        </div>

        <div v-if="index !== challenges.length - 1" class="line"></div>
      </div>
    </div>
  </div>
</template>
<script>
import { LeafOn, LeafOff } from "@/assets/index";
import "@/components/css/user/challengeBoard.scss";

export default {
  name: "ChallengeBoard",
  components: {
    LeafOn,
    LeafOff,
  },
  props: {
    challenges: {
      type: Array,
      required: true,
    },
  },
  data: () => {
    return {
      expand: "expand_more",
      showBoard: false,
      nonAchieve: 0,
    };
  },
  methods: {
    onClickExpand() {
      this.expand = this.expand === "expand_less" ? "expand_more" : "expand_less";
      this.showBoard = !this.showBoard;
    },
    handleChallengeClick(challengeId) {
      this.$router.push({
        name: "InProgressChallenge",
        params: {
          challengeId,
        },
      });
    },
  },
};
</script>
