<template>
  <div class="header">
    <span class="material-icons">
      {{ leftIcon }}
    </span>
    <h1 class="title">
      {{ title }}
    </h1>
    <span class="material-icons">
      {{ rightIcon }}
    </span>
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
    leftIcon: {
      type: String,
      default: "",
      required: false,
    },
    rightIcon: {
      type: String,
      default: "",
      required: false,
    },
  },
};
</script>
