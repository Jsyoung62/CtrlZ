<template>
  <div class="board">
    <div class="boardHeader">
      <h1>{{ title }}</h1>
      <p>({{ length }}개)</p>
      <span class="material-icons" @click="onClickExpand">
        {{ expand }}
      </span>
    </div>
    <div v-show="showBoard" class="boardContents" :class="type">
      <div v-for="badge in badges" v-show="type === 'badge'" :key="badge.badgeId">
        <Badge :name="badge.badgeName" :date="badge.challengeFinishDate" :icon="badge.badgeImage" />
      </div>
      <div
        v-for="(challenge, index) in challenges"
        v-show="type === 'challenge'"
        :key="challenge.challengeId"
        :class="type"
      >
        <Challenge
          :name="challenge.challengeName"
          :total="challenge.challengeMissionTotal"
          :achived="challenge.achivedMission"
        />
        <div v-if="index !== length - 1" class="line"></div>
      </div>
    </div>
  </div>
</template>
<script>
import Badge from "./Badge.vue";
import Challenge from "./Challenge.vue";
import "../css/main/board.scss";

export default {
  name: "Board",
  components: {
    Badge,
    Challenge,
  },
  props: {
    title: {
      type: String,
      required: true,
    },
    badges: {
      type: Array,
      required: false,
      default() {
        return [];
      },
    },
    challenges: {
      type: Array,
      required: false,
      default() {
        return [];
      },
    },
  },
  data: () => {
    return {
      expand: "expand_less",
      showBoard: true,
    };
  },
  computed: {
    type() {
      return this.badges.length === 0 ? "challenge" : "badge";
    },
    length() {
      return this.type === "challenge" ? this.challenges.length : this.badges.length;
    },
  },
  methods: {
    onClickExpand() {
      this.expand = this.expand === "expand_less" ? "expand_more" : "expand_less";
      this.showBoard = !this.showBoard;
    },
  },
};
</script>
