package com.ssafy.ctrlz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.exception.NoDataFoundException;
import com.ssafy.ctrlz.model.ChallengeStatus;
import com.ssafy.ctrlz.model.ChallengeStatusId;
import com.ssafy.ctrlz.repository.ChallengeStatusRepository;

@Service
public class ChallengeStatusServiceImpl implements ChallengeStatusService {

	@Autowired
	private ChallengeStatusRepository challengeStatusRepository;

	@Override
	public ChallengeStatus save(ChallengeStatus challengeStatus) {
		return challengeStatusRepository.save(challengeStatus);
	}

	@Override
	public List<ChallengeStatus> findAll() {
		List<ChallengeStatus> challengeStatusList = challengeStatusRepository.findAll();

		if (challengeStatusList.size() > 0) {
			return challengeStatusList;
		}
		else {
			return null;
		}
	}

	@Override
	public List<ChallengeStatus> findByChallenge(String challengeId) {
		List<ChallengeStatus> challengeStatusList = challengeStatusRepository.findByIdChallengeId(challengeId);

		if (challengeStatusList.size() > 0) {
			return challengeStatusList;
		}
		else {
			return null;
		}
	}

	@Override
	public List<ChallengeStatus> findByUser(String userId) {
		List<ChallengeStatus> challengeStatusList = challengeStatusRepository.findByIdUserId(userId);

		if (challengeStatusList.size() > 0) {
			return challengeStatusList;
		}
		else {
			return null;
		}
	}

	@Override
	public ChallengeStatus findByChallengeAndUser(ChallengeStatusId challengeStatusId) {
		return challengeStatusRepository.findById(challengeStatusId).orElseThrow(() -> new NoDataFoundException("challengeStatus"));
	}

}
