<template>
  <div class="mission" @click="handleClick">
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
    handleClick() {
      this.$router.push("/challenge/detail");
    },
  },
};
</script>
