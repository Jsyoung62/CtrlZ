<template>
  <div class="scoreBoard">
    <h1>나의 에코지수</h1>
    <div class="contents">
      <div class="gauge">
        <Gauge />
        <GaugeBar class="bar" />
      </div>
      <div class="score">
        <span class="number">{{ score }}</span>
        <span>점</span>
        <p>상위 {{ percentage }}% ({{ rank }}등)</p>
      </div>
    </div>
  </div>
</template>
<script>
import { Gauge, GaugeBar } from "@/assets/index";

export default {
  name: "ScoreBoard",
  components: {
    Gauge,
    GaugeBar,
  },
  data: () => {
    return {
      score: 73,
      percentage: 43,
      rank: 372,
    };
  },
};
</script>
