package com.ssafy.ctrlz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "mission")
public class Mission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String missionId;
	private String challengeId;
	private String missionTitle;
	private String dailyContent;
	private String missionContent;
	private String missionImage;

}
