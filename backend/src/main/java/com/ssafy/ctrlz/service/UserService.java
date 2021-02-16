package com.ssafy.ctrlz.service;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import com.ssafy.ctrlz.model.User;

public interface UserService {

	public User createUser(User user);

	public User createGoogleUser(User user);

	public User findByUserId(Long userId);

	public User findByUserName(String userName);

	public User findByUserEmail(String userEmail);

	public User findByUserGid(String userGid);

	public User findByUserEmailAndUserPassword(String userEmail, String userPassword);

	public User updateUser(User user);

	public void deleteUser(Long userId);

	public ResponseEntity<Map<String, Object>> userToken(User user);

}
