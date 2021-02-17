package com.ssafy.ctrlz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.exception.NoDataFoundException;
import com.ssafy.ctrlz.model.PostLike;
import com.ssafy.ctrlz.model.PostLikeId;
import com.ssafy.ctrlz.repository.PostLikeRepository;

@Service
public class PostLikeServiceImpl implements PostLikeService {

	@Autowired
	private PostLikeRepository postLikeRepository;
	
	@Override
	public void savePostLike(PostLike postLike) {
		postLikeRepository.save(postLike);
	}

	@Override
	public PostLike findUserIdAndPostId(PostLikeId postLikeId) {
		return postLikeRepository.findById(postLikeId).orElseThrow(() -> new NoDataFoundException("postLike"));
	}

	@Override
	public void deletePostLike(PostLikeId postlikeId) {
		postLikeRepository.deleteById(postlikeId);
	}
}
