<template>
  <div class="postUpload">
    <div class="postHeader">
      <span class="title" @click="handlePostUploadClick">
        인증
      </span>
    </div>
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
import "@/components/css/post/index.scss";
import "@/components/css/post/postUpload.scss";

export default {
  name: "PostUpload",
  data() {
    return {
      userId: "",
      mission: "",
      postImage: "",
      postThumbnail: "",
      postContent: "",
      missionTitle: "",
      challengeId: "3",
    };
  },
  created() {
    this.postImage = this.$route.params.postImage;
    this.userId = this.$store.$route.userInfo.userId;
    const reader = new FileReader();
    reader.readAsDataURL(this.postImage);
    reader.onload = (evt) => {
      this.postThumbnail = evt.target.result;
    };

    this.missionTitle = this.$route.params.missionTitle;
  },
  methods: {
    handlePostUploadClick() {
      // image 업로드를 위한 formData
      const vm = this;
      const formData = new FormData();
      formData.append("postImage", vm.postImage);
      formData.append("postContent", vm.postContent);
      formData.append("userId", vm.userId);
      formData.append("challengeId", vm.challengeId);

      this.$axios
        .post("/post", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          console.log(response.data);
          alert("업로드 완료");
          this.$router.push("/challenge/inprogress");
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
