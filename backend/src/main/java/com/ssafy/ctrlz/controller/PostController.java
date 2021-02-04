package com.ssafy.ctrlz.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ssafy.ctrlz.model.Post;
import com.ssafy.ctrlz.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Transactional
@Api(tags = "PostController", description = "게시글 API")
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("")
	@ApiOperation(value = "게시글 작성", notes = "한장의 사진과 내용을 작성합니다.")
	public Object writePost(Long postId, Long userId, String challengeId, String postContent, MultipartFile postImage) {

		postService.createPost(postId, userId, challengeId, postContent, postImage);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@GetMapping("")
	@ApiOperation(value = "게시글 조회", notes = "전체 게시글을 조회합니다.")
	public List<Post> selectPost(){
				
		return postService.selectPost();
	}
	
	@GetMapping("/detail")
	@ApiOperation(value = "게시글 세부 조회", notes = "게시글 ID에 맞는 게시글을 조회합니다.")
	public Object getPost(long postId) {
		
		return postService.getPost(postId);
	}
	
	@GetMapping("/find/challenge")
	@ApiOperation(value = "챌린지로 게시글 검색", notes = "챌린지 ID에 맞는 게시글을 조회합니다.")
	public List<Post> selectPostbyChallengeId(String challengeId){
		
		return postService.getPostByChallengeId(challengeId);
	}
	
	@GetMapping("/find/user")
	@ApiOperation(value = "유저ID로 게시글 검색", notes = "유저ID에 맞는 게시글을 조회합니다.")
	public List<Post> selectPostbyUserId(Long userId){
		
		return postService.findPostByUserId(userId);
	}
	
}
