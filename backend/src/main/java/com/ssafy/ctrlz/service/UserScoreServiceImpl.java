package com.ssafy.ctrlz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.exception.NoDataFoundException;
import com.ssafy.ctrlz.model.UserScore;
import com.ssafy.ctrlz.repository.UserScoreRepository;

@Service
public class UserScoreServiceImpl implements UserScoreService {

	@Autowired
	private UserScoreRepository userScoreRepository;

	@Override
	public UserScore save(UserScore userScore) {
		return userScoreRepository.save(userScore);
	}

	@Override
	public UserScore findByUser(String userId) {
		return userScoreRepository.findById(userId).orElseThrow(() -> new NoDataFoundException("userScore"));
	}

}
