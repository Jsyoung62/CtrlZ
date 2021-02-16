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
      <swiper-slide v-for="mission in missions" :key="mission.missionId">
        <Day :title="mission.missionTitle" :thumbnail="mission.missionImage" />
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
      challengeName: "",
      challengeDescription: "",
      missions: [],
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
    this.callDailyChallenge();
    this.callDailyMissions();
  },
  methods: {
    // 데일리 미션 정보 불러오기
    callDailyChallenge() {
      this.$axios({
        url: "/challenge/type",
        method: "GET",
        params: {
          challengeType: "데일리",
        },
      }).then((res) => {
        const info = res.data[0];
        this.challengeName = info.challengeName;
        this.challengeDescription = info.challengeContent;
      });
    },

    // 데일리 미션 title, 이미지(미션내용) 불러오기
    // 데일리 미션 challengeID = 1
    callDailyMissions() {
      this.$axios({
        url: "/mission/",
        method: "GET",
        params: {
          challengeId: "1",
        },
      }).then((res) => {
        this.missions = res.data.map((mission) => {
          // 정방형 이미지 처리
          const imageURL = mission.missionImage.split(".jpg")[0];
          return {
            ...mission,
            missionImage: `${imageURL}_square.jpg`,
          };
        });
      });
    },
  },
};
</script>
