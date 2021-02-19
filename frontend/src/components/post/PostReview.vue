<template>
  <div class="postReview">
    <div class="iconWrapper" @click="handleLikeClick">
      <span class="material-icons" :class="{ green: isActive }">
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
      url: "/post/like/select",
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
          .catch(() => {});
      } else {
        this.$router.push("/login");
      }
    },
    handleCommentClick() {
      this.$router.push({
        name: "Comment",
        params: {
          postId: this.postId,
        },
      });
    },
    handleMoreClick() {
      // 게시글 기능 추가 구현
    },
  },
};
</script>
