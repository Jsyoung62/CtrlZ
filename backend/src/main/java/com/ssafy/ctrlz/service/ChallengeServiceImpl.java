package com.ssafy.ctrlz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.model.Challenge;
import com.ssafy.ctrlz.repository.ChallengeRepository;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	ChallengeRepository challengeRepository;

	@Override
	public List<Challenge> findAll() {
		return challengeRepository.findAll();
	}

	@Override
	public Challenge findByChallenge(String challengeId) {
		return challengeRepository.getOne(challengeId);
	}

	@Override
	public List<Challenge> findByLevel(String levelId) {
		return challengeRepository.findByLevelId(levelId);
	}

	@Override
	public List<Challenge> findByChallengeType(String challengeType) {
		return challengeRepository.findByChallengeType(challengeType);
	}

	@Override
	public List<Challenge> findByChallengeInProgress(String now) {
		return challengeRepository.findBychallengeClosedGreaterThan(now);
	}

	@Override
	public List<Challenge> findByChallengeClosed(String now) {
		return challengeRepository.findBychallengeClosedLessThan(now);
	}

}
