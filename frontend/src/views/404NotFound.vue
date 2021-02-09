<template>
  <div class="error">
    <p class="message">
      404 Page not found!
    </p>
    <Logo />
    Ctrl Z your page
  </div>
</template>
<script>
import { Logo } from "@/assets/index.js";
import "@/components/css/error.scss";

export default {
  name: "NotFound",
  components: {
    Logo,
  },
};
</script>
