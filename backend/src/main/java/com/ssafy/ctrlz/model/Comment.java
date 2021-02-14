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
@Table(name ="comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String commentId;
	private String postId;
	private String commentContent;

	@Column(insertable = false, updatable = false)
	private LocalDate commentCreated;
	
	@OneToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
	
}
