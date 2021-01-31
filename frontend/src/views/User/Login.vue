<template>
  <div class="login">
    <Logo />
    <h1 class="title">
      로그인
    </h1>

    <form action="" method="post">
      <div class="emailWrapper">
        <label for="email">이메일</label>
        <input id="email" v-model="email" type="email" />
      </div>

      <div>
        <label for="password">비밀번호</label>
        <input id="password" v-model="password" type="password" />
      </div>
    </form>

    <a class="findPasswordButton">비밀번호 찾기</a>
    <button type="submit" class="loginButton" @click="checkForm">
      로그인
    </button>
    <p class="joinText">
      계정이 없나요?
      <a class="joinButton">회원가입</a>
    </p>
    <img
      class="googleLoginButton"
      src="@/assets/googleButton/btn_google_signin_light_normal_web.png"
    />
  </div>
</template>
<script>
import Logo from "@/assets/logo.vue";
import "@/components/css/user/login.scss";

export default {
  name: "Login",
  components: {
    Logo,
  },
  data: () => {
    return {
      email: "",
      password: "",
      validateEmail: false,
      validatePassword: false,
    };
  },
  watch: {
    email() {
      this.validateEmail = this.checkEmail(this.email);
    },
    password() {
      this.validatePassword = this.checkPassword(this.password);
    },
  },
  methods: {
    checkEmail(email) {
      const re = /^(([^<>()\\[\]\\.,;:\s@"]+(\.[^<>()\\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    checkPassword(password) {
      return (
        (/[A-Z]/.test(password) || /[a-z]/.test(password)) &&
        /[0-9]/.test(password) &&
        password.length >= 8
      );
    },
    checkForm() {
      if (this.validateEmail && this.validatePassword) {
        return true;
      }
    },
  },
};
</script>
