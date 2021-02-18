<template>
  <div class="missions">
    <UploadModal
      v-show="isModalViewed"
      :mission="missions[index]"
      :is-button="isButton"
      @close="isModalViewed = false"
    />
    <div v-for="post in posts" :key="post.postId" @click="handlePostClick(post.postId)">
      <img :src="post.postImage" alt="Thumbnail" class="mission" />
    </div>
    <div
      v-for="(mission, index) in missions"
      :key="mission.id.missionId"
      class="mission locked"
      @click="handleMissionClick(index)"
    >
      <p>
        {{ mission.id.missionId }}
      </p>
    </div>
  </div>
</template>
<script>
import UploadModal from "@/components/common/UploadModal.vue";
import "@/components/css/challenge/missions.scss";

export default {
  name: "Missions",
  components: {
    UploadModal,
  },
  data: () => {
    return {
      challengeId: "",
      index: "",
      isModalViewed: false,
      isMissionViewd: true,
      missions: [],
      posts: [],
      isButton: "",
    };
  },
  created() {
    this.challengeId = this.$route.params.challengeId;

    this.getMissions();
  },
  methods: {
    // 챌린지 내 모든 미션 정보 불러오기
    getMissions() {
      this.$axios({
        url: "/mission/",
        method: "GET",
        params: {
          challengeId: this.challengeId,
        },
      })
        .then((response) => {
          this.missions = response.data;

          this.getPosts();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 챌린지에 모든 사용자가 올린 게시글 정보 불러오기
    getPosts() {
      this.$axios({
        url: "/post/find/challenge/user",
        method: "GET",
        params: {
          challengeId: this.challengeId,
          userId: this.$store.state.userInfo.userId,
        },
      })
        .then((response) => {
          if (response.data !== "") {
            this.posts = response.data;
          }

          this.removeCompletedMissions();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 사용자가 이미 완료한 미션 정보 제거
    removeCompletedMissions() {
      for (let index = 0; index < this.posts.length; index++) {
        this.missions.splice(0, 1);
      }
    },
    handleMissionClick(index) {
      this.isModalViewed = true;
      this.index = index;

      if (index === 0) {
        this.isButton = true;
      } else {
        this.isButton = false;
      }
    },
    handlePostClick(postId) {
      this.$router.push({
        name: "Post",
        params: {
          postId,
        },
      });
    },
  },
};
</script>
