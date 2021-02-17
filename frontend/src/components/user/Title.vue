<template>
  <div class="pageTitle">
    <Logo @click="handleLogoClick" />
    <h1 class="title">
      {{ title }}
    </h1>
  </div>
</template>
<script>
import { Logo } from "@/assets/index.js";

export default {
  name: "PageTitle",
  components: {
    Logo,
  },
  props: {
    title: {
      type: String,
      required: true,
    },
  },
  methods: {
    handleLogoClick() {
      this.$router.go(-1);
    },
  },
};
</script>
