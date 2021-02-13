<template>
  <div class="post">
    <img class="postImage" :src="post.postImage" />
    <PostReview :like-cnt="post.postLike" :comment-cnt="4456" />
    <div class="user">
      <img :src="user.userImage" class="userImage" />
      <span>{{ user.userName }}</span>
    </div>
    <div class="postContent">
      {{ post.postContent }}
    </div>
  </div>
</template>

<script>
import PostReview from "@/components/post/postReview.vue";
import "@/components/css/post/post.scss";

export default {
  name: "Post",
  components: {
    PostReview,
  },
  data: () => {
    return {
      post: "",
      user: "",
    };
  },
  created() {
    this.$axios({
      url: "/post/detail",
      method: "GET",
      params: {
        postId: "1",
      },
    })
      .then((response) => {
        this.post = response.data;

        this.$axios({
          url: "/user/profile",
          method: "GET",
          params: {
            userId: this.post.userId,
          },
        })
          .then((response) => {
            this.user = response.data;
            console.log(this.user);
          })
          .catch((error) => {
            console.error(error);
          });
      })
      .catch((error) => {
        console.error(error);
      });
  },
};
</script>
