<template>
  <div class="categories">
    <div
      :class="['category', 'type1', { active: type1Active }]"
      @click="handleCategoryClick('dailyChallenges', 'type1Active')"
    >
      <div class="text">
        <p class="type">
          일상
        </p>
        <p class="total">
          {{ dailyLength }}
        </p>
      </div>
    </div>
    <div
      :class="['category', 'type2', { active: type2Active }]"
      @click="handleCategoryClick('foodChallenges', 'type2Active')"
    >
      <div class="text">
        <p class="type">
          음식
        </p>
        <p class="total">
          {{ foodLength }}
        </p>
      </div>
    </div>
    <div
      :class="['category', 'type3', { active: type3Active }]"
      @click="handleCategoryClick('fashionChallenges', 'type3Active')"
    >
      <div class="text">
        <p class="type">
          패션
        </p>
        <p class="total">
          {{ fashionLength }}
        </p>
      </div>
    </div>
    <div
      :class="['category', 'type4', { active: type4Active }]"
      @click="handleCategoryClick('activityChallenges', 'type4Active')"
    >
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
  data: () => {
    return {
      type1Active: false,
      type2Active: false,
      type3Active: false,
      type4Active: false,
    };
  },
  methods: {
    handleCategoryClick(type, activeClass) {
      this.resetActive();
      this[activeClass] = true;
      this.$emit("division", type);
    },
    resetActive() {
      this.type1Active = false;
      this.type2Active = false;
      this.type3Active = false;
      this.type4Active = false;
    },
  },
};
</script>
