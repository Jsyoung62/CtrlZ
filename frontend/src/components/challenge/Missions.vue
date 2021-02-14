<template>
  <div class="missions">
    <UploadModal
      v-show="isModalViewed"
      :mission-image="missionImage"
      :mission-title="missionTitle"
      :challenge-id="challengeId"
      @close="isModalViewed = false"
    >
      <template v-slot:modalTitle>
        {{ missionTitle }}
      </template>
      <template v-slot:modalContent>
        {{ missionContent }}
      </template>
    </UploadModal>
    <div
      v-for="(mission, index) in missions"
      :key="mission.missionId"
      class="mission locked"
      @click="handleMissionClick(index)"
    >
      <p>
        {{ index + 1 }}
      </p>
    </div>
    <div v-for="(post, index) in posts" :key="post.postId" @click="handleMissionDetail(index)">
      <img :src="post.postImage" alt="Thumbnail" class="mission" />
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
  props: {
    missionTotal: {
      type: Number,
      required: true,
    },

    missions: {
      type: Array,
      required: true,
    },
    posts: {
      // 내가 업로드한 미션
      type: Array,
      required: true,
    },
  },
  data: () => {
    return {
      challengeId: "",
      isModalViewed: false,
      missionTitle: "",
      missionContent: "",
      missionImage: "",
      isMissionViewd: true,
      postId: "",
    };
  },
  created() {
    this.challengeId = this.$route.params.challengeId;
  },
  methods: {
    handleMissionClick(index) {
      this.isModalViewed = true;
      this.missionTitle = this.missions[index].missionTitle;
      this.missionContent = this.missions[index].missionContent;
      this.missionImage = this.missions[index].missionImage;
    },
    handleMissionDetail(index) {
      this.postId = this.posts[index].postId;
      this.$router.push({
        name: "Post",
        params: {
          postId: this.postId,
        },
      });
    },
  },
};
</script>
