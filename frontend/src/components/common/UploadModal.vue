<template>
  <div class="modalMask" @click.self="$emit('close')">
    <div class="modalWrapper">
      <div class="modalTitle">
        <slot name="modalTitle"></slot>
      </div>
      <div class="modalContent">
        <slot name="modalContent"></slot>
        <div v-show="missionImage != null">
          <img class="modalImage" :src="missionImage" />
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
    missionImage: {
      type: String,
      required: true,
    },
    missionTitle: {
      type: String,
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
          postImage: this.postImage,
          missionTitle: this.missionTitle,
        },
      });
    },
  },
};
</script>
