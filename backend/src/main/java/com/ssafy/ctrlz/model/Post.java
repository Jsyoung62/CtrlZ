package com.ssafy.ctrlz.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime postCreated;
	
	private String challengeId;
	private Long userId;
	private String postContent;
	private String postImage;
	private int postLike;
}
