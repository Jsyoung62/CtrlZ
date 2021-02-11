<template>
  <div class="challengeFeed">
    <div v-for="post in posts" :key="post.postId" class="feed">
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
};
</script>
