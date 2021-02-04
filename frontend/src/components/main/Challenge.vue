<template>
  <div class="challenge">
    <h3>{{ name }}</h3>
    <div class="leaf">
      <LeafOn v-for="index in achived" :key="index" />
    </div>
    <div class="leaf">
      <LeafOff v-for="index in nonAchived" :key="index" />
    </div>
  </div>
</template>
<script>
import LeafOn from "@/assets/leafOn.vue";
import LeafOff from "@/assets/leafOff.vue";
export default {
  name: "Challenge",
  components: {
    LeafOn,
    LeafOff,
  },
  props: {
    name: {
      type: String,
      required: true,
    },
    total: {
      type: Number,
      required: true,
    },
    achived: {
      type: Number,
      required: true,
    },
  },
  computed: {
    nonAchived() {
      return this.total - this.achived;
    },
  },
};
</script>
