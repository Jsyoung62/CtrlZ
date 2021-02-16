<template>
  <div class="zscore">
    <div class="title">
      <p>Z score</p>
    </div>
    <div class="board">
      <span class="zScore">
        {{ score }}
      </span>
      점
      <p class="rank">
        상위<span class="percent">{{ percent }}</span> % (<span class="rank">{{ rank }}</span> 등)
      </p>
      <div class="zbtiWrapper">
        <div v-if="zbtiTest" class="zbtiWrapper">
          <p>관심영역</p>
          <div :class="['zbti', style]">
            {{ zbti }}
          </div>
        </div>
        <router-link v-else to="/zbti">
          나의 관심영역 알아보기
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ZScore",
  props: {
    score: {
      type: Number,
      required: true,
    },
    percent: {
      type: Number,
      required: true,
    },
    rank: {
      type: Number,
      required: true,
    },
    zbti: {
      type: String,
      default: "",
      required: false,
    },
  },
  data: () => {
    return {
      style: "",
      zbtiTest: true,
    };
  },
  mounted() {
    if (this.zbti === "일상") {
      return (this.style = "yellow");
    }
    if (this.zbti === "음식") {
      return (this.style = "purple");
    }
    if (this.zbti === "패션") {
      return (this.style = "pink");
    }
    if (this.zbti === "활동") {
      return (this.style = "blue");
    }

    if (this.zbti === "") {
      return (this.zbtiTest = false);
    }
  },
};
</script>
