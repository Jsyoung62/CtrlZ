<template>
  <div class="zbti">
    <div class="title">
      나에게 맞는 환경 보호 활동은?
    </div>
    <div class="description">
      내 성향에 맞는 환경보호활동을 알아보세요!
    </div>
    <img src="@/assets/zbti_example.jpg" class="intro" />
    <router-link to="/zbti/test" tag="button" class="startButton">
      ZBTI 테스트 시작하기
    </router-link>
    <router-link to="/" tag="button" class="skipButton">
      다음에 할래요
    </router-link>
  </div>
</template>

<script>
import "@/components/css/zbti/index.scss";

export default {
  name: "ZbtiIntro",
};
</script>
