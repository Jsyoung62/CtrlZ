package com.ssafy.ctrlz.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.ctrlz.model.UserBadge;
import com.ssafy.ctrlz.service.BadgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Badge", description = "뱃지 API")
@CrossOrigin
@RestController
@RequestMapping("/badge")
public class BadgeController {

	@Autowired
	private BadgeService badgeService;

	@ApiOperation(value = "전체 뱃지 조회")
	@GetMapping(value = "/findAll")
	public ResponseEntity<List<UserBadge>> findAll() {
		return new ResponseEntity<List<UserBadge>>(badgeService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "유저별 뱃지 조회")
	@GetMapping(value = "/find")
	public ResponseEntity<List<UserBadge>> findBadgesByUserId(@RequestParam String userId) {
		return new ResponseEntity<List<UserBadge>>(badgeService.findByUserId(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저별 뱃지 등록")
	@PostMapping(value = "/add")
	public ResponseEntity<UserBadge> addUserBadge(@RequestBody UserBadge userBadge) { 
		
		badgeService.save(userBadge);
		
		return new ResponseEntity<UserBadge>(badgeService.save(userBadge), HttpStatus.OK);
	}
	
}