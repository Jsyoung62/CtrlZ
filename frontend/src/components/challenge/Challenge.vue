<template>
  <div class="challenge">
    <div class="contents"></div>

    <h4 class="category">
      {{ category }}
    </h4>

    <div class="titleBox">
      <h3 class="title">
        {{ title }}
      </h3>
      <p class="participants">
        {{ participants }}
      </p>
    </div>

    <h4 class="description">
      {{ description }}
    </h4>
  </div>
</template>

<script>
import "../css/challenge/challengeList.scss";

export default {
  name: "Challenge",
  props: {
    category: {
      type: String,
      default: "",
      required: false,
    },
    title: {
      type: String,
      default: "",
      required: false,
    },
    participants: {
      type: String,
      default: "",
      required: false,
    },
    description: {
      type: String,
      default: "",
      required: false,
    },
  },
};
</script>
