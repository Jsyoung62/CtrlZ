import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import firebase from "firebase";

Vue.config.productionTip = false;

const config = {
  apiKey: process.env.VUE_APP_FIREBASE_API_KEY,
  authDomain: "ctrlz-1f100.firebaseapp.com",
  projectId: "ctrlz-1f100",
  storageBucket: "ctrlz-1f100.appspot.com",
  messagingSenderId: "915349401368",
  appId: "1:915349401368:web:fe551c570be1e9c6c8b86d",
  measurementId: "G-7QS8GW5EQ9",
};
firebase.initializeApp(config);

new Vue({
  router,
  store,
  // eslint-disable-next-line id-length, comma-dangle
  render: (h) => h(App),
}).$mount("#app");
