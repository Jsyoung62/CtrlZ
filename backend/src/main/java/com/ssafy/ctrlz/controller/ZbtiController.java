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
import com.ssafy.ctrlz.model.ZbtiResult;
import com.ssafy.ctrlz.model.ZbtiTest;
import com.ssafy.ctrlz.service.ZbtiResultService;
import com.ssafy.ctrlz.service.ZbtiTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ZBTI", description = "ZBTI 테스트 API")
@CrossOrigin
@RestController
@RequestMapping("/zbti")
public class ZbtiController {

	@Autowired
	private ZbtiTestService zbtiTestService;

	@Autowired
	private ZbtiResultService zbtiResultService;

	@ApiOperation(value = "전체 ZBTI 테스트")
	@GetMapping(value="/")
	public ResponseEntity<List<ZbtiTest>> findAll() {
		return new ResponseEntity<List<ZbtiTest>>(zbtiTestService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "타입별 ZBTI 결과 데이터")
	@GetMapping(value="/result")
	public ZbtiResult findByZbti(@RequestParam String zbtiId) {
		return zbtiResultService.findByZbti(zbtiId);
	}

}
