<template>
  <div class="zbti test">
    <img :src="zbtiTest[testId].testImage" class="testBackground" />
    <div class="question">
      {{ zbtiTest[testId].testQuestion }}
    </div>
    <div class="answer" @click="handleAnswerClick(2)">
      {{ zbtiTest[testId].testAnswer1 }}
    </div>
    <div class="answer" @click="handleAnswerClick(1)">
      {{ zbtiTest[testId].testAnswer2 }}
    </div>
    <div class="answer" @click="handleAnswerClick(0)">
      {{ zbtiTest[testId].testAnswer3 }}
    </div>
    <div class="answer" @click="handleAnswerClick(3)">
      {{ zbtiTest[testId].testAnswer4 }}
    </div>
    <progress :value="value" max="100"></progress>
  </div>
</template>

<script>
import "@/components/css/zbti/zbtiTest.scss";

export default {
  name: "ZbtiTest",
  data: () => {
    return {
      zbtiTest: [],
      testId: 0,
      selectCount: [],
      zbtiResult: "",
    };
  },
  computed: {
    value() {
      return (100 / this.zbtiTest.length) * (this.testId + 1);
    },
  },
  created() {
    this.$axios({
      url: "/zbti/",
      method: "GET",
    })
      .then((response) => {
        this.zbtiTest = response.data;
        this.selectInit();
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    selectInit() {
      this.zbtiTest.forEach((value, index) => {
        this.selectCount[index] = 0;
      });
    },
    handleAnswerClick(answer) {
      this.selectCount[answer] += 1;
      this.testId = this.testId + 1;

      if (this.testId === this.zbtiTest.length) {
        let isSelected = false;
        for (let index = 0; index < 4; index++) {
          if (this.selectCount[index] >= 3) {
            this.indexToAnswer(index);
            isSelected = true;
            break;
          }
        }
        if (isSelected === false) {
          for (let index = 0; index < 4; index++) {
            if (this.selectCount[index] === 2) {
              this.indexToAnswer(index);
              break;
            }
          }
        }
        this.moveZbtiResult();
      }
    },
    indexToAnswer(index) {
      if (index === 2) {
        return (this.zbtiResult = "A");
      }
      if (index === 1) {
        return (this.zbtiResult = "B");
      }
      if (index === 0) {
        return (this.zbtiResult = "C");
      }
      return (this.zbtiResult = "D");
    },
    moveZbtiResult() {
      this.$router.push({
        name: "ZbtiResult",
        params: {
          zbtiId: this.zbtiResult,
        },
      });
    },
  },
};
</script>
