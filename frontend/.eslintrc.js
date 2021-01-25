module.exports = {
  env: {
    "node": true,
    "commonjs": true
  },
  extends: [
    "eslint:recommended",
    "plugin:vue/recommended" // Use this if you are using Vue.js 2.x.
  ],
  rules: {
    "indent": ["error", 2],
    "quotes": ["error", "double"]
  }
}
