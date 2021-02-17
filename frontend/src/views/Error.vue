<template>
  <div class="error">
    <p class="message">
      {{ message }}
    </p>
    <Logo />
    Ctrl Z your page
  </div>
</template>
<script>
import { Logo } from "@/assets/index.js";
import "@/components/css/error.scss";

export default {
  name: "ErrorPage",
  components: {
    Logo,
  },
  data: () => {
    return {
      message: "Sorry :(\nSomething went wrong.",
    };
  },
};
</script>
