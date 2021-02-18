<template>
  <div class="modalMask" @click.self="$emit('close')">
    <div class="modalWrapper">
      <div class="modalTitle">
        {{ user.userName }}
      </div>
      <div class="modalContent">
        {{ user.userIntroduce }}
        <div v-show="user.userImage != null" class="modalImageWrapper">
          <img class="modalImage" :src="user.userImage" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "@/components/css/uploadModal.scss";

export default {
  name: "ProfileModal",
  props: {
    user: {
      type: Object,
      required: true,
    },
  },
};
</script>
