<template>
  <div class="challenge">
    <div v-if="category" class="categoryBox">
      <h2 v-if="category" class="category">
        {{ category }}
      </h2>
    </div>

    <div v-if="title" class="contentsBox">
      <div class="contents"></div>
      <div class="themeBox">
        <h4 class="theme">
          {{ theme }}
        </h4>
      </div>

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
    theme: {
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
