package com.ssafy.ctrlz.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.ctrlz.exception.NoDataFoundException;
import com.ssafy.ctrlz.model.Post;
import com.ssafy.ctrlz.model.PostLike;
import com.ssafy.ctrlz.model.PostLikeId;
import com.ssafy.ctrlz.model.PostUpload;
import com.ssafy.ctrlz.service.PostLikeService;
import com.ssafy.ctrlz.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Post", description = "게시글 API")
@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private PostLikeService postLikeService;

	@ApiOperation(value = "게시글 작성", notes = "한장의 사진과 내용을 작성합니다.")
	@PostMapping("")
	public ResponseEntity<String> createPost(PostUpload postUpload) {
		String filePath = "/var/www/html/dist/img/post";
		String fileName = "postId" + (postService.countAll() + 1) + "." + postUpload.getPostImage().getContentType().split("/")[1];

		File saveFile = new File(filePath, fileName);

		try {
			postUpload.getPostImage().transferTo(saveFile);
			fileName = "http://i4a202.p.ssafy.io/img/post/" + fileName;
		}
		catch (IOException e) {
            e.printStackTrace();
        }

		Post post = new Post();
		post.setChallengeId(postUpload.getChallengeId());
		post.setMissionId(postUpload.getMissionId());
		post.setUserId(postUpload.getUserId());
		post.setPostContent(postUpload.getPostContent());
		post.setPostImage(fileName);

		postService.createPost(post);

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 조회", notes = "전체 게시글을 조회합니다.")
	@GetMapping("")
	public ResponseEntity<List<Post>> findAll() {
		return new ResponseEntity<List<Post>>(postService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 세부 조회", notes = "게시글 ID에 맞는 게시글을 조회합니다.")
	@GetMapping("/detail")
	public ResponseEntity<Post> findByPost(long postId) {
		return new ResponseEntity<Post>(postService.findByPost(postId), HttpStatus.OK);
	}

	@ApiOperation(value = "챌린지로 게시글 검색", notes = "챌린지 ID에 맞는 게시글을 조회합니다.")
	@GetMapping("/find/challenge")
	public ResponseEntity<List<Post>> findByChallenge(String challengeId) {
		return new ResponseEntity<List<Post>>(postService.findByChallenge(challengeId), HttpStatus.OK);
	}

	@ApiOperation(value = "유저ID로 게시글 검색", notes = "유저ID에 맞는 게시글을 조회합니다.")
	@GetMapping("/find/user")
	public ResponseEntity<List<Post>> findByUser(Long userId) {
		return new ResponseEntity<List<Post>>(postService.findByUser(userId), HttpStatus.OK);
	}

	@ApiOperation(value = "유저ID,미션ID로 게시글 검색", notes = "유저ID,미션ID에 맞는 게시글을 조회합니다.")
	@GetMapping("/find/mission")
	public ResponseEntity<List<Post>> findByUserAndMission(Long userId, String missionId) {
		return new ResponseEntity<List<Post>>(postService.findByUserAndMission(userId, missionId), HttpStatus.OK);
	}

	@ApiOperation(value = "유저ID,챌린지ID로 게시글 검색", notes = "유저ID,챌린지ID에 맞는 게시글을 조회합니다.")
	@GetMapping("/find/challenge/user")
	public ResponseEntity<List<Post>> findByUserAndChallenge(Long userId, String challengeId) {
		return new ResponseEntity<List<Post>>(postService.findByUserAndChallenge(userId, challengeId), HttpStatus.OK);
	}

	@ApiOperation(value = "좋아요 누르기", notes = "유저가 좋아요를 누르면 postLike +1 && postLike 데이터 생성, 다시 한번 누르면 postLike -1 && postLike 데이터 삭제가 됩니다.")
	@PostMapping("/like")
	public ResponseEntity<String> postLike(PostLikeId postLikeId) {
		PostLike postLike = new PostLike();
		postLike.setId(postLikeId);
		Post post = postService.findByPost(postLikeId.getPostId());

		try {
			postLikeService.findUserIdAndPostId(postLikeId);
			post.setPostLike(post.getPostLike() - 1);
			postLikeService.deletePostLike(postLikeId);

			return new ResponseEntity<String>("LikeCancel", HttpStatus.OK);
		}
		catch (NoDataFoundException e) {
			post.setPostLike(post.getPostLike() + 1);
			postLikeService.savePostLike(postLike);

			return new ResponseEntity<String>("Like", HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "좋아요 눌렀는지 여부 조회", notes = "postLike 테이블에 userID 와 PostID로 조회")
	@GetMapping("/like/select")
	public ResponseEntity<PostLike> postLikeSelect(PostLikeId postLikeId){
		postLikeService.findUserIdAndPostId(postLikeId);
		
		return new ResponseEntity<PostLike>(postLikeService.findUserIdAndPostId(postLikeId), HttpStatus.OK);
	}
	

}
