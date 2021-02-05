<template>
  <div class="findPassword container">
    <Title title="비밀번호 찾기" />

    <form action="" method="post">
      <div>
        <label for="email">이메일</label>
        <input id="email" v-model="email" type="email" :class="emailStatus" />
      </div>
    </form>

    <button type="submit" class="findButton" @click="sendEmail">
      인증메일 보내기
    </button>
  </div>
</template>
<script>
import Title from "@/components/user/title.vue";
import "@/components/css/user/index.scss";

export default {
  name: "FindPassword",
  components: {
    Title,
  },
  data: () => {
    return {
      email: "",
      validateEmail: false,
      emailStatus: "",
    };
  },
  watch: {
    email() {
      this.validateEmail = this.checkEmail(this.email);
      this.validateEmail ? (this.emailStatus = "success") : (this.emailStatus = "fail");
    },
  },
  methods: {
    checkEmail(email) {
      const re = /^(([^<>()\\[\]\\.,;:\s@"]+(\.[^<>()\\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    sendEmail() {
      if (this.validateEmail) {
        console.log("sendEmail");
      }
    },
  },
};
</script>
