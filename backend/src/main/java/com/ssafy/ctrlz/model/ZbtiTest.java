package com.ssafy.ctrlz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "zbtiTest")
public class ZbtiTest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String testId;
	private String testQuestion;
	private String testAnswer1;
	private String testAnswer2;
	private String testAnswer3;
	private String testAnswer4;
	private String testImage;

}
