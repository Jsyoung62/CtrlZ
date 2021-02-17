package com.ssafy.ctrlz.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
import com.ssafy.ctrlz.model.ChallengeStatus;
import com.ssafy.ctrlz.service.ChallengeService;
import com.ssafy.ctrlz.service.ChallengeStatusService;
import com.ssafy.ctrlz.service.UserScoreService;
import com.ssafy.ctrlz.service.ZbtiResultService;
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

	@Autowired
	private UserScoreService userScoreService;

	@Autowired
	private ZbtiResultService zbtiResultService;

	@ApiOperation(value = "전체 챌린지 데이터")
	@GetMapping(value = "/all")
	public ResponseEntity<List<Challenge>> findAll() {
		List<Challenge> challengeList = challengeService.findAll();
		String challengeId;

		for (int i = 0; i < challengeList.size(); i++) {
			challengeId = challengeList.get(i).getChallengeId();
			challengeList.get(i).setParticipants(challengeStatusService.countInprogressByChallenge(challengeId));
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 챌린지 데이터")
	@GetMapping(value = "/")
	public Challenge findByChallenge(@RequestParam String challengeId) {
		Challenge challenge = challengeService.findByChallenge(challengeId);
		challenge.setParticipants(challengeStatusService.countInprogressByChallenge(challenge.getChallengeId()));

		return challenge;
	}

	@ApiOperation(value = "난이도별 챌린지 데이터")
	@GetMapping(value = "/level")
	public ResponseEntity<List<Challenge>> findByLevel(@RequestParam String levelId) {
		List<Challenge> challengeList = challengeService.findByLevel(levelId);
		String challengeId;

		if (challengeList != null) {
			for (int i = 0; i < challengeList.size(); i++) {
				challengeId = challengeList.get(i).getChallengeId();
				challengeList.get(i).setParticipants(challengeStatusService.countInprogressByChallenge(challengeId));
			}
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

	@ApiOperation(value = "타입별 챌린지 데이터")
	@GetMapping(value = "/type")
	public ResponseEntity<List<Challenge>> findByChallengeType(@RequestParam String challengeType) {
		List<Challenge> challengeList = challengeService.findByChallengeType(challengeType);
		String challengeId;

		if (challengeList != null) {
			for (int i = 0; i < challengeList.size(); i++) {
				challengeId = challengeList.get(i).getChallengeId();
				challengeList.get(i).setParticipants(challengeStatusService.countInprogressByChallenge(challengeId));
			}
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

	@ApiOperation(value = "진행중인 챌린지 데이터")
	@GetMapping(value = "/inprogress")
	public ResponseEntity<List<Challenge>> findByChallengeInProgress() {
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		List<Challenge> challengeList = challengeService.findByChallengeInProgress(now);
		String challengeId;

		if (challengeList != null) {
			for (int i = 0; i < challengeList.size(); i++) {
				challengeId = challengeList.get(i).getChallengeId();
				challengeList.get(i).setParticipants(challengeStatusService.countInprogressByChallenge(challengeId));
			}
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

	@ApiOperation(value = "종료된 챌린지 데이터")
	@GetMapping(value = "/closed")
	public ResponseEntity<List<Challenge>> findByChallengeClosed() {
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		List<Challenge> challengeList = challengeService.findByChallengeClosed(now);
		String challengeId;

		if (challengeList != null) {
			for (int i = 0; i < challengeList.size(); i++) {
				challengeId = challengeList.get(i).getChallengeId();
				challengeList.get(i).setParticipants(challengeStatusService.countInprogressByChallenge(challengeId));
			}
		}

		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

	@ApiOperation(value = "사용자별 추천 챌린지 데이터")
	@GetMapping(value = "/recommend")
	public ResponseEntity<List<Challenge>> findByRecommendChallenge(@RequestParam String userId) {
		String zbtiId;
		String challengeId;
		List<Challenge> challengeList;
		List<ChallengeStatus> userChallengeList = null;
		
		if (userId.equals("")) {
			challengeList = challengeService.findByChallengeType("일상");
		}
		else {
			zbtiId = userScoreService.findByUser(userId).getZbtiId();
			userChallengeList = challengeStatusService.findByUser(userId);
			if (zbtiId != null) {
				String zbtiType = zbtiResultService.findByZbti(zbtiId).getZbtiType();
				challengeList = challengeService.findByChallengeType(zbtiType);
			}
			else {
				challengeList = challengeService.findByChallengeType("일상");
			}
		}

		for (int i = 0; i < challengeList.size(); i++) {
			challengeId = challengeList.get(i).getChallengeId();
			challengeList.get(i).setParticipants(challengeStatusService.countInprogressByChallenge(challengeId));
		}

		challengeList.sort(new Comparator<Challenge>() {
			@Override
			public int compare(Challenge c1, Challenge c2) {
				return Long.compare(c2.getParticipants(), c1.getParticipants());
			}
		});

		if (userChallengeList != null) {
			for (int i = 0; i < challengeList.size(); i++) {
				for (int j = 0; j < userChallengeList.size(); j++) {
					if (challengeList.get(i).getChallengeId().equals(userChallengeList.get(j).getChallenge().getChallengeId())) {
						challengeList.remove(i);
					}
				}
			}
		}

		if (challengeList.size() >= 2) {
			return new ResponseEntity<List<Challenge>>(challengeList.subList(0, 2), HttpStatus.OK);
		}
		return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
	}

}
