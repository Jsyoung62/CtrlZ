package com.ssafy.ctrlz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "badge")
public class Badge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String badgeId;
	private String badgeName;
	private String badgeImage;

}
