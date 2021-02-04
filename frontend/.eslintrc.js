module.exports = {
  env: {
    "node": true,
    "commonjs": true
  },
  extends: [
    "eslint:recommended",
    "plugin:vue/recommended", // Use this if you are using Vue.js 2.x.
    'prettier',
    'plugin:prettier/recommended',
  ],
  plugins: ['prettier'],
  rules: {
    'prettier/prettier': [
      'error',
      {
        singleQuote: false,
        semi: true,
        useTabs: false,
        tabWidth: 2,
        trailingComma: 'es5',
        printWidth: 100,
        bracketSpacing: false,
        endOfLine: 'lf',
        arrowParens: 'always'
      },
    ],
    "vue/max-attributes-per-line": ["error", {
      "singleline": 5,
      "multiline": {
        "max": 1,
        "allowFirstLine": false
      }
    }],
    "vue/html-self-closing": ["error", {
      "html": {
        "void": "always",
        "normal": "never",
        "component": "always"
      },
      "svg": "always",
      "math": "always"
    }],
    "indent": [ "error", 2],
    "quotes": ["error", "double", { "allowTemplateLiterals": true }], 
    "no-var": 2,
    "no-const-assign": 2,
    "prefer-const": ["error", {
        "destructuring": "any",
        "ignoreReadBeforeAssign": true
    }],
    "no-new-object": 2,
    "object-shorthand": 2,
    "no-array-constructor": 2,
    "no-new-wrappers": 2,
    "array-callback-return": 0,
    "prefer-template": 2,
    "template-curly-spacing": ["error", "never"],
    "no-useless-escape": 2, 
    "func-style": ["error","declaration", { "allowArrowFunctions": true }],
    "wrap-iife": ["error", "outside"],
    "no-loop-func": 2,
    "no-new-func": 2,
    "space-before-blocks": ["error", { "functions": "always", "keywords": "always", "classes": "always" }],
    "brace-style": ["error", "stroustrup", { "allowSingleLine": true }],
    "space-before-function-paren": ["error", "never"],
    "prefer-spread": 2,
    "prefer-arrow-callback": 2,
    "arrow-spacing": ["error", { "before": true, "after": true }],
    "arrow-parens": ["error", "always"],
    "no-confusing-arrow": 2,
    "no-dupe-class-members": 2,
    "no-duplicate-imports": ["error", { "includeExports": true }],
    "dot-notation": 2,
    "no-multi-assign": 2,
    "eqeqeq": ["error", "always"],
    "no-case-declarations": 2,
    "no-nested-ternary": 2,
    "space-infix-ops": 2,
    "eol-last": ["error", "always"],
    "newline-per-chained-call": ["error", { "ignoreChainWithDepth": 2 }],
    "no-whitespace-before-property": 2,
    "padded-blocks": ["error", "never"],
    "space-in-parens": ["error", "never"],
    "array-bracket-spacing": ["error", "always", { "arraysInArrays": false }],
    "object-curly-spacing": ["error", "always", { "objectsInObjects": false, "arraysInObjects": false }],
    "comma-style": ["error", "last"],
    "comma-dangle": ["error", "only-multiline"],
    "semi": ["error", "always"],
    "id-length": ["error", { "min": 2 }],
    "camelcase": ["error", {"properties": "always"}],
    "new-cap": ["error", {"newIsCap": true, "capIsNew": false, "properties": false }],
  }
}
