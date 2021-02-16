<template>
  <div>
    <Header left-icon="chevron_left" />
    <Navigation />

    <div class="post">
      <img class="postImage" :src="post.postImage" />
      <PostReview :like-cnt="post.postLike" :comment-cnt="4456" :post-id="post.postId" />
      <div class="user">
        <img :src="post.user.userImage" class="userImage" />
        <span>{{ post.user.userName }}</span>
      </div>
      <div class="postContent">
        {{ post.postContent }}
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import Navigation from "@/components/common/Navigation.vue";
import PostReview from "@/components/post/PostReview.vue";
import "@/components/css/post/post.scss";

export default {
  name: "Post",
  components: {
    Header,
    Navigation,
    PostReview,
  },
  data: () => {
    return {
      post: "",
      postId: "",
    };
  },
  created() {
    // postId에 맞는 게시글 불러오기
    this.$axios({
      url: "/post/detail",
      method: "GET",
      params: {
        postId: this.$route.params.postId,
      },
    })
      .then((response) => {
        this.post = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {},
};
</script>
