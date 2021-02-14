package com.ssafy.ctrlz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.model.Mission;
import com.ssafy.ctrlz.repository.MissionRepository;

@Service
public class MissionServiceImpl implements MissionService {

	@Autowired
	private MissionRepository missionRepository;

	@Override
	public List<Mission> findAll() {
		List<Mission> missionList = missionRepository.findAll();

		if (missionList.size() > 0) {
			return missionList;
		}
		return null;
	}

	@Override
	public List<Mission> findByChallenge(String challengeId) {
		List<Mission> missionList = missionRepository.findByIdChallengeId(challengeId);

		if(missionList.size() > 0) {
			return missionList;
		}
		return null;
	}

}
