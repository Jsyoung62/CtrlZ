<template>
  <div class="pageTitle">
    <Logo />
    <h1 class="title">
      {{ title }}
    </h1>
  </div>
</template>
<script>
import Logo from "@/assets/logo.vue";

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
};
</script>
