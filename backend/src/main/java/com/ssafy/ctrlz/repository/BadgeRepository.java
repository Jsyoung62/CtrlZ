package com.ssafy.ctrlz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssafy.ctrlz.model.UserBadge;

@Repository
public interface BadgeRepository extends JpaRepository<UserBadge, String> {

	public List<UserBadge> findByUserId(String userId);
	
}
