<template>
  <div class="header">
    <span class="material-icons" @click="open">
      dehaze
    </span>
    <h1 class="title">
      {{ title }}
    </h1>
  </div>
</template>

<script>
import "../css/header.scss";

export default {
  name: "Header",
  props: {
    title: {
      type: String,
      default: "",
      required: false,
    },
  },
  methods: {
    open() {
      document.getElementsByClassName("sideNav")[0].style.transform = "translateX(320%)";
    },
  },
};
</script>
