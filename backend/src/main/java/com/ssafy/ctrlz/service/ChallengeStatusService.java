package com.ssafy.ctrlz.service;

import java.util.List;
import com.ssafy.ctrlz.model.ChallengeStatus;
import com.ssafy.ctrlz.model.ChallengeStatusId;

public interface ChallengeStatusService {

	ChallengeStatus save(ChallengeStatus challengeStatus);

	List<ChallengeStatus> findAll();

	List<ChallengeStatus> findByChallenge(String challengeId);

	List<ChallengeStatus> findByUser(String userId);

	ChallengeStatus findByChallengeAndUser(ChallengeStatusId challengeStatusId);

}
