package com.ssafy.ctrlz.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class PostUpload {
	private Long userId;
	private String challengeId;
	private String postContent;
	private MultipartFile postImage;
}
