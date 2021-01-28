package com.ssafy.ctrlz.service;

import java.util.List;
import com.ssafy.ctrlz.model.Challenge;

public interface ChallengeService {

	List<Challenge> findAll();

	Challenge findByChallenge(String challengeId);

	List<Challenge> findByLevel(String levelId);

	List<Challenge> findByChallengeType(String challengeType);

	List<Challenge> findByChallengeInProgress(String now);

	List<Challenge> findByChallengeClosed(String now);

}
