package com.ssafy.ctrlz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "challenge")
public class Challenge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String challengeId;
	private String levelId;
	private String challengeName;
	private String challengeContent;
	private String challengeImage;
	private String challengeType;
	private int challengeMissionTotal;
	private String challengeCreated;
	private String challengeClosed;
	private long participants;

	@OneToOne
	@JoinColumn(name = "badgeId")
	private Badge badge;

}
