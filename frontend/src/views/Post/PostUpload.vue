<template>
  <div class="postUpload">
    <Header left-icon="chevron_left" />
    <Navigation />

    <button class="uploadButton" @click="handlePostUploadClick">
      공유
    </button>

    <div class="postUploadImage">
      <img class="selectedImage" :src="postThumbnail" />
      <span>{{ missionTitle }}</span>
    </div>
    <div class="postUploadText">
      <textarea v-model="postContent" rows="30"></textarea>
    </div>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import "@/components/css/post/postUpload.scss";

export default {
  name: "PostUpload",
  components: {
    Header,
    Navigation,
  },
  data() {
    return {
      challengeId: "",
      missionId: "",
      userId: "",
      postThumbnail: "",
      postContent: "",
      missionTitle: "",
    };
  },
  created() {
    this.challengeId = this.$route.params.challengeId;
    this.missionId = this.$route.params.missionId;
    this.userId = this.$store.state.userInfo.userId;
    this.missionTitle = this.$route.params.missionTitle;

    this.makeThumbnail(this.$route.params.postImage);
  },
  methods: {
    // 썸네일 이미지 변환
    makeThumbnail(postImage) {
      const reader = new FileReader();
      reader.readAsDataURL(postImage);
      reader.onload = (evt) => {
        this.postThumbnail = evt.target.result;
      };
    },
    handlePostUploadClick() {
      // 이미지 전달을 위한 FormData
      const formData = new FormData();
      formData.append("challengeId", this.challengeId);
      formData.append("missionId", this.missionId);
      formData.append("userId", this.userId);
      formData.append("postContent", this.postContent);
      formData.append("postImage", this.$route.params.postImage);

      // 게시글 업로드
      this.$axios
        .post("/post", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          this.updateUserZScore();

          if (this.challengeId !== "1") {
            this.updateChallengeStatus();
          } else {
            this.updateDailyMissionStatus();
          }

          this.$router.push({
            name: "InProgressChallenge",
            params: {
              challengeId: this.$route.params.challengeId,
            },
          });
        })
        .catch(() => {});
    },
    // 게시글 업로드에 따른 사용자 Z 점수 수정
    updateUserZScore() {
      this.$axios({
        url: "/user/zscore/",
        method: "PUT",
        params: {
          userId: this.userId,
        },
      })
        .then(() => {})
        .catch(() => {});
    },
    // 게시글 업로드에 따른 사용자의 챌린지 진행 사항 수정
    updateChallengeStatus() {
      this.$axios({
        url: `/challenge/status/${this.challengeId}/${this.userId}`,
        method: "PUT",
      })
        .then(() => {})
        .catch(() => {});
    },
    // 데일리 미션을 업로드하는 사용자의 경우 챌린지 진행 사항 수정
    updateDailyMissionStatus() {
      this.$axios({
        url: `/challenge/status/${this.challengeId}/${this.userId}`,
        method: "GET",
      })
        .then(() => {
          this.updateChallengeStatus();
        })
        .catch(() => {
          this.joinDailyMission();
        });
    },
    // 현재 사용자의 해당 챌린지 참여 현황 추가
    joinDailyMission() {
      this.$axios({
        url: "/challenge/status/",
        method: "POST",
        data: {
          challengeId: this.challengeId,
          userId: this.userId,
        },
      })
        .then(() => {
          this.updateChallengeStatus();
        })
        .catch(() => {});
    },
  },
};
</script>
