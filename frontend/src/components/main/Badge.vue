<template>
  <div class="badge">
    <img src="@/assets/badge.png" />
    <h3>{{ name }}</h3>
    <p>{{ date }}</p>
  </div>
</template>
<script>
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
    icon: {
      type: String,
      required: true,
    },
  },
};
</script>
