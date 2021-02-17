<template>
  <div class="zbti">
    <div class="title">
      나에게 맞는 환경 보호 활동은?
    </div>
    <div class="description">
      내 성향에 맞는 환경보호활동을 알아보세요!
    </div>
    <img src="http://i4a202.p.ssafy.io/img/zbti_images/zbtiIntro.png" class="intro" />
    <router-link to="/zbti/test" tag="button" class="startButton">
      ZBTI 테스트 시작하기
    </router-link>
    <router-link to="/" tag="button" class="skipButton">
      다음에 할래요
    </router-link>
  </div>
</template>

<script>
export default {
  name: "ZbtiIntro",
};
</script>
