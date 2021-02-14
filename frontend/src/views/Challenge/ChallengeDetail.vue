<template>
  <div class="challengeDetail">
    <Header title="챌린지 상세보기" />
    <Navigation />

    <div class="thumbnailWrapper">
      <img :src="challenge.challengeImage" class="thumbnail" />
      <progress v-show="isInprogress || isAchived" :value="progress" max="100"></progress>
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

      <p v-if="isInprogress" class="status">
        {{ progress }}
        % 진행중
      </p>
      <p v-else-if="isAchived" class="status">
        {{ achived | numberWithComma }}
        번째로 완료했어요!
      </p>
      <button v-else class="startButton" @click="handleStartButton">
        {{ (achived + challenge.participants + 1) | numberWithComma }}
        번째 참여자 되기
      </button>
    </div>

    <ChallengeFeed :posts="challengePosts" />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import ChallengeTitle from "@/components/challenge/ChallengeTitle.vue";
import ChallengeFeed from "@/components/challenge/ChallengeFeed.vue";
import "@/components/css/challenge/challengeDetail.scss";

export default {
  name: "ChallengeDetail",
  components: {
    Header,
    Navigation,
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

    this.$axios({
      url: "/challenge/",
      method: "GET",
      params: {
        challengeId: this.challengeId,
      },
    })
      .then((response) => {
        this.challenge = response.data;
      })
      .catch((error) => {
        console.error(error);
      }); // 해당 챌린지 데이터 조회

    this.$axios({
      url: "/post/find/challenge/",
      method: "GET",
      params: {
        challengeId: this.challengeId,
      },
    })
      .then((response) => {
        this.challengePosts = response.data;
      })
      .catch((error) => {
        console.error(error);
      }); // 해당 챌린지의 모든 게시글 조회

    this.$axios({
      url: "/challenge/status/count/achived",
      method: "GET",
      params: {
        challengeId: this.challengeId,
      },
    })
      .then((response) => {
        this.achived = response.data;
      })
      .catch((error) => {
        console.error(error);
      }); // 해당 챌린지 완료자 수 조회

    if (this.userId > 0) {
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
          console.log("현재 사용자가 해당 챌린지에 참여하지 않고 있는 경우");
          console.error(error);
        }); // 현재 사용자의 해당 챌린지 참여 현황 조회
    }
  },
  methods: {
    handleStartButton() {
      if (this.userId > 0) {
        this.$axios({
          url: "/challenge/status/",
          method: "POST",
          data: {
            challengeId: this.challengeId,
            userId: this.userId,
          },
        })
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.error(error);
          }); // 현재 사용자의 해당 챌린지 참여 현황 추가
      }
    },
  },
};
</script>
