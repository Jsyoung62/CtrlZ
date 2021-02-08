<template>
  <div class="zbti test">
    <div class="question">
      {{ zbtiTest[testId].testQuestion }}
    </div>
    <div class="answer" @click="handleAnswerClick(1)">
      {{ zbtiTest[testId].testAnswer1 }}
    </div>
    <div class="answer" @click="handleAnswerClick(2)">
      {{ zbtiTest[testId].testAnswer2 }}
    </div>
    <div class="answer" @click="handleAnswerClick(3)">
      {{ zbtiTest[testId].testAnswer3 }}
    </div>
    <div class="answer" @click="handleAnswerClick(4)">
      {{ zbtiTest[testId].testAnswer4 }}
    </div>
    <progress :value="value" max="100"></progress>
  </div>
</template>

<script>
import axios from "axios";
import "@/components/css/zbti/index.scss";
import "@/components/css/zbti/zbtiTest.scss";

axios.defaults.baseURL = "https://i4a202.p.ssafy.io:8888";

export default {
  name: "ZbtiTest",
  data: () => {
    return {
      zbtiTest: [],
      testId: 0,
      testResult: "",
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
      console.log(`ZBTI 테스트 답안 버튼 동작 ${answer}`);

      if (this.testId + 1 === this.zbtiTest.length) {
        this.$router.push({
          name: "ZbtiResult",
          params: {
            zbtiId: "A",
          },
        });
      }

      this.testId = this.testId + 1;
    },
  },
};
</script>
