<template>
  <div class="profile">
    <Header title="마이페이지" />
    <Navigation />

    <ProfileBoard
      :user-name="user.userName"
      :user-image="user.userImage"
      :user-introduce="user.userIntroduce"
    />
    <ZScore
      :score="userScore.zscore"
      :percent="userRank[1]"
      :rank="userRank[0]"
      :zbti="userScore.zbtiId"
    />
    <ChallengeBoard :challenges="challenges" />
    <ZFeed :data="feed" />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import ProfileBoard from "@/components/user/ProfileBoard.vue";
import ChallengeBoard from "@/components/user/ChallengeBoard.vue";
import ZScore from "@/components/user/ZScore.vue";
import ZFeed from "@/components/user/ZFeed.vue";
import "@/components/css/user/profile.scss";

export default {
  name: "Profile",
  components: {
    Header,
    Navigation,
    ProfileBoard,
    ChallengeBoard,
    ZScore,
    ZFeed,
  },
  data: () => {
    return {
      user: {},
      userScore: {
        zscore: 0,
        zbtiId: "",
      },
      // eslint-disable-next-line prettier/prettier
      userRank: [ 0, 0 ],
      challenges: [],
      feed: [],
    };
  },
  created() {
    this.user = this.$store.state.userInfo;

    this.getZScore();
    this.getRank();
    this.getChallenges();
    this.getFeed();
  },
  methods: {
    // 유저 점수, zbti 조회
    getZScore() {
      this.$axios({
        url: "/user/zscore",
        method: "GET",
        params: {
          userId: this.user.userId,
        },
      })
        .then((response) => {
          this.userScore = response.data;

          this.changeZbtiResult();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 유저의 [등수, 상위 퍼센트]
    getRank() {
      this.$axios({
        url: "/user/rank",
        method: "GET",
        params: {
          userId: this.user.userId,
        },
      })
        .then((response) => {
          this.userRank = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 진행중인 챌린지 조회
    getChallenges() {
      this.$axios({
        url: "/challenge/status/user",
        method: "GET",
        params: {
          userId: this.user.userId,
        },
      })
        .then((response) => {
          this.challenges = response.data
            .filter((challengeInfo) => challengeInfo.challengeFinishDate === null)
            .map((challengeInfo) => {
              return {
                challengeId: challengeInfo.challenge.challengeId,
                challengeName: challengeInfo.challenge.challengeName,
                challengeMissionTotal: challengeInfo.challenge.challengeMissionTotal,
                challengeMissionCurrent: challengeInfo.challengeMissionCurrent,
                missionNonAchieve:
                  challengeInfo.challenge.challengeMissionTotal -
                  challengeInfo.challengeMissionCurrent,
              };
            });
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 유저가 작성한 게시글 조회
    getFeed() {
      this.$axios({
        url: "/post/find/user",
        method: "GET",
        params: {
          userId: this.user.userId,
        },
      })
        .then((response) => {
          if (response.data !== "") {
            this.feed = response.data;
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // zbitId를 zbti 유형 이름으로 변경
    changeZbtiResult() {
      if (this.userScore.zbtiId === "A") {
        this.userScore.zbtiId = "패션";
      } else if (this.userScore.zbtiId === "B") {
        this.userScore.zbtiId = "음식";
      } else if (this.userScore.zbtiId === "C") {
        this.userScore.zbtiId = "일상";
      } else if (this.userScore.zbtiId === "D") {
        this.userScore.zbtiId = "활동";
      }
    },
  },
};
</script>
