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
      <textarea rows="30"></textarea>
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
      postImage: "",
      mission: "",
    };
  },
  created() {
    console.log("POST UPLOAD");
    this.postImage = this.$route.params.postImage;
    console.log(this.postImage);
  },
  methods: {
    handleUploadClick() {
      console.log("Upload");
    },
  },
};
</script>
