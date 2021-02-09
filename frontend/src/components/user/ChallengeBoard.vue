<template>
  <div class="challenge">
    <div class="boardHeader">
      <h1>진행중인 챌린지</h1>
      <p>({{ challenges.length }}개)</p>
      <span class="material-icons" @click="onClickExpand">
        {{ expand }}
      </span>
    </div>

    <div v-show="showBoard" class="challengeBoard">
      <div v-for="(challenge, index) in challenges" :key="challenge.challengeId">
        <p class="challengeName">
          {{ challenge.challengeName }}
        </p>
        <div class="leaf">
          <LeafOn v-for="index_a in challenge.achived" :key="index_a" />
        </div>
        <div class="leaf">
          <LeafOff v-for="index_na in challenge.nonAchived" :key="index_na" />
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
  data: () => {
    return {
      expand: "expand_less",
      showBoard: true,
      challenges: [
        {
          challengeId: 1,
          challengeName: "시작이 반이다",
          nonAchived: 2,
          achived: 1,
        },
        {
          challengeId: 2,
          challengeName: "플라스틱 다이어트",
          nonAchived: 6,
          achived: 3,
        },
      ],
    };
  },
  methods: {
    onClickExpand() {
      this.expand = this.expand === "expand_less" ? "expand_more" : "expand_less";
      this.showBoard = !this.showBoard;
    },
  },
};
</script>
