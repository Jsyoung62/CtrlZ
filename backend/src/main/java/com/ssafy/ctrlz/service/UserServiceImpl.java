package com.ssafy.ctrlz.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ssafy.ctrlz.exception.NoDataFoundException;
import com.ssafy.ctrlz.model.User;
import com.ssafy.ctrlz.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;
	
	@Override
	public User createUser(User user) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(user.getUserPassword().getBytes());
			user.setUserPassword(String.format("%040x", new BigInteger(1, md.digest())));
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		user.setUserEmail(user.getUserEmail());
		user.setUserName(user.getUserName());

		return userRepository.save(user);
	}
	
	@Override
	public User createGoogleUser(User user) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(user.getUserPassword().getBytes());
			user.setUserPassword(String.format("%040x", new BigInteger(1, md.digest())));
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		user.setUserEmail(user.getUserEmail());
		user.setUserName(user.getUserEmail().substring(0, user.getUserEmail().indexOf("@")));

		return userRepository.save(user);
	}
	
	@Override
	public User findByUserId(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NoDataFoundException("user"));
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public User findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public User findByUserGid(String userGid) {
		return userRepository.findByUserGid(userGid);
	}

	@Override
	public User findByUserEmailAndUserPassword(String userEmail, String userPassword) {
		return userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public ResponseEntity<Map<String, Object>> userToken(User user) {
		User userFind = findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (userFind != null) {
			User userInfo = findByUserEmail(user.getUserEmail());
			userInfo.setUserPassword(user.getUserPassword());
			String token = jwtService.create(userInfo);
			resultMap.put("accesstoken", token);
			resultMap.put("message", "Success");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
		}
		resultMap.put("message", "FAIL");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
