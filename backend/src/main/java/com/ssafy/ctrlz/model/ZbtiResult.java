package com.ssafy.ctrlz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "zbtiResult")
public class ZbtiResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String zbtiId;
	private String zbtiType;
	private String zbtiName;
	private String zbtiDescription;
	private String zbtiImage;

}
