<template>
  <div class="comment">
    <Header left-icon="chevron_left" />
    <Navigation />

    <PostComment
      v-for="comment in comments"
      :key="comment.commentId"
      :user-name="comment.userId"
      :comment="comment.commentContent"
    />
    <CommentWrite />
  </div>
</template>
<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import PostComment from "@/components/post/PostComment.vue";
import CommentWrite from "@/components/post/CommentWrite.vue";
import axios from "axios";

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
      comments: [],
    };
  },
  created() {
    this.getComments(this.$route.params.postId);
  },
  methods: {
    getComments(postId) {
      axios({
        url: "http://i4a202.p.ssafy.io:8888/comment/find",
        method: "GET",
        params: {
          postId,
        },
      })
        .then((response) => {
          this.comments = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
