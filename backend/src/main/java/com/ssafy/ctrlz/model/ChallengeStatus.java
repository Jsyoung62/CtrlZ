package com.ssafy.ctrlz.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "challengeStatus")
public class ChallengeStatus {

	@EmbeddedId
	private ChallengeStatusId id;
	@Column(insertable = false, updatable = false)
	private LocalDateTime challengeStartDate;
	private String challengeFinishDate;
	private long challengeMissionCurrent;
	private long challengeFinishOrder;

	@OneToOne
	@JoinColumn(name = "challengeId", insertable = false, updatable = false)
	private Challenge challenge;

}
