package com.ssafy.ctrlz.model;

import java.time.LocalDateTime;
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
@Table(name = "post")
public class Post {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	private String challengeId;
	private String missionId;
	private Long userId;
	private String postContent;
	private String postImage;
	@Column(insertable = false, updatable = false)
	private LocalDateTime postCreated;
	private int postLike;

	@OneToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;

}
