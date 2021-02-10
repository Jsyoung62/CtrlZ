<template>
  <div class="profile">
    <Header title="마이페이지" />
    <Navigation />
    <ProfileBoard
      user-name="greengirl_927"
      user-introduce="건강한 지구를 만들기 위해 불편함을 감수하는 Z세대 그린걸입니다~"
    />
    <ZScore :score="score" />
    <ChallengeBoard />
    <ZFeed :images="images" />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import ChallengeBoard from "@/components/user/ChallengeBoard.vue";
import ProfileBoard from "@/components/user/ProfileBoard.vue";
import ZScore from "@/components/user/ZScore.vue";
import ZFeed from "@/components/user/ZFeed.vue";
import "@/components/css/user/profile.scss";

export default {
  name: "Profile",
  components: {
    Header,
    Navigation,
    ChallengeBoard,
    ProfileBoard,
    ZScore,
    ZFeed,
  },
  data: () => {
    return {
      score: 73,
      images: [
        "http://i4a202.p.ssafy.io/img/dailymission.png",
        "http://i4a202.p.ssafy.io/img/dailymission.png",
        "http://i4a202.p.ssafy.io/img/dailymission.png",
        "http://i4a202.p.ssafy.io/img/dailymission.png",
      ],
    };
  },
};
</script>
