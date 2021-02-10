<template>
  <div class="header">
    <h1 class="title">
      {{ title }}
    </h1>
  </div>
</template>

<script>
import "@/components/css/header.scss";

export default {
  name: "Header",
  props: {
    title: {
      type: String,
      default: "",
      required: false,
    },
  },
};
</script>
