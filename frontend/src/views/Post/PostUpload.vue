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
          this.updateChallengeStatus();

          this.$router.push({
            name: "InProgressChallenge",
            params: {
              challengeId: this.$route.params.challengeId,
            },
          });
        })
        .catch((error) => {
          console.error(error);
        });
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
        .catch((error) => {
          console.error(error);
        });
    },
    // 게시글 업로드에 따른 사용자의 챌린지 진행 사항 수정
    updateChallengeStatus() {
      this.$axios({
        url: `/challenge/status/${this.challengeId}/${this.userId}`,
        method: "PUT",
      })
        .then(() => {})
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
