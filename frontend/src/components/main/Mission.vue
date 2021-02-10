<template>
  <div class="mission">
    <p class="topic">
      {{ topic }}
    </p>

    <img src="@/assets/mission.png" class="thumbnail" />

    <div class="titleWrapper">
      <p class="title">
        {{ title }}
      </p>
      <p class="participants">
        {{ participants | numberWithComma }}
        명 참여 중
      </p>
    </div>

    <div class="description">
      {{ description }}
    </div>
  </div>
</template>
<script>
import "@/components/css/main/mission.scss";

export default {
  name: "Mission",
  filters: {
    numberWithComma(num) {
      return String(num).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  props: {
    topic: {
      type: String,
      required: true,
    },
    title: {
      type: String,
      required: true,
    },
    participants: {
      type: Number,
      required: true,
    },
    description: {
      type: String,
      default: "",
      required: false,
    },
  },
};
</script>
