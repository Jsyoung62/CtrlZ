import Vue from "vue";
import Vuex from "vuex";
import jwtDecode from "jwt-decode";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userInfo: {
      userId: "",
      userEmail: "",
      userName: "",
      userImage: "",
      userIntroduce: "",
    },
  },
  mutations: {
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
