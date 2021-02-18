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
        :mission="modalData"
        :is-button="isModalButton"
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
    <ChallengeFeed :posts="challengePosts" />
  </div>
</template>
<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import WeeklyFeed from "@/components/challenge/WeeklyFeed.vue";
import UploadModal from "@/components/common/UploadModal.vue";
import ChallengeFeed from "@/components/challenge/ChallengeFeed.vue";
import "@/components/css/challenge/dailyMission.scss";

export default {
  name: "DailyMission",
  components: {
    Header,
    Navigation,
    WeeklyFeed,
    UploadModal,
    ChallengeFeed,
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
      modalData: {},
      isModalButton: true,
      challengePosts: {},
    };
  },
  created() {
    const day = new Date().getDay();
    this.getDailyMission(day);
    this.getParticipants();
    this.getChallengeFeed();
  },
  methods: {
    // 데일리 미션 정보 불러오기
    getDailyMission(day) {
      this.$axios({
        url: "/mission/",
        method: "GET",
        params: {
          challengeId: "1",
        },
      })
        .then((response) => {
          // 오늘 요일에 맞는 미션 정보 찾기
          const result = response.data.filter((mission) => {
            return mission.id.missionId % 7 === day;
          });
          this.challenge = result[0];
          this.modalData = {
            ...result[0],
            missionImage: null,
          };
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
      this.checkMissionUploaded();
      this.isModalViewed = true;
    },
    checkMissionUploaded() {
      this.$axios({
        url: "/post/find/challenge/user/mission",
        method: "GET",
        params: {
          userId: this.$store.state.userInfo.userId,
          challengeId: "1",
          missionId: this.modalData.id.missionId,
        },
      })
        .then((response) => {
          if (response.data === "") {
            this.isModalButton = true;
          } else {
            this.isModalButton = false;
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 해당 챌린지의 오늘 미션의 모든 게시글 조회
    getChallengeFeed() {
      this.$axios({
        url: "/post/find/challenge/mission",
        method: "GET",
        params: {
          challengeId: "1",
          misisonId: this.modalData.id.missionId,
        },
      })
        .then((response) => {
          this.challengePosts = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
