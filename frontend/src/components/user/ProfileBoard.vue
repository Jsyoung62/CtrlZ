<template>
  <div class="profileBoard">
    <div class="profileWrapper">
      <img :src="userImage" class="profileImage" />
      <div class="profileInfo">
        <p class="name">
          {{ userName }}
        </p>
        <p v-if="userIntroduce" class="introduce">
          {{ userIntroduce }}
        </p>
        <p v-else class="introduce placeholder">
          자기 소개가 없습니다
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
      required: true,
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
