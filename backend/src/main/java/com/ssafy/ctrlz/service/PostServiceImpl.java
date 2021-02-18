package com.ssafy.ctrlz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.exception.NoDataFoundException;
import com.ssafy.ctrlz.model.Post;
import com.ssafy.ctrlz.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public List<Post> findAll() {
		List<Post> postList = postRepository.findAll();

		if(postList.size() > 0) {
			return postList;
		}
		return null;
	}

	@Override
	public Post findByPost(long postId) {
		return postRepository.findById(postId).orElseThrow(() -> new NoDataFoundException("post"));
	}

	@Override
	public List<Post> findByChallenge(String challengeId) {
		List<Post> postList = postRepository.findByChallengeIdOrderByPostId(challengeId);

		if(postList.size() > 0) {
			return postList;
		}
		return null;
	}

	@Override
	public List<Post> findByUser(Long userId) {
		List<Post> postList = postRepository.findByUserId(userId);

		if(postList.size() > 0) {
			return postList;
		}
		return null;
	}

	@Override
	public List<Post> findByUserAndMission(Long userId, String missionId) {
		List<Post> postList = postRepository.findByUserIdAndMissionId(userId, missionId);

		if(postList.size() > 0) {
			return postList;
		}
		return null;
	}

	@Override
	public List<Post> findByUserAndChallenge(Long userId, String challengeId) {
		List<Post> postList = postRepository.findByUserIdAndChallengeId(userId, challengeId);

		if(postList.size() > 0) {
			return postList;
		}
		return null;
	}

	@Override
	public long countAll() {
		return postRepository.count();
	}

}
