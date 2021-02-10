<template>
  <div class="modalMask" @click.self="$emit('close')">
    <div class="modalWrapper">
      <div class="modalTitle">
        <slot name="modalTitle"></slot>
      </div>
      <div class="modalContent">
        <slot name="modalContent"></slot>
        <div v-show="isImage">
          <slot name="modalImage"></slot>
        </div>
      </div>
      <div class="modalButton">
        <label for="uploadImage" class="uploadImageButton">
          인증샷 올리기
        </label>
        <input
          id="uploadImage"
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
  name: "MissionModal",
  props: {
    isImage: {
      type: Boolean,
      default: true,
      required: false,
    },
  },
  data() {
    return {
      postImage: "",
    };
  },
  methods: {
    uploadImage(evt) {
      const files = evt.target.files;

      if (!files.length) {
        return;
      }

      const reader = new FileReader();
      reader.readAsDataURL(files[0]);
      reader.onload = (evt) => {
        this.postImage = evt.target.result;
        console.log(this.postImage);

        if (this.postImage !== null) {
          this.movePostUpload();
        }
      };

      document.querySelector("#uploadImage").value = "";
    },
    movePostUpload() {
      this.$router.push({
        name: "PostUpload",
        params: {
          postImage: this.postImage,
        },
      });
    },
  },
};
</script>
