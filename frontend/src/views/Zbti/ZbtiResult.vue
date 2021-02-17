<template>
  <div class="zbti">
    <div class="title">
      <p>당신은</p>
      {{ zbtiResult.zbtiName }}
    </div>
    <img :src="zbtiResult.zbtiImage" class="resultImage" />
    <div class="title">
      <span>추천 챌린지 영역 #</span>
      {{ zbtiResult.zbtiType }}
    </div>
    <div class="description">
      {{ zbtiResult.zbtiDescription[0] }}
      <div class="highlightWrapper">
        <div class="highlight"></div>
        <span>{{ zbtiResult.zbtiDescription[1] }}</span>
      </div>
      {{ zbtiResult.zbtiDescription[2] }}
    </div>
    <router-link to="/" tag="button" class="startButton">
      챌린지 하러 가기
    </router-link>
  </div>
</template>

<script>
export default {
  name: "ZbtiResult",
  data: () => {
    return {
      userId: "",
      zbtiId: "",
      zbtiResult: {
        zbtiType: "",
        zbtiName: "",
        zbtiImage: "",
        zbtiDescription: [],
      },
    };
  },
  created() {
    this.userId = this.$store.state.userInfo.userId;
    this.zbtiId = this.$route.params.zbtiId;

    this.getZbtiResult();
    this.setZbtiResult();
  },
  methods: {
    // ZBTI 결과 불러오기
    getZbtiResult() {
      this.$axios({
        url: "/zbti/result",
        method: "GET",
        params: {
          zbtiId: this.zbtiId,
        },
      })
        .then((response) => {
          this.zbtiResult = response.data;

          // 하이라이트 분리
          this.zbtiResult.zbtiDescription = this.zbtiResult.zbtiDescription.split("\b");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    setZbtiResult() {
      // 로그인 후 ZBTI 테스트 하는 경우
      if (this.userId > 0) {
        this.$axios({
          url: "/user/zbti",
          method: "PUT",
          data: {
            userId: this.userId,
            zbtiId: this.zbtiId,
          },
        })
          .then(() => {})
          .catch((error) => {
            console.error(error);
          });
      } else {
        // 로그인안하고 ZBTI 테스트 하는 경우 vuex에 저장
        this.$store.commit("SETZBTI", this.zbtiId);
      }
    },
  },
};
</script>
