<template>
  <div class="mission">
    <!-- <img :src="challengeImage" class="thumbnail" /> -->
    <img src="@/assets/ch_example.jpg" class="thumbnail" />
  </div>
</template>
<script>
import "@/components/css/main/mission.scss";

export default {
  name: "Mission",
  props: {
    challengeImage: {
      type: String,
      required: true,
    },
  },
};
</script>
