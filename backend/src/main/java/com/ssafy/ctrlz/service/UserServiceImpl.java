package com.ssafy.ctrlz.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ssafy.ctrlz.model.User;
import com.ssafy.ctrlz.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void createAccount(User user) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(user.getUserPassword().getBytes());
			user.setUserPassword(String.format("%040x", new BigInteger(1,md.digest())));
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		user.setUserEmail(user.getUserEmail());
		user.setUserName(user.getUserName());
		userRepository.save(user);
	}
	
	@Override
	public Optional<User> loginAccount(String userEmail, String userPassword) {
		return userRepository.findUserByUserEmailAndUserPassword(userEmail,userPassword);
	}
	
	@Override
	public User profileAccount(Long userId) {
		return userRepository.getUserByUserId(userId);
	}
	
	@Override
	public void updateAccount(Long userId, String userName, String userIntroduce, MultipartFile userImage, String userEmail, String userPassword) {
		User user = new User();
		String UPLOAD_PATH = "/var/www/html/dist/profileimg";
        UUID uuid = UUID.randomUUID();
        String saveName = uuid+"_"+userImage.getOriginalFilename();

        File saveFile = new File(UPLOAD_PATH, saveName);
        
        try {
            userImage.transferTo(saveFile);
            saveName = "http://i4a202.p.ssafy.io/profileimg/" + saveName;
        } catch (IOException e) {
            e.printStackTrace();
            
        }
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserIntroduce(userIntroduce);
		user.setUserImage(saveName);
		user.setUserEmail(userEmail);
		user.setUserPassword(userPassword);
		userRepository.save(user);
	}
	
	@Override
	public Optional<User> deleteAccount(Long userId) {
		 return userRepository.deleteUserByUserId(userId);
	}

	@Override
	public Optional<User> findUserByUserEmailAndUserPassword(String userEmail, String userPassword) {	
		return userRepository.findUserByUserEmailAndUserPassword(userEmail, userPassword);
	}

	@Override
	public User getUserByUserEmail(String userEmail) {	
		return userRepository.getUserByUserEmail(userEmail);
	}

	@Override
	public User getUserByUserId(Long userId) {	
		return userRepository.getUserByUserId(userId);
	}

	@Override
	public User getUserByUserName(String userName) {	
		return userRepository.getUserByUserName(userName);
	}

	@Override
	public Optional<User> deleteUserByUserId(Long userId) {	
		return userRepository.deleteUserByUserId(userId);
	}

}
