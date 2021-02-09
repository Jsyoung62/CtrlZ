<template>
  <div class="profileBoard">
    <div class="profileWrapper">
      <img :src="require('@/assets/' + userImage)" class="profileImage" />
      <div class="profileInfo">
        <p class="name">
          {{ userName }}
        </p>
        <p class="introduce">
          {{ userIntroduce }}
        </p>
      </div>
    </div>

    <button class="modifyButton">
      프로필 편집
    </button>
  </div>
</template>

<script>
export default {
  name: "ProfileBoard",
  props: {
    userImage: {
      type: String,
      default: "no_profile.png",
      required: false,
    },
    userName: {
      type: String,
      required: true,
    },
    userIntroduce: {
      type: String,
      required: true,
    },
  },
};
</script>
