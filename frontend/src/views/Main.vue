<template>
  <div class="main">
    <Header title="Ctrl Z" />
    <Navigation />

    <Mission
      topic="데일리 미션"
      title="잔반 없는 金요일"
      participants="3123"
      description="매일 데일리 미션을 수행하며 생활 속에서\n꾸준히 제로웨이스트를 실천해보세요!"
    />
    <Mission
      topic="환경부와 런데이가 함께하는"
      title="Run for earth 챌린지"
      participants="17211"
      description=""
    />
    <Challenges />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import Mission from "@/components/main/Mission.vue";
import Challenges from "@/components/main/Challenges.vue";
import "@/components/css/main/index.scss";

export default {
  name: "Main",
  components: {
    Header,
    Navigation,
    Mission,
    Challenges,
  },
};
</script>
