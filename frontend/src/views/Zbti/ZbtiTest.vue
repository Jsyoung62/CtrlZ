<template>
  <div class="zbti test">
    <div class="question">
      {{ zbtiTest[testId].testQuestion }}
    </div>
    <div class="answer" @click="handleAnswerClick(0)">
      {{ zbtiTest[testId].testAnswer1 }}
    </div>
    <div class="answer" @click="handleAnswerClick(1)">
      {{ zbtiTest[testId].testAnswer2 }}
    </div>
    <div class="answer" @click="handleAnswerClick(2)">
      {{ zbtiTest[testId].testAnswer3 }}
    </div>
    <div class="answer" @click="handleAnswerClick(3)">
      {{ zbtiTest[testId].testAnswer4 }}
    </div>
    <progress :value="value" max="100"></progress>
    <!-- <img :src="zbtiTest[testId].testImage" /> -->
  </div>
</template>

<script>
import axios from "axios";
import "@/components/css/zbti/zbtiTest.scss";

axios.defaults.baseURL = "https://i4a202.p.ssafy.io:8888";

export default {
  name: "ZbtiTest",
  data: () => {
    return {
      zbtiTest: [],
      testId: 0,
      selectCount: [],
      zbtiResult: "A",
    };
  },
  computed: {
    value() {
      return (100 / this.zbtiTest.length) * (this.testId + 1);
    },
  },
  created() {
    axios({
      url: "/zbti/",
      method: "GET",
    })
      .then((response) => {
        this.zbtiTest = response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    handleAnswerClick(answer) {
      console.log(`ZBTI 테스트 답안 버튼 동작 ${answer + 1}`);

      if (typeof this.selectCount[answer] === "undefined") {
        this.selectCount[answer] = 0;
      }

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
      if (index === 2) this.zbtiResult = "A";
      else if (index === 1) this.zbtiResult = "B";
      else if (index === 0) this.zbtiResult = "C";
      else this.zbtiResult = "D";
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
