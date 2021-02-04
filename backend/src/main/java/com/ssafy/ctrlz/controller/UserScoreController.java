package com.ssafy.ctrlz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.ctrlz.model.UserScore;
import com.ssafy.ctrlz.service.UserScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "UserScore", description = "사용자 테스트 결과 API")
@CrossOrigin
@RestController
@RequestMapping("/user/score")
public class UserScoreController {

	@Autowired
	private UserScoreService userScoreService;

	@ApiOperation(value = "사용자 테스트 결과 추가")
	@PostMapping(value="/")
	public ResponseEntity<UserScore> save(@RequestBody UserScore userScore) {
		return new ResponseEntity<UserScore>(userScoreService.save(userScore), HttpStatus.OK);
	}

	@ApiOperation(value = "사용자 테스트 결과 조회")
	@GetMapping(value="/")
	public UserScore findByUser(@RequestParam String userId) {
		return userScoreService.findByUser(userId);
	}

	@ApiOperation(value = "사용자 ZBTI 결과 수정")
	@PutMapping(value="/zbti")
	public ResponseEntity<UserScore> updateZbti(@RequestBody UserScore userScore) {
		String userId = userScore.getUserId();
		String zbtiId = userScore.getZbtiId();

		userScore = userScoreService.findByUser(userId);
		userScore.setZbtiId(zbtiId);

		return new ResponseEntity<UserScore>(userScoreService.save(userScore), HttpStatus.OK);
	}

	@ApiOperation(value = "사용자 에코 지수 수정")
	@PutMapping(value="/echo")
	public ResponseEntity<UserScore> updateEcho(@RequestParam String userId) {
		UserScore userScore = userScoreService.findByUser(userId);
		userScore.setEchoScore(userScore.getEchoScore() + 3);

		return new ResponseEntity<UserScore>(userScoreService.save(userScore), HttpStatus.OK);
	}

}
