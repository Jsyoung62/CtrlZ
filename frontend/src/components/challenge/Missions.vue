<template>
  <div class="missions">
    <UploadModal
      v-show="isModalViewed"
      :mission-image="missionImage"
      :mission-title="missionTitle"
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
      <img :src="posts[index].postImage" alt="Thumbnail" class="mission" />
    </div>
  </div>
</template>
<script>
import axios from "axios";
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
    challengeId: {
      type: Number,
      required: true,
    },
  },
  data: () => {
    return {
      isModalViewed: false,
      missionTitle: "",
      missionContent: "",
      missionImage: "",
      isMissionViewd: true,
      posts: [],
      postId: "",
    };
  },
  created() {
    axios({
      url: "http://i4a202.p.ssafy.io:8888/post/find/challenge/user/",
      method: "GET",
      params: {
        challengeId: this.challengeId,
        userId: this.$store.state.userInfo.userId,
      },
    }).then((response) => {
      this.posts = response.data;
    });
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
