<template>
  <div class="categories">
    <div class="category type1" @click="handleCategoryClick('dailyChallenges')">
      <div class="text">
        <p class="type">
          일상
        </p>
        <p class="total">
          {{ dailyLength }}
        </p>
      </div>
    </div>
    <div class="category type2" @click="handleCategoryClick('foodChallenges')">
      <div class="text">
        <p class="type">
          음식
        </p>
        <p class="total">
          {{ foodLength }}
        </p>
      </div>
    </div>
    <div class="category type3" @click="handleCategoryClick('fashionChallenges')">
      <div class="text">
        <p class="type">
          패션
        </p>
        <p class="total">
          {{ fashionLength }}
        </p>
      </div>
    </div>
    <div class="category type4" @click="handleCategoryClick('activityChallenges')">
      <div class="text">
        <p class="type">
          활동
        </p>
        <p class="total">
          {{ activityLength }}
        </p>
      </div>
    </div>
  </div>
</template>
<script>
import "@/components/css/challenge/categories.scss";

export default {
  name: "Categories",
  props: {
    dailyLength: {
      type: Number,
      required: true,
    },
    foodLength: {
      type: Number,
      required: true,
    },
    fashionLength: {
      type: Number,
      required: true,
    },
    activityLength: {
      type: Number,
      required: true,
    },
  },
  methods: {
    handleCategoryClick(type) {
      this.$emit("division", type);
    },
  },
};
</script>
