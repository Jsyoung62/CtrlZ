<template>
  <nav class="sideNav">
    <div class="navHeader">
      <Logo />
      <span class="material-icons" @click="close">
        clear
      </span>
    </div>

    <a href="/">홈</a>
    <a href="/">전체 챌린지</a>
    <a href="/">마이페이지</a>
    <a href="/">ZBTI 테스트</a>
    <a href="/">로그인</a>
  </nav>
</template>
<script>
import Logo from "@/assets/logo.vue";
import "@/components/css/sideNav.scss";
export default {
  name: "SideNav",
  components: {
    Logo,
  },
  methods: {
    close() {
      document.getElementsByClassName("sideNav")[0].style.transform = "translateX(-105%)";
    },
  },
};
</script>
