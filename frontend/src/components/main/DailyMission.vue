<template>
  <div class="dailyMission">
    <div class="titleWrapper">
      <h1 class="title">
        {{ data.challengeName }}
      </h1>
      <p class="description">
        {{ data.challengeDescription }}
      </p>
    </div>
    <swiper class="swiper" :options="swiperOption">
      <swiper-slide>
        <Day day="월요일" title="고기없는 月요일" />
      </swiper-slide>
      <swiper-slide>
        <Day day="화요일" title="고기없는 月요일" />
      </swiper-slide>
      <swiper-slide>
        <Day day="수요일" title="고기없는 月요일" />
      </swiper-slide>
      <swiper-slide>
        <Day day="목요일" title="고기없는 月요일" />
      </swiper-slide>
      <swiper-slide>
        <Day day="금요일" title="고기없는 月요일" />
      </swiper-slide>
      <div slot="button-prev" class="swiper-button-prev"></div>
      <div slot="button-next" class="swiper-button-next"></div>
    </swiper>
  </div>
</template>
<script>
import Day from "@/components/main/Day.vue";
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import "swiper/css/swiper.css";
import "@/components/css/main/dailyMission.scss";

export default {
  name: "DailyMission",
  components: {
    Swiper,
    SwiperSlide,
    Day,
  },
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      swiperOption: {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
          rotate: 50,
          stretch: 0,
          depth: 100,
          modifier: 1,
          slideShadows: true,
        },
        pagination: {
          el: ".swiper-pagination",
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      },
    };
  },
};
</script>
