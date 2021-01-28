<template>
  <div class="sponsoredChallenge">
    <Challenge
      category="Ctrl Z x RunDay"
      title="줍고 살리고 달리고"
      participants="1,234명 도전 중"
    />
  </div>
</template>

<script>
import Challenge from "./Challenge.vue";

export default {
  name: "SponsoredChallenge",
  components: {
    Challenge,
  },
};
</script>
