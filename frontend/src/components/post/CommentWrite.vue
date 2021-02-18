<template>
  <div class="commentWrite">
    <img :src="userImage" class="userImage" />
    <input
      v-model="commentContent"
      type="text"
      class="commentContent"
      placeholder="댓글 남기기..."
      @keyup.enter="handleCommentUploadClick"
    />
    <button class="uploadComment" @click="handleCommentUploadClick">
      게시
    </button>
  </div>
</template>

<script>
import "@/components/css/post/postComment.scss";

export default {
  name: "CommentWrite",
  data: () => {
    return {
      userName: "",
      userImage: "",
      commentContent: "",
    };
  },
  created() {
    this.userName = this.$store.state.userInfo.userName;
    this.userImage = this.$store.state.userInfo.userImage;
  },
  methods: {
    handleCommentUploadClick() {
      if (this.commentContent !== "") {
        this.$axios({
          url: "/comment/add",
          method: "POST",
          data: {
            postId: this.$route.params.postId,
            userId: this.$store.state.userInfo.userId,
            commentContent: this.commentContent,
          },
        })
          .then((response) => {
            console.log(response);
            this.$router.go(this.$router.currentRoute);
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
  },
};
</script>
