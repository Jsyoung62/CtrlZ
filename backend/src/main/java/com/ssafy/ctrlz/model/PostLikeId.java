package com.ssafy.ctrlz.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PostLikeId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long postId;
	private Long userId;

}
