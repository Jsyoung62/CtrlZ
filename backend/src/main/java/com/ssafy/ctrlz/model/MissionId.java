package com.ssafy.ctrlz.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class MissionId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String challengeId;
	private String missionId;

}
