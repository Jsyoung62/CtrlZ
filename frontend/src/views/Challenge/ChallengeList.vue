<template>
  <div class="challengeList">
    <Header title="전체 챌린지" />
    <Navigation />

    <Categories />
    <div class="challengeContainer">
      <div v-for="(challenge, index) in challenges" :key="index" class="challenge">
        <img src="@/assets/mission.png" class="thumbnail" />

        <p class="category">
          <span>#{{ challenge.level }}</span>
          <span>#{{ challenge.challengeType }}</span>
        </p>
        <p class="challengeName">
          {{ challenge.challengeName }}
        </p>
        <p class="participants">
          {{ challenge.participants }}
          명 도전 중
        </p>
      </div>
    </div>
  </div>
</template>
<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import Categories from "@/components/challenge/Categories.vue";
import "@/components/css/challenge/index.scss";
import "@/components/css/challenge/challenge.scss";

export default {
  name: "ChallengeList",
  components: {
    Header,
    Navigation,
    Categories,
  },
  data: () => {
    return {
      challenges: [
        {
          level: "입문",
          challengeType: "생활",
          challengeName: "시작이 반이다",
          participants: 13723,
        },
        {
          level: "고급",
          challengeType: "생활",
          challengeName: "플라스틱 다이어트",
          participants: 2988,
        },
      ],
    };
  },
};
</script>
