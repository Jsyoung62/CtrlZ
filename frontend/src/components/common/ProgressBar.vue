<template>
  <div class="progressBar">
    <div class="front" :style="{ width: width }"></div>
    <div class="back"></div>
  </div>
</template>
<script>
import "@/components/css/progressBar.scss";

export default {
  name: "ProgressBar",
  props: {
    progress: {
      type: Number,
      required: true,
    },
  },
  computed: {
    width() {
      return `${this.progress}%`;
    },
  },
};
</script>
