import Vue from "vue";
import Vuex from "vuex";
import jwtDecode from "jwt-decode";
import createPersistedState from "vuex-persistedstate";

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
  plugins: [createPersistedState()],
});
