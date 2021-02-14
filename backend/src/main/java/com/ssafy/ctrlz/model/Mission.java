package com.ssafy.ctrlz.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "mission")
public class Mission {

	@EmbeddedId
	private MissionId id;
	private String missionTitle;
	private String dailyContent;
	private String missionContent;
	private String missionImage;

}
