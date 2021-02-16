package com.ssafy.ctrlz.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ssafy.ctrlz.model.User;
import com.ssafy.ctrlz.model.UserScore;
import com.ssafy.ctrlz.service.UserScoreService;
import com.ssafy.ctrlz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "User", description = "사용자  API")
@CrossOrigin
@RestController
@Transactional
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserScoreService userScoreService;

	@ApiOperation(value = "가입하기", notes = "중복 이메일, 이름을 검사합니다.")
	@PostMapping("/register")
	public ResponseEntity<User> userRegister(@RequestBody User user) {
		if (userService.findByUserEmail(user.getUserEmail()) != null || userService.findByUserName(user.getUserName()) != null) {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}

		user = userService.createUser(user);
		System.out.println(user.toString());
		UserScore userScore = new UserScore();
		userScore.setUserId(Long.toString(user.getUserId()));
		userScoreService.save(userScore);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "구글 계정으로 로그인, 가입하기", notes = "가입된 Gid면 가입하지않고 로그인처리 됩니다.")
	@PostMapping("/google/register")
	public Object userGoogleRegister(@RequestBody User user) {
		User userGoogle = userService.findByUserGid(user.getUserGid());

		if (userGoogle != null) {
			return userService.userToken(userGoogle);
		}

		user = userService.createGoogleUser(user);
		System.out.println(user.toString());
		UserScore userScore = new UserScore();
		userScore.setUserId(Long.toString(user.getUserId()));
		userScoreService.save(userScore);

		return userService.userToken(user);
	}

	@ApiOperation(value = "로그인", notes = "성공시 jwt 토큰을 반환합니다.")
	@PostMapping("/login")
	public Object userLogin(@RequestBody User user) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(user.getUserPassword().getBytes());
			user.setUserPassword(String.format("%040x", new BigInteger(1, md.digest())));
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return userService.userToken(user);
	}

	@ApiOperation(value = "회원정보 수정")
	@PostMapping("/profile/update")
	public ResponseEntity<String> userUpdate(Long userId, MultipartFile userImage, String userIntroduce) {
		User user = userService.findByUserId(userId);

		if (user == null) {
			return new ResponseEntity<String>("Fail", HttpStatus.NOT_FOUND);
		}

		UUID uuid = UUID.randomUUID();
		String filePath = "/var/www/html/dist/profileimg";
		String fileName = uuid + "_" + userImage.getOriginalFilename();

		File saveFile = new File(filePath, fileName);

		try {
			userImage.transferTo(saveFile);
			fileName = "http://i4a202.p.ssafy.io/profileimg/" + fileName;
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		user.setUserId(userId);
		user.setUserIntroduce(userIntroduce);
		user.setUserImage(fileName);
		userService.updateUser(user);

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@ApiOperation(value = "회원정보 조회")
	@GetMapping("/profile")
	public User userProfile(Long userId) {
		return userService.findByUserId(userId);
	}

	@ApiOperation(value = "회원 삭제")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> userDelete(@PathVariable Long userId) {
		User user = userService.findByUserId(userId);
		user.setUserType('D');
		userService.updateUser(user);

		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@ApiOperation(value = "회원 이름 중복 체크")
	@GetMapping("/namecheck")
	public ResponseEntity<String> userNameCheck(String userName) {
		if (userService.findByUserName(userName) != null) {
			return new ResponseEntity<String>("Duplicated Name", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Possible Name", HttpStatus.OK);
	}

	@ApiOperation(value = "회원 이메일 중복 체크")
	@GetMapping("/emailcheck")
	public ResponseEntity<String> userEmailCheck(String userEmail) {
		if (userService.findByUserEmail(userEmail) != null) {
			return new ResponseEntity<String>("Duplicated Email", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Possible Email", HttpStatus.OK);
	}

}
