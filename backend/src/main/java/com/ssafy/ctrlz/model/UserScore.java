package com.ssafy.ctrlz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "userScore")
public class UserScore {

	@Id
	private String userId;
	private String zbtiId;
	private int echoScore;

}
