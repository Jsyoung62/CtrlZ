package com.ssafy.ctrlz.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.model.Post;
import com.ssafy.ctrlz.model.PostUpload;
import com.ssafy.ctrlz.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void createPost(PostUpload postUpload) {
		Post post = new Post();
		String UPLOAD_PATH = "/var/www/html/dist/img/userMission";
		UUID uuid = UUID.randomUUID();
		String saveName = uuid+"_"+postUpload.getPostImage().getOriginalFilename();

		File saveFile = new File(UPLOAD_PATH, saveName);
        
		try {
			postUpload.getPostImage().transferTo(saveFile);
            saveName = "http://i4a202.p.ssafy.io/img/userMission/" + saveName;
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		post.setUserId(postUpload.getUserId());
		post.setChallengeId(postUpload.getChallengeId());
		post.setPostContent(postUpload.getPostContent());
		post.setPostImage(saveName);
		postRepository.save(post);
		
	}

	@Override
	public List<Post> selectPost() {
		List<Post> postList = postRepository.findAll();
		
		if(postList.size() > 0) {
			return postList;
		}else {
			return null;
		}
	}

	@Override
	public Post getPost(long postId) {
		
		return postRepository.getPostByPostId(postId);
	}

	@Override
	public List<Post> getPostByChallengeId(String challengeId) {
		List<Post> postByChallengeId = postRepository.getPostByChallengeId(challengeId);
		
		if(postByChallengeId.size() > 0) {
			return postByChallengeId;
		}else {
			return null;
		}
	}

	@Override
	public List<Post> findPostByUserId(Long userId) {
		List<Post> postByUserId = postRepository.findPostByUserId(userId);
		
		if(postByUserId.size() > 0) {
			return postByUserId;
		}else {
			return null;
		}
	}

	@Override
	public List<Post> findPostByUserIdAndMissionId(Long userId, String missionId) {
		List<Post> postByUserIdAndMissionId = postRepository.findPostByUserIdAndMissionId(userId, missionId);
		
		if(postByUserIdAndMissionId.size() > 0) {
			return postByUserIdAndMissionId;
		}else {
			return null;
		}
	}

	@Override
	public List<Post> findPostByUserIdAndChallengeId(Long userId, String challengeId) {
		List<Post> postByUserIdAndChallengeId = postRepository.findPostByUserIdAndChallengeId(userId, challengeId);
		
		if(postByUserIdAndChallengeId.size() > 0) {
			return postByUserIdAndChallengeId;
		}else {
			return null;
		}
	}
}
