<template>
  <nav class="navigation">
    <div class="buttonWrapper" @click="handleHomeClick">
      <span class="material-icons">
        home
      </span>
      <p>홈</p>
    </div>

    <div class="buttonWrapper" @click="handleTodayClick">
      <span class="material-icons">
        today
      </span>
      <p>오늘의미션</p>
    </div>

    <div class="buttonWrapper" @click="handleChallengeClick">
      <span class="material-icons">
        bubble_chart
      </span>
      <p>챌린지</p>
    </div>

    <div class="buttonWrapper" @click="handleMypageClick">
      <span class="material-icons">
        account_circle
      </span>
      <p>마이페이지</p>
    </div>
  </nav>
</template>
<script>
import "@/components/css/navigation.scss";

export default {
  name: "Navigation",
  components: {},
  data: () => {
    return {
      isUser: false,
    };
  },
  mounted() {
    if (this.$store.state.userInfo.userId !== "") {
      this.isUser = true;
    }
  },
  methods: {
    handleHomeClick() {
      if (this.$router.currentRoute.path !== "/") {
        // 같은 URL 중복 접근 못하게 처리
        this.$router.push("/");
      }
    },
    handleTodayClick() {
      if (this.$router.currentRoute.path !== "/challenge/daily") {
        // 같은 URL 중복 접근 못하게 처리
        this.$router.push("/challenge/daily");
      }
    },
    handleChallengeClick() {
      if (this.$router.currentRoute.path !== "/challenge") {
        // 같은 URL 중복 접근 못하게 처리
        this.$router.push("/challenge");
      }
    },
    handleMypageClick() {
      // 이미 로그인 한 경우  & 같은 URL 중복 접근 못하게 처리
      if (this.isUser) {
        if (this.$router.currentRoute.path !== "/user/profile") {
          this.$router.push("/user/profile");
        }
      } else {
        this.$router.push("/login");
      }
    },
  },
};
</script>
