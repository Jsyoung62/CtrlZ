package com.ssafy.ctrlz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.model.UserBadge;
import com.ssafy.ctrlz.repository.BadgeRepository;

@Service
public class BadgeServiceImpl implements BadgeService {

	@Autowired
	private BadgeRepository badgeRepository;

	@Override
	public UserBadge save(UserBadge userBadge) {
		return badgeRepository.save(userBadge);
	}

	@Override
	public List<UserBadge> findAll() {
		List<UserBadge> userBadgeList = badgeRepository.findAll();
		
		if(userBadgeList.size() > 0) {
			return userBadgeList;
		}
		return null;
	}

	@Override
	public List<UserBadge> findByUserId(String userId) {
		List<UserBadge> userBadgeList = badgeRepository.findByUserId(userId);
		
		if(userBadgeList.size() > 0) {
			return userBadgeList;
		}
		return null;
	}

}
