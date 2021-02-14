<template>
  <swiper class="swiper" :options="swiperOption">
    <swiper-slide>
      <Mission :challenge="sponseredChallenge" />
    </swiper-slide>
    <swiper-slide v-for="(challenge, index) in recommendChallenges" :key="index">
      <Mission :challenge="challenge" />
    </swiper-slide>
    <div slot="pagination" class="swiper-pagination"></div>
    <div slot="button-prev" class="swiper-button-prev"></div>
    <div slot="button-next" class="swiper-button-next"></div>
  </swiper>
</template>
<script>
import Mission from "@/components/main/Mission.vue";
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import "swiper/css/swiper.css";
export default {
  name: "Challenges",
  components: {
    Swiper,
    SwiperSlide,
    Mission,
  },
  props: {
    sponseredChallenge: {
      type: Object,
      required: true,
    },
    recommendChallenges: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      swiperOption: {
        pagination: {
          el: ".swiper-pagination",
          dynamicBullets: true,
          clickable: true,
        },
        speed: 300,
        autoHeight: true,
        centeredSlides: true,
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      },
    };
  },
  computed: {
    challenges() {
      return this.recommendChallenges.map((challenge) => {
        return {
          challengeId: challenge.challengeId,
          challengeName: challenge.challengeName,
          imageURL: challenge.challengeImage,
        };
      });
    },
  },
};
</script>

<style lang="scss">
@import "@/components/css/colors.scss";

.swiper-button-prev {
  color: $basic-white;
  left: 15px;

  &::after {
    font-size: 20px;
    font-weight: bold;
  }
}

.swiper-button-next {
  color: $basic-white;
  right: 15px;

  &::after {
    font-size: 20px;
    font-weight: bold;
  }
}

.swiper-pagination-bullet {
  background: $basic-white;
  opacity: 50%;
}

.swiper-pagination-bullet-active-main {
  background: $basic-white;
  opacity: 100%;
}
</style>
