<template>
  <div class="modalMask" @click.self="$emit('close')">
    <div class="modalWrapper">
      <div class="modalTitle">
        {{ mission.missionTitle }}
      </div>
      <div class="modalContent">
        {{ mission.missionContent }}
        <div v-show="mission.missionImage != null" class="modalImageWrapper">
          <img class="modalImage" :src="mission.missionImage" />
        </div>
      </div>
      <div v-show="isButton" class="modalButton">
        <label v-if="isUser" for="uploadImage" class="uploadImageButton">
          인증샷 올리기
        </label>
        <label v-else for="loginButton" class="uploadImageButton">
          로그인 후 이용해주세요
        </label>
        <input
          v-if="isUser"
          id="uploadImage"
          ref="postImage"
          type="file"
          accept="image/*"
          name="uploadImage"
          class="uploadImage"
          @change="uploadImage"
        />
        <input
          v-else
          id="loginButton"
          type="button"
          name="loginButton"
          class="uploadImage"
          @click="handleLoginButtonClick"
        />
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/uploadModal.scss";

export default {
  name: "UploadModal",
  props: {
    mission: {
      type: Object,
      required: true,
    },
    isButton: {
      type: Boolean,
      required: true,
    },
  },
  data() {
    return {
      postImage: "",
      isUser: false,
    };
  },
  created() {
    if (this.$store.state.userInfo.userId !== "") {
      this.isUser = true;
    }
  },
  methods: {
    handleLoginButtonClick() {
      this.$router.push("/login");
    },
    uploadImage() {
      this.postImage = this.$refs.postImage.files[0];
      if (this.postImage !== null) {
        this.movePostUpload();
      }
      document.querySelector("#uploadImage").value = "";
    },
    movePostUpload() {
      this.$router.push({
        name: "PostUpload",
        params: {
          challengeId: this.mission.id.challengeId,
          missionId: this.mission.id.missionId,
          postImage: this.postImage,
          missionTitle: this.mission.missionTitle,
        },
      });
    },
  },
};
</script>
