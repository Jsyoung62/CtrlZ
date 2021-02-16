<template>
  <div class="weeklyFeed">
    <div v-for="(mission, index) in missions" :key="index" class="feed">
      <img
        v-if="mission.upload"
        :src="mission.postImage"
        @click="handlePostClick(mission.postId)"
      />
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
      missions: [
        {
          missionDay: "月",
          upload: false,
        },
        {
          missionDay: "火",
          upload: false,
        },
        {
          missionDay: "水",
          upload: false,
        },
        {
          missionDay: "木",
          upload: false,
        },
        {
          missionDay: "金",
          upload: false,
        },
        {
          missionDay: "土",
          upload: false,
        },
        {
          missionDay: "日",
          upload: false,
        },
      ],
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
          userId: this.$store.state.userInfo.userId,
        },
      })
        .then((response) => {
          if (response.data !== "") {
            this.missions = response.data;
            this.setWeeklyFeed(this.missions);
          }
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
          // 업로드 된 부분은 사진으로 표시
          if (dayData.missionId % 7 === index + 1) {
            week[index] = {
              ...dayData,
              disabled: "disabled",
              upload: true,
              missionDay: this.getDay(index),
            };
          }
        });

        // 업로드 안된 부분은 빈 공간으로 표시
        if (week[index] === undefined) {
          week[index] = {
            disabled: this.isDisabled(index),
            missionDay: this.getDay(index),
          };
        }
      }
      this.missions = week;
    },
    // 미래 미션들은 비활성화
    isDisabled(num) {
      const day = new Date().getDay();
      return day <= num ? "disabled" : "";
    },
    // 요일에 맞는 글자 표기를 위한 작업
    getDay(num) {
      if (num === 0) return "月";
      else if (num === 1) return "火";
      else if (num === 2) return "水";
      else if (num === 3) return "木";
      else if (num === 4) return "金";
      else if (num === 5) return "土";
      else if (num === 6) return "日";
    },
    // 해당 게시글 상세보기로 이동
    handlePostClick(postId) {
      this.$router.push({
        name: "Post",
        params: {
          postId,
        },
      });
    },
  },
};
</script>
