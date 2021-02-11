<template>
  <div class="weeklyFeed">
    <div v-for="(mission, index) in missions" :key="index" class="feed">
      <img v-if="mission.upload" :src="mission.missionImage" />
      <div v-else class="emptyFeed" :class="mission.disabled">
        <div class="day">
          {{ mission.missionDay }}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import "@/components/css/challenge/weeklyFeed.scss";

export default {
  name: "WeeklyFeed",
  props: {
    status: {
      type: Array,
      required: true,
    },
  },
  computed: {
    // 일주일치 미션을 missions 배열에 저장
    missions() {
      const week = new Array(7);
      for (let index = 0; index < 7; index++) {
        this.status.forEach((value) => {
          if (value.missionDay === index) {
            week[index] = {
              ...value,
              disabled: "disabled",
              upload: true,
              missionDay: this.getDay(index),
            };
          }
        });

        if (week[index] === undefined) {
          week[index] = {
            disabled: this.isDisabled(index),
            missionDay: this.getDay(index),
          };
        }
      }
      return week;
    },
  },
  methods: {
    isDisabled(num) {
      const day = new Date().getDay();
      return day <= num ? "disabled" : "";
    },
    getDay(num) {
      if (num === 0) return "月";
      else if (num === 1) return "火";
      else if (num === 2) return "水";
      else if (num === 3) return "木";
      else if (num === 4) return "金";
      else if (num === 5) return "土";
      else if (num === 6) return "日";
    },
  },
};
</script>
