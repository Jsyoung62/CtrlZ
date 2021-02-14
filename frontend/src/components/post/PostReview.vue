<template>
  <div class="postReview">
    <div class="iconWrapper" @click="handleLikeClick">
      <span class="material-icons">
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
  },
  methods: {
    handleLikeClick() {
      console.log("POST LIKE");
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
