package com.ssafy.ctrlz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.model.ZbtiTest;
import com.ssafy.ctrlz.repository.ZbtiTestRepository;

@Service
public class ZbtiTestServiceImpl implements ZbtiTestService {

	@Autowired
	private ZbtiTestRepository zbtiTestRepository;

	@Override
	public List<ZbtiTest> findAll() {
		List<ZbtiTest> zbtiTestList = zbtiTestRepository.findAll();

		if (zbtiTestList.size() > 0) {
			return zbtiTestList;
		}
		return null;
	}

}
