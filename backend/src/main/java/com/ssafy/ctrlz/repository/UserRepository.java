package com.ssafy.ctrlz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ssafy.ctrlz.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserName(String userName);

	public User findByUserEmail(String userEmail);

	public User findByUserGid(String userGid);

	public User findByUserEmailAndUserPassword(String userEmail, String userPassword);

}
