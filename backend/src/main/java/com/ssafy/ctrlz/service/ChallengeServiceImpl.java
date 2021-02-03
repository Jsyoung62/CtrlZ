package com.ssafy.ctrlz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.exception.NoDataFoundException;
import com.ssafy.ctrlz.model.Challenge;
import com.ssafy.ctrlz.repository.ChallengeRepository;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	private ChallengeRepository challengeRepository;

	@Override
	public List<Challenge> findAll() {
		List<Challenge> challengeList = challengeRepository.findAll();

		if (challengeList.size() > 0) {
			return challengeList;
		}
		else {
			return null;
		}
	}

	@Override
	public Challenge findByChallenge(String challengeId) {
		return challengeRepository.findById(challengeId).orElseThrow(() -> new NoDataFoundException("challenge"));
	}

	@Override
	public List<Challenge> findByLevel(String levelId) {
		List<Challenge> challengeList = challengeRepository.findByLevelId(levelId);

		if (challengeList.size() > 0) {
			return challengeList;
		}
		else {
			return null;
		}
	}

	@Override
	public List<Challenge> findByChallengeType(String challengeType) {
		List<Challenge> challengeList = challengeRepository.findByChallengeType(challengeType);

		if (challengeList.size() > 0) {
			return challengeList;
		}
		else {
			return null;
		}
	}

	@Override
	public List<Challenge> findByChallengeInProgress(String now) {
		List<Challenge> challengeList = challengeRepository.findBychallengeClosedGreaterThan(now);

		if (challengeList.size() > 0) {
			return challengeList;
		}
		else {
			return null;
		}
	}

	@Override
	public List<Challenge> findByChallengeClosed(String now) {
		List<Challenge> challengeList = challengeRepository.findBychallengeClosedLessThan(now);

		if (challengeList.size() > 0) {
			return challengeList;
		}
		else {
			return null;
		}
	}

}
