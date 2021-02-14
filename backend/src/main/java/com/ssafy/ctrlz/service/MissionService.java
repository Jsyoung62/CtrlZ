package com.ssafy.ctrlz.service;

import java.util.List;
import com.ssafy.ctrlz.model.Mission;

public interface MissionService {

	List<Mission> findAll();

	List<Mission> findByChallenge(String challengeId);

}
