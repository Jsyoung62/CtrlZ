package com.ssafy.ctrlz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssafy.ctrlz.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	public List<Post> findByChallengeIdOrderByPostId(String challengeId);

	public List<Post> findByUserId(Long userId);

	public List<Post> findByUserIdAndMissionId(Long userId, String missionId);

	public List<Post> findByUserIdAndChallengeId(Long userId, String challengeId);

}
