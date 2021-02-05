package com.ssafy.ctrlz.model;

import java.time.LocalDate;
import javax.persistence.Column;
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
@Table(name = "userBadge")
public class UserBadge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userBadgeId;
	private String userId;
	private String badgeId;
	
	@Column(insertable = false, updatable = false)
	private LocalDate userBadgeGetDate;
	
	@OneToOne
	@JoinColumn(name = "badgeId", insertable = false, updatable = false)
	private Badge badge;
	
}
