package com.ssafy.ctrlz.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.ctrlz.model.Challenge;
import com.ssafy.ctrlz.service.ChallengeService;
import com.ssafy.ctrlz.service.ChallengeStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Challenge", description = "챌린지 API")
@CrossOrigin
@RestController
@RequestMapping("/challenge")
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;

	@Autowired
	private ChallengeStatusService challengeStatusService;

	@ApiOperation(value = "전체 챌린지 데이터")
	@GetMapping(value="/all")
	public ResponseEntity<List<Challenge>> findAll() {
		List<Challenge> challengeList = challengeService.findAll();
		String challengeId;

		for (int i = 0; i < challengeList.size(); i++) {
			challengeId = challengeList.get(i).getChallengeId();
			challengeList.get(i).setParticipants(challengeStatusService.countByChallenge(challengeId));
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 챌린지 데이터")
	@GetMapping(value="/")
	public Challenge findByChallenge(@RequestParam String challengeId) {
		Challenge challenge = challengeService.findByChallenge(challengeId);
		challenge.setParticipants(challengeStatusService.countByChallenge(challenge.getChallengeId()));

		return challenge;
	}

	@ApiOperation(value = "난이도별 챌린지 데이터")
	@GetMapping(value="/level")
	public ResponseEntity<List<Challenge>> findByLevel(@RequestParam String levelId) {
		List<Challenge> challengeList = challengeService.findByLevel(levelId);
		String challengeId;

		if (challengeList != null) {
			for (int i = 0; i < challengeList.size(); i++) {
				challengeId = challengeList.get(i).getChallengeId();
				challengeList.get(i).setParticipants(challengeStatusService.countByChallenge(challengeId));
			}
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

	@ApiOperation(value = "타입별 챌린지 데이터")
	@GetMapping(value="/type")
	public ResponseEntity<List<Challenge>> findByChallengeType(@RequestParam String challengeType) {
		List<Challenge> challengeList = challengeService.findByChallengeType(challengeType);
		String challengeId;

		if (challengeList != null) {
			for (int i = 0; i < challengeList.size(); i++) {
				challengeId = challengeList.get(i).getChallengeId();
				challengeList.get(i).setParticipants(challengeStatusService.countByChallenge(challengeId));
			}
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

	@ApiOperation(value = "진행중인 챌린지 데이터")
	@GetMapping(value="/inprogress")
	public ResponseEntity<List<Challenge>> findByChallengeInProgress() {
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		List<Challenge> challengeList = challengeService.findByChallengeInProgress(now);
		String challengeId;

		if (challengeList != null) {
			for (int i = 0; i < challengeList.size(); i++) {
				challengeId = challengeList.get(i).getChallengeId();
				challengeList.get(i).setParticipants(challengeStatusService.countByChallenge(challengeId));
			}
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

	@ApiOperation(value = "종료된 챌린지 데이터")
	@GetMapping(value="/closed")
	public ResponseEntity<List<Challenge>> findByChallengeClosed() {
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		List<Challenge> challengeList = challengeService.findByChallengeClosed(now);
		String challengeId;

		if (challengeList != null) {
			for (int i = 0; i < challengeList.size(); i++) {
				challengeId = challengeList.get(i).getChallengeId();
				challengeList.get(i).setParticipants(challengeStatusService.countByChallenge(challengeId));
			}
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

}
