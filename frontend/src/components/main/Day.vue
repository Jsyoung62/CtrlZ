<template>
  <div class="day">
    <img src="@/assets/ch_example.jpg" class="post" />
    <div class="title">
      {{ title }}
    </div>
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
  },
};
</script>
