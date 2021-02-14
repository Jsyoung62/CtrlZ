package com.ssafy.ctrlz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.model.Comment;
import com.ssafy.ctrlz.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> findAll() {
		List<Comment> commentList = commentRepository.findAll();
		
		if(commentList.size() > 0) {
			return commentList;
		}
		return null;
	}

	@Override
	public List<Comment> findByPostId(String postId) {
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		if(commentList.size() > 0) {
			return commentList;
		}
		return null;
	}

}
