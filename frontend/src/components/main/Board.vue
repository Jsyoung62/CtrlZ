<template>
  <div class="board">
    <div class="boardHeader">
      <h1>{{ title }}</h1>
      <p>
        (5개)
      </p>
      <span class="material-icons" @click="onClickExpand">
        {{ expand }}
      </span>
    </div>
    <div v-show="showBoard" class="boardContents">
      <Badge name="텀블러브유" date="2020.12.18" />
      <Badge name="명품에코백" date="2021.1.20" />
      <Badge name="텀블러브유" date="2020.12.18" />
      <Badge name="텀블러브유" date="2020.12.18" />
      <Badge name="텀블러브유" date="2020.12.18" />
      <Badge name="텀블러브유" date="2020.12.18" />
    </div>
  </div>
</template>
<script>
import Badge from "./Badge.vue";
import "../css/main/board.scss";

export default {
  name: "Board",
  components: {
    Badge,
  },
  props: {
    title: {
      type: String,
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
  },
};
</script>
