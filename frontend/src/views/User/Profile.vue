<template>
  <div class="profile">
    <Profileboard />
    <Zscore />
    <ChallengeBoard />
  </div>
</template>

<script>
import ChallengeBoard from "@/components/user/ChallengeBoard.vue";
import Profileboard from "@/components/user/profileboard.vue";
import Zscore from "@/components/user/zscore.vue";
import "@/components/css/user/profile.scss";

export default {
  name: "Profile",
  components: {
    ChallengeBoard,
    Profileboard,
    Zscore,
  },
};
</script>
