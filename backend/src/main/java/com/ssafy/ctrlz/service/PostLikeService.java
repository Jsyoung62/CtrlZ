package com.ssafy.ctrlz.service;

import com.ssafy.ctrlz.model.PostLike;
import com.ssafy.ctrlz.model.PostLikeId;

public interface PostLikeService {
	
	public PostLike findUserIdAndPostId(PostLikeId postLikeId);
	public void savePostLike(PostLike postLike);
	public void deletePostLike(PostLikeId postlikeId);
	
}
