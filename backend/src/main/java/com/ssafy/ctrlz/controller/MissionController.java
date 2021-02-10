package com.ssafy.ctrlz.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.ctrlz.model.Mission;
import com.ssafy.ctrlz.service.MissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Mission", description = "미션 API")
@CrossOrigin
@RestController
@RequestMapping("/mission")
public class MissionController {
	
	@Autowired
	private MissionService missionService;
	
	@ApiOperation(value = "전체 미션 데이터")
	@GetMapping(value="/all")
	public ResponseEntity<List<Mission>> findAll() {
		return new ResponseEntity<List<Mission>>(missionService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "챌린지별 미션 데이터")
	@GetMapping(value="/")
	public ResponseEntity<List<Mission>> findByLevel(@RequestParam String challengeId) {
		return new ResponseEntity<List<Mission>>(missionService.findByChallenge(challengeId), HttpStatus.OK);
	}
}
