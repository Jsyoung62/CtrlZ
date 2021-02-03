package com.ssafy.ctrlz.service;

import java.util.List;
import com.ssafy.ctrlz.model.UserBadge;

public interface BadgeService {
	
	UserBadge save(UserBadge userBadge);
	
	List <UserBadge> findAll();
	
	List<UserBadge> findByUserId(String userId);

}
