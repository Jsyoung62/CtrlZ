package com.ssafy.ctrlz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ssafy.ctrlz.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, String>  {
	
	public List<Comment> findByPostId(String postId);

}
