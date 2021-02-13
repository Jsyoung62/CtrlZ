<template>
  <div class="dailyMission">
    <div class="titleWrapper">
      <h1 class="title">
        {{ challengeName }}
      </h1>
      <p class="description">
        {{ challengeDescription }}
      </p>
    </div>
    <swiper class="swiper" :options="swiperOption">
      <swiper-slide>
        <Day title="고기없는 月요일" />
      </swiper-slide>
      <swiper-slide>
        <Day title="고기없는 月요일" />
      </swiper-slide>
      <swiper-slide>
        <Day title="고기없는 月요일" />
      </swiper-slide>
      <swiper-slide>
        <Day title="고기없는 月요일" />
      </swiper-slide>
      <swiper-slide>
        <Day title="고기없는 月요일" />
      </swiper-slide>
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
  data() {
    return {
      imageURL: "",
      challengeName: "",
      challengeDescription: "",
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
      },
    };
  },
  created() {
    // 데일리 미션 데이터 불러오기
    this.callDailyChallenge();
  },
  methods: {
    // 데일리 미션 정보 불러오기
    callDailyChallenge() {
      this.$axios({
        url: "/challenge/",
        method: "GET",
        params: {
          challengeId: "1",
        },
      }).then((res) => {
        this.setDailyChallenge(res.data);
      });
    },
    setDailyChallenge(info) {
      this.imageURL = info.challengeImage;
      this.challengeName = info.challengeName;
      this.challengeDescription = info.challengeContent;
    },
  },
};
</script>
