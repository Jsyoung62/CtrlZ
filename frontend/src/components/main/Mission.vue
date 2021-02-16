<template>
  <div class="mission" @click="handleChallengeClick">
    <img :src="challenge.challengeImage" class="thumbnail" />
    <div class="title">
      {{ challenge.challengeName }}
    </div>
  </div>
</template>
<script>
import "@/components/css/main/mission.scss";

export default {
  name: "Mission",
  props: {
    challenge: {
      type: Object,
      required: true,
    },
  },
  methods: {
    handleChallengeClick() {
      this.$router.push({
        name: "ChallengeDetail",
        params: {
          challengeId: this.challenge.challengeId,
        },
      });
    },
  },
};
</script>
