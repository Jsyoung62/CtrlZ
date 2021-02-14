<template>
  <div class="commentWrite">
    <img :src="require('@/assets/' + userImage)" class="userImage" />
    <input v-model="comment" type="text" class="comment" placeholder="댓글 남기기..." />
    <button class="uploadComment" @click="handleCommentUploadClick">
      게시
    </button>
  </div>
</template>
<script>
import "@/components/css/post/postComment.scss";
export default {
  name: "CommentWrite",
  date: () => {
    return {
      userName: "",
      userImage: "",
      comment: "",
    };
  },
  created() {
    this.userName = this.$store.state.userInfo.userName;
    this.userImage = this.$store.state.userInfo.userImage;
  },
  methods: {
    handleCommentUploadClick() {
      console.log("댓글 업로드");
    },
  },
};
</script>
