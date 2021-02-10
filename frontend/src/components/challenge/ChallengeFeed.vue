<template>
  <div class="challengeFeed">
    <div v-for="(image, index) in images" :key="index" class="feed">
      <img :src="image" />
    </div>
  </div>
</template>

<script>
export default {
  name: "ChallengeFeed",
  props: {
    images: {
      type: Array,
      required: true,
    },
  },
};
</script>
