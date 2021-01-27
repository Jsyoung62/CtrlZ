<template>
  <div class="badge">
    <img :src="BadgeImage" />
    <h3>{{ name }}</h3>
    <p>{{ date }}</p>
  </div>
</template>
<script>
import BadgeImage from "@/assets/badge.png";

export default {
  name: "Badge",
  props: {
    name: {
      type: String,
      required: true,
    },
    date: {
      type: String,
      required: true,
    },
  },
  data: () => {
    return {
      BadgeImage,
    };
  },
};
</script>
