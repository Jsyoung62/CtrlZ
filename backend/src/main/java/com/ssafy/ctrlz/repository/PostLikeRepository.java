package com.ssafy.ctrlz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ssafy.ctrlz.model.PostLike;
import com.ssafy.ctrlz.model.PostLikeId;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {

}
