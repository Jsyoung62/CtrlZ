<template>
  <div class="board">
    <div class="boardHeader">
      <h1>{{ title }}</h1>
      <p>
        (5개)
      </p>
      <a href="#">
        <span class="material-icons">
          expand_less
        </span>
      </a>
    </div>
    <div class="boardContents" />
  </div>
</template>
<script>
import "../css/main/board.scss";

export default {
  name: "Board",
  props: {
    title: {
      type: String,
      required: true,
    },
  },
};
</script>
