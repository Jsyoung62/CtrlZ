package com.ssafy.ctrlz.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "postLike")
@Data
public class PostLike {

	@EmbeddedId
	private PostLikeId id;

}
