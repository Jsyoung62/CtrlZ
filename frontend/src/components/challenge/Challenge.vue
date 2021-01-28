<template>
  <div class="challenge">
    <h3 class="category">
      {{ category }}
    </h3>
    <div class="contents"></div>
    <h4 class="theme">
      {{ theme }}
    </h4>
    <h2 class="title">
      {{ title }}
    </h2>
    <p class="participants">
      {{ participants }}
    </p>
  </div>
</template>

<script>
import "../css/challenge/challengelist.scss";

export default {
  name: "Challenge",
  props: {
    category: {
      type: String,
      default: "",
      required: false,
    },
    theme: {
      type: String,
      default: "",
      required: false,
    },
    title: {
      type: String,
      required: true,
    },
    participants: {
      type: String,
      default: "",
      required: false,
    },
  },
};
</script>
