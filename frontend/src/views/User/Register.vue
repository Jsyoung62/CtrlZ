<template>
  <div class="register">
    <div v-if="registerSuccess" class="container">
      <Title title="축하합니다!" />
      <p class="registerSuccessText">
        회원가입에 성공했습니다
      </p>
      <router-link to="/login" tag="button" class="toLoginButton">
        로그인하러 가기
      </router-link>
    </div>
    <div v-else class="container">
      <Title title="회원가입" />
      <form action="" method="post">
        <div>
          <label for="userName">닉네임</label>
          <input id="userName" v-model="userName" type="text" />
        </div>

        <div>
          <label for="userEmail">이메일</label>
          <input id="userEmail" v-model="userEmail" type="email" />
        </div>

        <div>
          <label for="userPassword">비밀번호</label>
          <input id="userPassword" v-model="userPassword" type="password" />
        </div>

        <div>
          <label for="password">비밀번호 확인</label>
          <input id="passwordConfirm" v-model="passwordConfirm" type="password" />
        </div>
      </form>

      <button type="submit" class="registerButton" @click="checkForm">
        회원가입
      </button>
    </div>
  </div>
</template>
<script>
import Title from "@/components/user/title.vue";

export default {
  name: "Register",
  components: {
    Title,
  },
  data: () => {
    return {
      userName: "",
      userEmail: "",
      userPassword: "",
      passwordConfirm: "",
      validateUserName: false,
      validateUserEmail: false,
      validateUserPassword: false,
      registerSuccess: false,
    };
  },
  watch: {
    userName() {
      this.validateUserName = this.checkUserName(this.userName);
    },
    userEmail() {
      this.validateUserEmail = this.checkEmail(this.userEmail);
    },
    userPassword() {
      this.validateUserPassword = this.checkPassword(this.userPassword);
    },
  },
  methods: {
    checkUserName(userName) {
      return !/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/.test(userName) && userName.length >= 1 && userName.length < 20;
    },
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
      //   if (this.validateUserName && this.validateUserEmail && this.validateUserPassword) {
      this.registerSuccess = true;
      return true;
      //   }
    },
  },
};
</script>
