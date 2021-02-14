<template>
  <div class="weeklyFeed">
    <div v-for="(mission, index) in missions" :key="index" class="feed">
      <img v-if="mission.upload" :src="mission.postImage" />
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
  data: () => {
    return {
      missions: [],
    };
  },
  created() {
    this.getWeeklyFeed();
  },
  methods: {
    // 일주일치 미션 서버에서 가져오기
    getWeeklyFeed() {
      this.$axios({
        url: "/post/find/challenge/user",
        method: "GET",
        params: {
          challengeId: "1",
          userId: "10",
        },
      })
        .then((response) => {
          this.setWeeklyFeed(response.data);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 서버에서 가져온 데이터를 missions 배열에 저장
    setWeeklyFeed(data) {
      const week = new Array(7);
      for (let index = 0; index < 7; index++) {
        data.forEach((dayData) => {
          if (dayData.missionId % 7 === index + 1) {
            week[index] = {
              ...dayData,
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
      this.missions = week;
    },
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
