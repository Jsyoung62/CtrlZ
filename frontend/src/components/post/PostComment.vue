<template>
  <div class="postComment">
    <img :src="userImage" class="userImage" />
    <p>
      <span class="userName">{{ userName }}</span>
      <span class="comment">
        {{ comment }}
      </span>
    </p>
  </div>
</template>

<script>
import "@/components/css/post/postComment.scss";

export default {
  name: "PostComment",
  props: {
    userName: {
      type: String,
      required: true,
    },
    comment: {
      type: String,
      required: true,
    },
    userImage: {
      type: String,
      default: "@/assets/badge.png",
      required: false,
    },
  },
};
</script>
