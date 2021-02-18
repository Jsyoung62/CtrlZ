<template>
  <div class="comment">
    <Header left-icon="chevron_left" />
    <Navigation />
    <div class="comments">
      <PostComment
        v-for="comment in comments"
        :key="comment.commentId"
        :user-image="comment.user.userImage"
        :user-name="comment.user.userName"
        :comment="comment.commentContent"
      />
    </div>
    <CommentWrite v-show="isUser" :user-image="user.userImage" />
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import PostComment from "@/components/post/PostComment.vue";
import CommentWrite from "@/components/post/CommentWrite.vue";

export default {
  name: "Comment",
  components: {
    Header,
    Navigation,
    PostComment,
    CommentWrite,
  },
  data: () => {
    return {
      user: "",
      comments: [],
      isUser: "",
    };
  },
  created() {
    this.getComments(this.$route.params.postId);

    if (this.$store.state.userInfo.userId > 0) {
      this.isUser = true;
    } else {
      this.isUser = false;
    }
  },
  methods: {
    getComments(postId) {
      this.$axios({
        url: "/comment/find",
        method: "GET",
        params: {
          postId,
        },
      })
        .then((response) => {
          this.comments = response.data.map((comment) => {
            if (comment.user.userImage === null) {
              comment.user.userImage = "http://i4a202.p.ssafy.io/img/no_profile.png";
            }
            return comment;
          });
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
