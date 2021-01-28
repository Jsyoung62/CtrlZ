<template>
  <div class="recommendBoard">
    <h1>추천 챌린지</h1>
    <div class="contents">
      <div class="overlay"></div>
      <img src="@/assets/ch_example.jpg" class="thumbnail" />
      <h2 class="title">
        {{ title }}
      </h2>
    </div>
  </div>
</template>
<script>
export default {
  name: "RecommendBoard",
  data: () => {
    return {
      title: "텀블러 사용하기",
    };
  },
};
</script>
