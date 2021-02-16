<template>
  <div class="day">
    <router-link to="/challenge/daily">
      <img :src="thumbnail" class="thumbnail" />
      <div class="title">
        {{ title }}
      </div>
    </router-link>
  </div>
</template>
<script>
import "@/components/css/main/day.scss";
export default {
  name: "Day",
  props: {
    title: {
      type: String,
      required: true,
    },
    thumbnail: {
      type: String,
      required: true,
    },
  },
};
</script>
