<template>
  <div class="challengeDetail">
    <Header title="챌린지 상세보기" left-icon="chevron_left" />
    <Navigation />

    <div class="thumbnailWrapper">
      <img :src="challenge.challengeImage" class="thumbnail" />
      <ProgressBar v-show="isInprogress || isAchived" :progress="progress" />
    </div>

    <div class="challengeDetailContainer">
      <ChallengeTitle
        :title="challenge.challengeName"
        :level="challenge.levelId"
        :challenge-type="challenge.challengeType"
      />
      <div class="countWrapper">
        <div>
          {{ achived | numberWithComma }}
          명
          <span>완료</span>
        </div>
        <div>
          {{ challenge.participants | numberWithComma }}
          명
          <span>도전중</span>
        </div>
      </div>

      <p class="description">
        {{ challenge.challengeContent }}
      </p>

      <div v-if="isUser">
        <p v-if="isInprogress" class="status" @click="moveChallengeInProgress">
          {{ progress }}
          % 진행중
        </p>
        <p v-else-if="isAchived" class="status">
          {{ challengeStatus.challengeFinishOrder | numberWithComma }}
          번째로 완료했어요!
        </p>
        <button v-else class="startButton" @click="handleStartButtonClick">
          {{ (achived + challenge.participants + 1) | numberWithComma }}
          번째 참여자 되기
        </button>
      </div>
      <div v-else>
        <button class="startButton" @click="handleLoginButtonClick">
          로그인 후 이용해주세요
        </button>
      </div>
    </div>

    <ChallengeFeed :posts="challengePosts" />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import ProgressBar from "@/components/common/ProgressBar.vue";
import ChallengeTitle from "@/components/challenge/ChallengeTitle.vue";
import ChallengeFeed from "@/components/challenge/ChallengeFeed.vue";
import "@/components/css/challenge/challengeDetail.scss";

export default {
  name: "ChallengeDetail",
  components: {
    Header,
    Navigation,
    ProgressBar,
    ChallengeTitle,
    ChallengeFeed,
  },
  filters: {
    numberWithComma(num) {
      return String(num).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  data: () => {
    return {
      userId: "",
      isUser: false,
      challengeId: "",
      challenge: "",
      challengeStatus: "",
      achived: "",
      challengePosts: "",
      isInprogress: false,
      isAchived: false,
    };
  },
  computed: {
    progress() {
      return Math.floor(
        (this.challengeStatus.challengeMissionCurrent / this.challenge.challengeMissionTotal) * 100
      );
    },
  },
  created() {
    this.userId = this.$store.state.userInfo.userId;
    this.challengeId = this.$route.params.challengeId;

    this.getChallengeInfo(this.challengeId);
    this.getChallengeFeed(this.challengeId);
    this.getAchivedCount(this.challengeId);

    // 로그인 한 경우
    if (this.userId !== "") {
      this.isUser = true;
      this.getUserChallengeStatus();
    }
  },
  methods: {
    // 해당 챌린지 데이터 조회
    getChallengeInfo(challengeId) {
      this.$axios({
        url: "/challenge/",
        method: "GET",
        params: {
          challengeId,
        },
      })
        .then((response) => {
          this.challenge = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 해당 챌린지의 모든 게시글 조회
    getChallengeFeed(challengeId) {
      this.$axios({
        url: "/post/find/challenge/",
        method: "GET",
        params: {
          challengeId,
        },
      })
        .then((response) => {
          this.challengePosts = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 해당 챌린지 완료자 수 조회
    getAchivedCount(challengeId) {
      this.$axios({
        url: "/challenge/status/count/achived",
        method: "GET",
        params: {
          challengeId,
        },
      })
        .then((response) => {
          this.achived = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 현재 사용자의 해당 챌린지 참여 현황 조회
    getUserChallengeStatus() {
      this.$axios({
        url: `/challenge/status/${this.challengeId}/${this.userId}`,
        method: "GET",
      })
        .then((response) => {
          this.challengeStatus = response.data;

          if (this.challengeStatus !== null) {
            if (this.challengeStatus.challengeFinishDate === null) {
              this.isInprogress = true;
            }
            if (this.challengeStatus.challengeFinishDate !== null) {
              this.isAchived = true;
            }
          }
        })
        .catch((error) => {
          // 현재 사용자가 해당 챌린지에 참여하지 않고 있는 경우
          console.error(error);
        });
    },
    handleStartButtonClick() {
      this.$axios({
        url: "/challenge/status/",
        method: "POST",
        data: {
          challengeId: this.challengeId,
          userId: this.userId,
        },
      })
        .then(() => {
          // 챌린지 시작하면 진행 중인 챌린지 화면으로 이동
          this.moveChallengeInProgress();
        })
        .catch((error) => {
          console.error(error);
        }); // 현재 사용자의 해당 챌린지 참여 현황 추가
    },
    handleLoginButtonClick() {
      this.$router.push("/login");
    },
    // 진행 중인 챌린지 화면으로 이동
    moveChallengeInProgress() {
      this.$router.push({
        name: "InProgressChallenge",
        params: {
          challengeId: this.challengeId,
        },
      });
    },
  },
};
</script>
