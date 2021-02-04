package com.ssafy.ctrlz.service;

import java.util.List;
import com.ssafy.ctrlz.model.Comment;

public interface CommentService {
	
	Comment save(Comment comment);
	
	List <Comment> findAll();
	
	List <Comment> findByPostId(String postId);

}
