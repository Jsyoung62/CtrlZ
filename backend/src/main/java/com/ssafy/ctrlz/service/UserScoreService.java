package com.ssafy.ctrlz.service;

import com.ssafy.ctrlz.model.UserScore;

public interface UserScoreService {

	UserScore save(UserScore userScore);

	UserScore findByUser(String userId);

	long countAll();

	long countRankByZScore(long zScore);

}
