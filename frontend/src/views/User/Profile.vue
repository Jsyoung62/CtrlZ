<template>
  <div class="profile">
    <Header title="마이페이지" />
    <Navigation />

    <ProfileBoard
      :user-name="user.userName"
      :user-image="user.userImage"
      :user-introduce="user.userIntroduce"
    />
    <ZScore :score="user.zscore" :percent="user.rank[1]" :rank="user.rank[0]" :zbti="user.zbti" />
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
      challenges: [],
      feed: [],
    };
  },
  created() {
    this.user = this.$store.state.userInfo;

    // zbitId에 맞게 zbti 이름 받아오기
    if (this.user.zbtiId === "A") {
      this.user = {
        ...this.user,
        zbti: "패션",
      };
    } else if (this.user.zbtiId === "B") {
      this.user = {
        ...this.user,
        zbti: "음식",
      };
    } else if (this.user.zbtiId === "C") {
      this.user = {
        ...this.user,
        zbti: "일상",
      };
    } else if (this.user.zbtiId === "D") {
      this.user = {
        ...this.user,
        zbti: "활동",
      };
    }

    this.getChallenges();
    this.getFeed();
  },
  methods: {
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
          this.challenges = response.data.map((challengeInfo) => {
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
          this.feed = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
