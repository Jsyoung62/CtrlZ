import Vue from "vue";
import App from "./App.vue";
import router from "./router";

Vue.config.productionTip = false;

new Vue({
  router,
  // eslint-disable-next-line id-length, comma-dangle
  render: (h) => h(App),
}).$mount("#app");
