<template>
  <div class="login userContainer">
    <Title title="로그인" />
    <form action="" method="post">
      <div>
        <label for="email">이메일</label>
        <input id="email" v-model="email" type="email" />
      </div>

      <div>
        <label for="password">비밀번호</label>
        <input id="password" v-model="password" type="password" @keyup.enter="login" />
      </div>
    </form>

    <router-link to="find" class="toFindButton">
      비밀번호 찾기
    </router-link>
    <button type="submit" class="loginButton" @click="login">
      로그인
    </button>
    <p class="registerText">
      계정이 없나요?
      <router-link to="register" class="toRegisterButton">
        회원가입
      </router-link>
    </p>
    <img
      class="googleLoginButton"
      src="@/assets/googleButton/btn_google_signin_light_normal_web.png"
      @click="googleLogin"
    />
  </div>
</template>
<script>
import firebase from "firebase";
import Title from "@/components/user/Title.vue";
import "@/components/css/user/index.scss";
import "@/components/css/user/login.scss";

export default {
  name: "Login",
  components: {
    Title,
  },
  data: () => {
    return {
      email: "",
      password: "",
      validateEmail: false,
    };
  },
  watch: {
    email() {
      this.validateEmail = this.checkEmail(this.email);
    },
  },
  methods: {
    checkEmail(email) {
      const re = /^(([^<>()\\[\]\\.,;:\s@"]+(\.[^<>()\\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    checkForm() {
      if (this.validateEmail && this.password.length > 0) {
        return true;
      }
    },
    login() {
      if (this.checkForm) {
        this.$axios({
          url: "/user/login",
          method: "POST",
          data: {
            userEmail: this.email,
            userPassword: this.password,
          },
        })
          .then((res) => {
            const token = res.data.accesstoken;
            this.$store.commit("LOGIN", token); // vuex에 회원정보 저장

            this.updateZbtiResult(); // user ZBTI 결과 업데이트
            this.$router.push("/");
          })
          .catch((error) => {
            console.error(error);
          });
        return;
      }
    },
    googleLogin() {
      const provider = new firebase.auth.GoogleAuthProvider();
      firebase
        .auth()
        .signInWithPopup(provider)
        .then((res) => {
          this.$axios({
            url: "/user/google/register",
            method: "POST",
            data: {
              userGid: res.user.uid,
              userEmail: res.user.email,
              userName: res.user.displayName,
              userImage: res.user.photoURL,
              userPassword: res.user.uid,
              userType: "Y",
            },
          }).then((res) => {
            const token = res.data.accesstoken;
            this.$store.commit("LOGIN", token); // vuex에 회원정보 저장

            this.updateZbtiResult(); // user ZBTI 결과 업데이트
            this.$router.push("/");
          });
        })
        .catch((error) => {
          console.error(error);
        });
    },
    updateZbtiResult() {
      // ZBTI 테스트 결과가 있는 경우
      if (this.$store.state.zbtiId !== "") {
        this.$axios({
          url: "/user/zbti",
          method: "PUT",
          data: {
            userId: this.$store.state.userInfo.userId,
            zbtiId: this.$store.state.zbtiId,
          },
        })
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
  },
};
</script>
