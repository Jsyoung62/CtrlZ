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
        this.$router.push("/");
      }
    },
    handleTodayClick() {
      if (this.$router.currentRoute.path !== "/challenge/daily") {
        this.$router.push("/challenge/daily");
      }
    },
    handleChallengeClick() {
      if (this.$router.currentRoute.path !== "/challenge") {
        this.$router.push("/challenge");
      }
    },
    handleMypageClick() {
      if (this.isUser) {
        this.$router.push("/user/profile");
        // eslint-disable-next-line prettier/prettier
      } 
      else {
        this.$router.push("/login");
      }
    },
  },
};
</script>
