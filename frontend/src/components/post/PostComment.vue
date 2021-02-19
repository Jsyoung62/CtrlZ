<template>
  <div class="postComment">
    <ProfileModal v-show="isModalViewed" :user="user" @close="isModalViewed = false" />
    <img :src="user.userImage" class="userImage" @click="handleUserClick" />
    <p>
      <span class="userName">{{ user.userName }}</span>
      <span class="comment">
        {{ comment }}
      </span>
    </p>
  </div>
</template>

<script>
import ProfileModal from "@/components/user/ProfileModal.vue";
import "@/components/css/post/postComment.scss";
import "@/components/css/post/post.scss";

export default {
  name: "PostComment",
  components: {
    ProfileModal,
  },
  props: {
    comment: {
      type: String,
      required: true,
    },
    user: {
      type: Object,
      required: true,
    },
  },
  data: () => {
    return {
      isModalViewed: false,
    };
  },
  methods: {
    handleUserClick() {
      this.isModalViewed = true;
    },
  },
};
</script>
