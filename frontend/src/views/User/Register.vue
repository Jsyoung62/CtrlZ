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
          <input
            id="userName"
            v-model="userName"
            type="text"
            :class="userNameStatus"
            placeholder="영문, 20자 이내"
            autocomplete="off"
          />
          <span v-show="validateUserName" class="material-icons done">
            done
          </span>
        </div>

        <div>
          <label for="userEmail">이메일</label>
          <input
            id="userEmail"
            v-model="userEmail"
            type="email"
            :class="userEmailStatus"
            placeholder="ex. ssafy@samsung.com"
            autocomplete="off"
          />
          <span v-show="validateUserEmail" class="material-icons done">
            done
          </span>
        </div>

        <div>
          <label for="userPassword">비밀번호</label>
          <input
            id="userPassword"
            v-model="userPassword"
            type="password"
            :class="userPasswordStatus"
            placeholder="영문, 숫자, 8자 이상"
          />
          <span v-show="validateUserPassword" class="material-icons done">
            done
          </span>
        </div>

        <div>
          <label for="password">비밀번호 확인</label>
          <input
            id="passwordConfirm"
            v-model="passwordConfirm"
            type="password"
            :class="passwordConfirmStatus"
          />
          <span v-show="passwordConfirmStatus === 'success'" class="material-icons done">
            done
          </span>
        </div>
      </form>

      <button type="submit" class="registerButton" :disabled="!isSubmit" @click="register">
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
      isSubmit: false,
    };
  },
  watch: {
    userName() {
      if (this.checkUserName(this.userName)) {
        this.uniqueName(this.userName);
      } else {
        this.userNameStatus = "fail";
        this.validateUserName = false;
        this.checkForm();
      }
    },
    userEmail() {
      if (this.checkEmail(this.userEmail)) {
        this.uniqueEmail(this.userEmail);
      } else {
        this.userEmailStatus = "fail";
        this.validateUserEmail = false;
        this.checkForm();
      }
    },
    userPassword() {
      this.validateUserPassword = this.checkPassword(this.userPassword);
      this.checkPasswordConfirm()
        ? (this.passwordConfirmStatus = "success")
        : (this.passwordConfirmStatus = "fail");
      this.validateUserPassword
        ? (this.userPasswordStatus = "success")
        : (this.userPasswordStatus = "fail");

      this.checkForm();
    },
    passwordConfirm() {
      this.checkPasswordConfirm() && this.checkPassword(this.userPassword)
        ? (this.passwordConfirmStatus = "success")
        : (this.passwordConfirmStatus = "fail");

      this.checkForm();
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
        this.userPassword === this.passwordConfirm
      ) {
        this.isSubmit = true;
        return true;
      } else {
        this.isSubmit = false;
        return false;
      }
    },
    uniqueName(userName) {
      this.$axios({
        url: "/user/namecheck",
        method: "GET",
        params: {
          userName,
        },
      })
        .then((response) => {
          const uniqueName = response.data;
          this.validateUserName = this.checkUserName(this.userName) && uniqueName;
          this.validateUserName
            ? (this.userNameStatus = "success")
            : (this.userNameStatus = "fail");
          this.checkForm();
        })
        .catch(() => {});
    },
    uniqueEmail(userEmail) {
      this.$axios({
        url: "/user/emailcheck",
        method: "GET",
        params: {
          userEmail,
        },
      })
        .then((response) => {
          const uniqueEmail = response.data;
          this.validateUserEmail = this.checkEmail(this.userEmail) && uniqueEmail;
          this.validateUserEmail
            ? (this.userEmailStatus = "success")
            : (this.userEmailStatus = "fail");
          this.checkForm();
        })
        .catch(() => {});
    },
    register() {
      if (this.checkForm) {
        this.$axios({
          url: "/user/register",
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
          .catch(() => {});
      }
    },
  },
};
</script>
