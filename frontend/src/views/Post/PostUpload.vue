<template>
  <div class="postUpload">
    <div class="postHeader">
      <span class="title" @click="handleUploadClick">
        인증
      </span>
    </div>
    <div class="postUploadImage">
      <img class="selectedImage" :src="postImage" />
      <span>mission.missionTitle</span>
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
      user: "",
      mission: "",
      postImage: "",
      postContent: "",
    };
  },
  created() {
    this.postImage = this.$route.params.postImage;
  },
  methods: {
    handleUploadClick() {
      this.$axios({
        url: "/post",
        method: "POST",
        params: {
          challengeId: this.mission.challengeId,
          userId: this.user.userId,
          postContent: this.postContent,
          postImage: this.postImage,
        },
      })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
