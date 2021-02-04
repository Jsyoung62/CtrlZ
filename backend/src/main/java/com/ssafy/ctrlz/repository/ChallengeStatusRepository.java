package com.ssafy.ctrlz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssafy.ctrlz.model.ChallengeStatus;
import com.ssafy.ctrlz.model.ChallengeStatusId;

@Repository
public interface ChallengeStatusRepository extends JpaRepository<ChallengeStatus, ChallengeStatusId> {

	public List<ChallengeStatus> findByIdChallengeId(String challengeId);

	public List<ChallengeStatus> findByIdUserId(String userId);

}
