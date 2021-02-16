<template>
  <div class="postReview">
    <div class="iconWrapper" @click="handleLikeClick">
      <span class="material-icons" :class="{ greenlike: isActive }">
        eco
      </span>
      <p>
        {{ likeCnt | numberWithComma }}
      </p>
    </div>
    <div class="iconWrapper" @click="handleCommentClick">
      <span class="material-icons">
        chat_bubble_outline
      </span>
      <p>
        {{ commentCnt | numberWithComma }}
      </p>
    </div>
    <div class="iconWrapper" @click="handleMoreClick">
      <span class="material-icons">
        more_horiz
      </span>
    </div>
  </div>
</template>
<style scoped>
.material-icons.greenlike {
  color: #4a6f53;
}
</style>
<script>
import "@/components/css/post/postReview.scss";

export default {
  name: "PostReview",
  filters: {
    numberWithComma(num) {
      return String(num).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  props: {
    likeCnt: {
      type: Number,
      required: true,
    },
    commentCnt: {
      type: Number,
      required: true,
    },
    postId: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      isActive: "",
      postLikeId: "",
    };
  },

  created() {
    this.$axios({
      url: "http://localhost:8888/post/like/select",
      method: "GET",
      params: {
        postId: this.postId,
        userId: this.$store.state.userInfo.userId,
      },
    }).then((response) => {
      this.postLikeId = response.data;
      this.isActive = true;
    });
  },
  methods: {
    handleLikeClick() {
      if (this.$store.state.userInfo.userId > 0) {
        this.$axios({
          url: "/post/like",
          method: "POST",
          params: {
            postId: this.postId,
            userId: this.$store.state.userInfo.userId,
          },
        })
          .then(() => {
            this.isActive = true;
            location.reload();
          })
          .catch((error) => {
            console.error(error);
          });
        console.log("POST LIKE");
      } else {
        this.$router.push("/login");
      }
    },
    handleCommentClick() {
      console.log("POST COMMENT");
    },
    handleMoreClick() {
      console.log("POST REVIEW MORE");
    },
  },
};
</script>
