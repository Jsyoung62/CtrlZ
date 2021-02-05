package com.ssafy.ctrlz.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.ctrlz.model.Comment;
import com.ssafy.ctrlz.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Comment", description = "댓글 API")
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "전체 댓글 조회")
	@GetMapping(value = "/findAll")
	public ResponseEntity<List<Comment>> findAll() {
		return new ResponseEntity<List<Comment>>(commentService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글별 댓글 조회")
	@GetMapping(value = "/find")
	public ResponseEntity<List<Comment>> findCommentByPostId(@RequestParam String postId) {
		return new ResponseEntity<List<Comment>>(commentService.findByPostId(postId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글별 댓글 등록")
	@PostMapping(value = "/add")
	public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
		
		commentService.save(comment);
		
		return new ResponseEntity<Comment>(commentService.save(comment), HttpStatus.OK);
	}

}
