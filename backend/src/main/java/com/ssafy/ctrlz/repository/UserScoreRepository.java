package com.ssafy.ctrlz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssafy.ctrlz.model.UserScore;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, String> {

	public long countByzScoreGreaterThan(long zScore);

}
