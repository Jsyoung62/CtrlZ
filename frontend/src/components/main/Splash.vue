<template>
  <div class="splash">
    <div class="logo">
      <Logo />
    </div>
    <p>Ctrl Z</p>
  </div>
</template>
<script>
import { Logo } from "@/assets/index.js";
import "@/components/css/main/splash.scss";
export default {
  name: "Splash",
  components: {
    Logo,
  },
};
</script>
