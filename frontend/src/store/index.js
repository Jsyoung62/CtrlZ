import Vue from "vue";
import Vuex from "vuex";
import jwtDecode from "jwt-decode";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    splash: true,
    userInfo: {
      userId: "",
      userEmail: "",
      userName: "",
      userImage: "",
      userIntroduce: "",
    },
  },
  mutations: {
    ENDSPLASH: (state) => {
      state.splash = false;
    },
    LOGIN: (state, token) => {
      const user = jwtDecode(token).user;
      state.userInfo = user;
    },
    LOGOUT: (state) => {
      state.userInfo = {};
    },
    GOOGLELOGIN: (state, userInfo) => {
      state.userInfo = userInfo;
    },
  },
});
