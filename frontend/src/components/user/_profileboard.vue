<template>
  <div class="profileBoard">
    <nav id="profile">
      <div class="profile__Image">
        <img src="@/assets/user_test.png" class="profileImage" />
      </div>
      <div class="profile__Info">
        <ul>
          <li id="porfile__name">
            <p>{{ name }}</p>
          </li>
          <br />
          <li id="profile__introduce">
            <p>{{ introduce }}</p>
          </li>
        </ul>
      </div>
    </nav>
    <button id="profile__modify">
      프로필 편집
    </button>
  </div>
</template>

<script>
import "@/components/css/user/profile.scss";
export default {
  name: "Profileboard",
  data: () => {
    return {
      image: "",
      name: "greengirl-927",
      introduce: "건강한 지구를 만들기 위해 불편함을 감수하는 Z세대 그린걸입니다~",
    };
  },
};
</script>
