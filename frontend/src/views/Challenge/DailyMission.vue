<template>
  <div class="dailyMission">
    <Header title="하루 하나 도전" left-icon="chevron_left" />
    <Navigation />

    <img :src="challenge.missionImage" class="thumbnail" />

    <div class="InfoContainer">
      <div class="titleWrapper">
        <p class="title">
          {{ challenge.missionTitle }}
        </p>
        <p class="participants">
          {{ participants | numberWithComma }}
          명
          <span>참여중</span>
        </p>
      </div>
      <UploadModal
        v-show="isModalViewed"
        :mission="missions[index]"
        @close="isModalViewed = false"
      />
      <button @click="handleMissionClick()">
        실천 리스트
      </button>
    </div>

    <p class="description">
      {{ challenge.dailyContent }}
    </p>

    <WeeklyFeed />
  </div>
</template>
<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import WeeklyFeed from "@/components/challenge/WeeklyFeed.vue";
import UploadModal from "@/components/common/UploadModal.vue";
import "@/components/css/challenge/dailyMission.scss";

export default {
  name: "DailyMission",
  components: {
    Header,
    Navigation,
    WeeklyFeed,
    UploadModal,
  },
  filters: {
    numberWithComma(num) {
      return String(num).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  data: () => {
    return {
      challenge: {},
      participants: 0,
      isModalViewed: false,
      missions: [],
      index: "",
    };
  },
  created() {
    const day = new Date().getDay();
    this.getDailyMission(day);
    this.getParticipants();
  },
  methods: {
    // 오늘 날짜에 맞는 데일리 미션 정보 불러오기
    getDailyMission(day) {
      this.$axios({
        url: "/mission/",
        method: "GET",
        params: {
          challengeId: "1",
        },
      })
        .then((response) => {
          this.missions = response.data;
          const result = response.data.filter((mission) => {
            this.index = day - 1;
            if (this.index < 0) {
              this.index === 6;
            }
            return mission.id.missionId % 7 === day;
          });
          this.challenge = result[0];
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 데일리 미션에 참여중인 사람 수 불러오기 (완료한 사람 수 + 참여중인 사람 수)
    getParticipants() {
      this.$axios({
        url: "/challenge/status/count/achived",
        method: "GET",
        params: {
          challengeId: "1",
        },
      })
        .then((response) => {
          this.participants += response.data;
        })
        .catch((error) => {
          console.error(error);
        });

      this.$axios({
        url: "/challenge/status/count/inprogress",
        method: "GET",
        params: {
          challengeId: "1",
        },
      })
        .then((response) => {
          this.participants += response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },

    handleMissionClick() {
      this.isModalViewed = true;
    },
  },
};
</script>
