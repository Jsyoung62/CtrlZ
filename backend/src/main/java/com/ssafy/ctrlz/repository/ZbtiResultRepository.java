package com.ssafy.ctrlz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssafy.ctrlz.model.ZbtiResult;

@Repository
public interface ZbtiResultRepository extends JpaRepository<ZbtiResult, String> {

}
