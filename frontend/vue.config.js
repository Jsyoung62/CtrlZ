module.exports = {
  chainWebpack: (config) => {
    config.module
      .rule("eslint")
      .use("eslint-loader")
      .tap((options) => {
        options.fix = true; // auto-fix 옵션
        return options;
      });

    const svgRule = config.module.rule("svg");

    svgRule.uses.clear();

    svgRule
      .use("vue-loader")
      .loader("vue-loader") // or `vue-loader-v16` if you are using a preview support of Vue 3 in Vue CLI
      .end()
      .use("vue-svg-loader")
      .loader("vue-svg-loader");
  },
  // pwa 설정
  pwa: {
    name: "CtrlZ",
    short_name: "CtrlZ",
    icons: [
      {
        src: "/img/icons/192.png",
        sizes: "192x192",
        type: "image/png",
      },
      {
        src: "/img/icons/512.png",
        sizes: "512x512",
        type: "image/png",
      },
    ],
    start_url: "/index.html",
    display: "standalone",
    background_color: "#000000",
    theme_color: "#4DBA87",
    appleMobileWebAppCapable: "yes",
    appleMobileWebAppStatusBarStyle: "white",
  },
};
