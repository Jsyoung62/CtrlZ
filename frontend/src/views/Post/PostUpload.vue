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
      userId: "",
      postThumbnail: "",
      postContent: "",
      missionTitle: "",
    };
  },
  created() {
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
      // image 업로드를 위한 formData
      const formData = new FormData();
      formData.append("postImage", this.$route.params.postImage);
      formData.append("postContent", this.postContent);
      formData.append("userId", this.userId);
      formData.append("challengeId", this.$route.params.challengeId);

      this.$axios
        .post("/post", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          alert("업로드 완료");
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
  },
};
</script>
