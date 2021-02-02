package com.ssafy.ctrlz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ssafy.ctrlz.model.Post;

public interface PostRepository extends JpaRepository<Post,Object> {
	
	Post getPostByPostId(Long postId);
}
