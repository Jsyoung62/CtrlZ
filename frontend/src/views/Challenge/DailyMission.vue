<template>
  <div class="dailyMission">
    <Header title="데일리 미션" />
    <Navigation />

    <img src="@/assets/mission.png" class="thumbnail" />

    <div class="InfoContainer">
      <div class="titleWrapper">
        <p class="title">
          title
        </p>
        <p class="participants">
          {{ 71222 | numberWithComma }}
          명
          <span>참여중</span>
        </p>
      </div>
      <button>실천 리스트</button>
    </div>

    <p class="description">
      {{ description }}
    </p>

    <WeeklyFeed :status="status" />
  </div>
</template>
<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import WeeklyFeed from "@/components/challenge/WeeklyFeed.vue";
import "@/components/css/challenge/dailyMission.scss";

export default {
  name: "DailyMission",
  components: {
    Header,
    Navigation,
    WeeklyFeed,
  },
  filters: {
    numberWithComma(num) {
      return String(num).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  data: () => {
    return {
      description:
        "오염된 물을 다시 정화시키는 데에는 수많은 돈과 에너지가 필요합니다. 생활하수를 줄이는 것만으로도 환경보호에 큰 도움이 되니 오늘은 다같이 물을 아껴볼까요?",
      status: [
        {
          missionImage: "http://i4a202.p.ssafy.io/img/dailymission.png",
          missionDay: 0,
        },
        {
          missionImage: "http://i4a202.p.ssafy.io/img/dailymission.png",
          missionDay: 2,
        },
      ],
    };
  },
};
</script>
