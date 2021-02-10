package com.ssafy.ctrlz.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.ctrlz.model.ChallengeStatus;
import com.ssafy.ctrlz.model.ChallengeStatusId;
import com.ssafy.ctrlz.service.ChallengeStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ChallengeStatus", description = "챌린지 현황 API")
@CrossOrigin
@RestController
@RequestMapping("/challenge/status")
public class ChallengeStatusController {

	@Autowired
	private ChallengeStatusService challengeStatusService;

	@ApiOperation(value = "챌린지 현황 데이터 추가")
	@PostMapping(value="/")
	public ResponseEntity<ChallengeStatus> save(@RequestBody ChallengeStatus challengeStatus) {
		return new ResponseEntity<ChallengeStatus>(challengeStatusService.save(challengeStatus), HttpStatus.OK);
	}

	@ApiOperation(value = "전체 챌린지 현황 데이터")
	@GetMapping(value="/all")
	public ResponseEntity<List<ChallengeStatus>> findAll() {
		return new ResponseEntity<List<ChallengeStatus>>(challengeStatusService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "챌린지별 현황 데이터")
	@GetMapping(value="/")
	public ResponseEntity<List<ChallengeStatus>> findByChallenge(@RequestParam String challengeId) {
		return new ResponseEntity<List<ChallengeStatus>>(challengeStatusService.findByChallenge(challengeId), HttpStatus.OK);
	}

	@ApiOperation(value = "사용자별 챌린지 현황 데이터")
	@GetMapping(value="/user")
	public ResponseEntity<List<ChallengeStatus>> findByUser(@RequestParam String userId) {
		return new ResponseEntity<List<ChallengeStatus>>(challengeStatusService.findByUser(userId), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 챌린지 현황 데이터")
	@GetMapping(value="/{challengeId}/{userId}")
	public ChallengeStatus findByChallengeAndUser(@PathVariable String challengeId, @PathVariable String userId) {
		ChallengeStatusId challengeStatusId = new ChallengeStatusId();
		challengeStatusId.setChallengeId(challengeId);
		challengeStatusId.setUserId(userId);

		return challengeStatusService.findByChallengeAndUser(challengeStatusId);
	}

	@ApiOperation(value = "특정 챌린지 현황 진행미션수 수정")
	@PutMapping(value="/{challengeId}/{userId}")
	public ResponseEntity<ChallengeStatus> updateChallengeMissionCurrent(@PathVariable String challengeId, @PathVariable String userId) {
		ChallengeStatusId challengeStatusId = new ChallengeStatusId();
		challengeStatusId.setChallengeId(challengeId);
		challengeStatusId.setUserId(userId);

		ChallengeStatus challengeStatus = challengeStatusService.findByChallengeAndUser(challengeStatusId);

		if (challengeStatus.getChallenge().getChallengeMissionTotal() > challengeStatus.getChallengeMissionCurrent()) {
			challengeStatus.setChallengeMissionCurrent(challengeStatus.getChallengeMissionCurrent() + 1);

			if (challengeStatus.getChallenge().getChallengeMissionTotal() == challengeStatus.getChallengeMissionCurrent()) {
				String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				challengeStatus.setChallengeFinishDate(now);
			}

			challengeStatusService.save(challengeStatus);
		}

		return new ResponseEntity<ChallengeStatus>(challengeStatus, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 챌린지 현황 참여자수")
	@GetMapping(value="/count")
	public long countByChallengeId(@RequestParam String challengeId) {
		return challengeStatusService.countByChallenge(challengeId);
	}

}
