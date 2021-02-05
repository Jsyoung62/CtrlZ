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
};
