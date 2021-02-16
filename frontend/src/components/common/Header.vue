<template>
  <div class="header">
    <span class="material-icons" @click="handleBackClick">
      {{ leftIcon }}
    </span>
    <h1 class="title">
      {{ title }}
    </h1>
    <span class="material-icons" @click="handleRightIconClick">
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
  methods: {
    handleBackClick() {
      this.$router.go(-1);
    },
    handleRightIconClick() {
      console.log("RIGHT ICON");
    },
  },
};
</script>
