package com.ssafy.ctrlz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.exception.NoDataFoundException;
import com.ssafy.ctrlz.model.ZbtiResult;
import com.ssafy.ctrlz.repository.ZbtiResultRepository;

@Service
public class ZbtiResultServiceImpl implements ZbtiResultService {

	@Autowired
	private ZbtiResultRepository zbtiResultRepository;

	@Override
	public ZbtiResult findByZbti(String zbtiId) {
		return zbtiResultRepository.findById(zbtiId).orElseThrow(() -> new NoDataFoundException("zbtiResult"));
	}

}
