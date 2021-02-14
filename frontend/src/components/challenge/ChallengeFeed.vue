<template>
  <div class="challengeFeed">
    <div
      v-for="(post, index) in posts"
      :key="post.postId"
      class="feed"
      @click="handleMissionDetail(index)"
    >
      <img :src="post.postImage" />
    </div>
  </div>
</template>

<script>
export default {
  name: "ChallengeFeed",
  props: {
    posts: {
      type: Array,
      required: true,
    },
  },
  data: () => {
    return {
      postId: "",
    };
  },
  methods: {
    handleMissionDetail(index) {
      this.postId = this.posts[index].postId;
      this.$router.push({
        name: "Post",
        params: {
          postId: this.postId,
        },
      });
    },
  },
};
</script>
