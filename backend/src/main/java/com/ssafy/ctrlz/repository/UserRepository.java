package com.ssafy.ctrlz.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ssafy.ctrlz.model.User;

public interface UserRepository extends JpaRepository<User,String> {
	
	Optional<User> findUserByUserEmailAndUserPassword(String userEmail, String userPassword);
	User getUserByUserEmail(String userEmail);
	User getUserByUserId(Long userId);
	User getUserByUserName(String userName);
	Optional<User> deleteUserByUserId(Long userId);
	
}
