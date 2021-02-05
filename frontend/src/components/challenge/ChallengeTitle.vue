<template>
  <div class="challengeTitle">
    <div class="category">
      <p>#{{ level }}</p>
      <p>#{{ challengeType }}</p>
    </div>

    <h1 class="title">
      {{ title }}
    </h1>
  </div>
</template>
<script>
import "@/components/css/challenge/challengeTitle.scss";

export default {
  name: "ChallengeTitle",
  props: {
    title: {
      type: String,
      required: true,
    },
    level: {
      type: String,
      required: true,
    },
    challengeType: {
      type: String,
      required: true,
    },
  },
};
</script>
