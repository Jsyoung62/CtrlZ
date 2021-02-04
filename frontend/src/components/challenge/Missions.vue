<template>
  <div class="missions">
    <img src="@/assets/ch_example.jpg" class="mission" />
    <img src="@/assets/ch_example.jpg" class="mission" />

    <div class="mission locked">
      <p>3</p>
    </div>
  </div>
</template>
<script>
import "@/components/css/challenge/missions.scss";
export default {
  name: "Missions",
  props: {
    missionTotal: {
      type: Number,
      required: true,
    },
  },
};
</script>
