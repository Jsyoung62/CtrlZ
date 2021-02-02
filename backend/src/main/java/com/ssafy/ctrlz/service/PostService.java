package com.ssafy.ctrlz.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ssafy.ctrlz.model.Post;

public interface PostService {
	//게시글 작성 하기
	public void createPost(Long postId,Long userId, String challengeId,String postContent, MultipartFile postImage);
	//게시글 전체 조회 하기
	public List<Post> selectPost(Post post);
	//게시글 세부사항
	public Post getPost(long postId);
	
}
