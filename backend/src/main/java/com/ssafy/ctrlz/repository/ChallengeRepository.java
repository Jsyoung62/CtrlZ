package com.ssafy.ctrlz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssafy.ctrlz.model.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, String> {

	public List<Challenge> findByLevelId(String levelId);

	public List<Challenge> findByChallengeType(String challengeType);

	public List<Challenge> findBychallengeClosedGreaterThan(String now);

	public List<Challenge> findBychallengeClosedLessThan(String now);

}
