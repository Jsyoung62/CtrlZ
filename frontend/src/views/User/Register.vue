<template>
  <div class="register">
    <div v-if="registerSuccess" class="userContainer">
      <Title title="축하합니다!" />
      <p class="registerSuccessText">
        회원가입에 성공했습니다
      </p>
      <router-link to="/login" tag="button" class="toLoginButton">
        로그인하러 가기
      </router-link>
    </div>
    <div v-else class="userContainer">
      <Title title="회원가입" />
      <form action="" method="post">
        <div>
          <label for="userName">닉네임</label>
          <input id="userName" v-model="userName" type="text" :class="userNameStatus" />
        </div>

        <div>
          <label for="userEmail">이메일</label>
          <input id="userEmail" v-model="userEmail" type="email" :class="userEmailStatus" />
        </div>

        <div>
          <label for="userPassword">비밀번호</label>
          <input
            id="userPassword"
            v-model="userPassword"
            type="password"
            :class="userPasswordStatus"
          />
        </div>

        <div>
          <label for="password">비밀번호 확인</label>
          <input
            id="passwordConfirm"
            v-model="passwordConfirm"
            type="password"
            :class="passwordConfirmStatus"
          />
        </div>
      </form>

      <button type="submit" class="registerButton" @click="register">
        회원가입
      </button>
    </div>
  </div>
</template>
<script>
import Title from "@/components/user/Title.vue";
import "@/components/css/user/index.scss";

export default {
  name: "Register",
  components: {
    Title,
  },
  data: () => {
    return {
      userType: "N",
      userName: "",
      userEmail: "",
      userPassword: "",
      passwordConfirm: "",
      validateUserName: false,
      validateUserEmail: false,
      validateUserPassword: false,
      registerSuccess: false,
      userNameStatus: "",
      userEmailStatus: "",
      userPasswordStatus: "",
      passwordConfirmStatus: "",
    };
  },
  watch: {
    userName() {
      this.validateUserName = this.checkUserName(this.userName);
      this.validateUserName ? (this.userNameStatus = "success") : (this.userNameStatus = "fail");
    },
    userEmail() {
      this.validateUserEmail = this.checkEmail(this.userEmail);
      this.validateUserEmail ? (this.userEmailStatus = "success") : (this.userEmailStatus = "fail");
    },
    userPassword() {
      this.validateUserPassword = this.checkPassword(this.userPassword);
      this.checkPasswordConfirm()
        ? (this.passwordConfirmStatus = "success")
        : (this.passwordConfirmStatus = "fail");
      this.validateUserPassword
        ? (this.userPasswordStatus = "success")
        : (this.userPasswordStatus = "fail");
    },
    passwordConfirm() {
      this.checkPasswordConfirm()
        ? (this.passwordConfirmStatus = "success")
        : (this.passwordConfirmStatus = "fail");
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
    checkPasswordConfirm() {
      if (this.passwordConfirm === "") return false;
      return this.userPassword === this.passwordConfirm;
    },
    checkForm() {
      if (
        this.validateUserName &&
        this.validateUserEmail &&
        this.validateUserPassword &&
        this.password === this.passwordConfirm
      ) {
        return true;
      }
    },
    register() {
      if (this.checkForm) {
        this.$axios({
          url: "http://i4a202.p.ssafy.io:8888/user/register",
          method: "POST",
          data: {
            userName: this.userName,
            userEmail: this.userEmail,
            userPassword: this.userPassword,
            userType: this.userType,
          },
        })
          .then(() => {
            this.registerSuccess = true;
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
  },
};
</script>
