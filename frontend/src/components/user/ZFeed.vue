<template>
  <div class="zfeed">
    <div class="boardHeader">
      <h1>Z feed</h1>
      <span class="material-icons" @click="onClickExpand">
        {{ expand }}
      </span>
    </div>

    <div v-show="showBoard" class="board">
      <div
        v-for="(post, index) in data"
        :key="index"
        class="feed"
        @click="handlePostClick(post.postId)"
      >
        <img :src="post.postImage" />
      </div>
    </div>
  </div>
</template>
<script>
import "@/components/css/user/zfeed.scss";
export default {
  name: "ZFeed",
  props: {
    data: {
      type: Array,
      required: true,
    },
  },
  data: () => {
    return {
      expand: "expand_less",
      showBoard: true,
    };
  },
  methods: {
    onClickExpand() {
      this.expand = this.expand === "expand_less" ? "expand_more" : "expand_less";
      this.showBoard = !this.showBoard;
    },
    handlePostClick(postId) {
      this.$router.push({
        name: "Post",
        params: {
          postId,
        },
      });
    },
  },
};
</script>
