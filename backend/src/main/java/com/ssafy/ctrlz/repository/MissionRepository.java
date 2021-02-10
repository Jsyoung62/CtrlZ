package com.ssafy.ctrlz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssafy.ctrlz.model.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, String> {
	
	public List<Mission> findByChallengeId(String challengeId);

}
