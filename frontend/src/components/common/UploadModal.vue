<template>
  <div class="modalMask" @click.self="$emit('close')">
    <div class="modalWrapper">
      <div class="modalTitle">
        {{ mission.missionTitle }}
      </div>
      <div class="modalContent">
        {{ mission.missionContent }}
        <div v-show="mission.missionImage != null">
          <img class="modalImage" :src="mission.missionImage" />
        </div>
      </div>
      <div class="modalButton">
        <label for="uploadImage" class="uploadImageButton">
          인증샷 올리기
        </label>
        <input
          id="uploadImage"
          ref="postImage"
          type="file"
          accept="image/*"
          name="uploadImage"
          class="uploadImage"
          @change="uploadImage"
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
  },
  data() {
    return {
      postImage: "",
    };
  },
  methods: {
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
