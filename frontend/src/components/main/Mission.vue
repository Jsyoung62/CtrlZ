<template>
  <div class="mission" @click="handleClick">
    <img :src="data.imageURL" class="thumbnail" />
    <div class="title">
      {{ data.challengeName }}
    </div>
  </div>
</template>
<script>
import "@/components/css/main/mission.scss";

export default {
  name: "Mission",
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  methods: {
    handleClick() {
      this.$router.push("/challenge/detail");
    },
  },
};
</script>
