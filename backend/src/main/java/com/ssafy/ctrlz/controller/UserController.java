package com.ssafy.ctrlz.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
import com.ssafy.ctrlz.service.JwtService;
import com.ssafy.ctrlz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" },allowedHeaders = "*", maxAge = 6000)
@RestController
@Transactional
@Api(tags = "UserController", description = "회원API")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
		
	@PostMapping("/register")
	@ApiOperation(value = "가입하기", notes = "중복 이메일, 이름을 검사합니다.")
	public Object userRegister(@RequestBody User user) {
		
		if(userService.getUserByUserEmail(user.getUserEmail()) != null){
			return new ResponseEntity<>("Fail",HttpStatus.NOT_FOUND);
		}
		if(userService.getUserByUserName(user.getUserName()) != null) {
			return new ResponseEntity<>("Fail",HttpStatus.NOT_FOUND);
		}
		userService.createAccount(user);
		return new ResponseEntity<>("Success", HttpStatus.OK);
		
		}
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "성공시 jwt 토큰을 반환합니다.")
	public Object userLogin(@RequestBody User user) {
	
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(user.getUserPassword().getBytes());
			user.setUserPassword(String.format("%040x", new BigInteger(1,md.digest())));
			}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			}
		
		String original = user.getUserPassword();
		Optional<User> userOpt = userService.loginAccount(user.getUserEmail(), original);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		if(userOpt.isPresent()) {
			User userInfo = userService.getUserByUserEmail(user.getUserEmail());
			userInfo.setUserPassword(original);
			String token = jwtService.create(userInfo);
			resultMap.put("accesstoken",token);
			resultMap.put("message", "Success");
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<>(resultMap, status);
		}
		else {
			resultMap.put("message", "FAIL");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(resultMap, status);
		}
	}
	
	@PostMapping("/profile/update")
	@ApiOperation(value = "회원정보 수정")
	public Object userUpdate(Long userId,String userName, String userIntroduce,
							 MultipartFile userImage, String userEmail, String userPassword) {
		if(userService.getUserByUserName(userName) != null) {
			return new ResponseEntity<>("Fail",HttpStatus.NOT_FOUND);
		}
		userService.updateAccount(userId,userName,userIntroduce,userImage,userEmail,userPassword);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@GetMapping("/profile")
	@ApiOperation(value = "회원정보 조회")
	public Object userProfile(Long userId) {   
         return userService.profileAccount(userId);
	}
	
	@DeleteMapping("/delete/{userId}")
	@ApiOperation(value = "회원 삭제")
	public Object userDelete(@PathVariable Long userId) {
		Optional<User> userOpt = userService.deleteAccount(userId);
		if(userOpt.isPresent()) return new ResponseEntity<>("Success", HttpStatus.OK);
		return new ResponseEntity<>("Fail", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/emailcheck")
	@ApiOperation(value = "회원 이메일 중복 체크")
	public Object userEmailCheck(User user) {
		
		if(userService.getUserByUserEmail(user.getUserEmail()) != null){
			return new ResponseEntity<>("Fail",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
		}
	
	@GetMapping("/namecheck")
	@ApiOperation(value = "회원 이름 중복 체크")
	public Object userNameCheck(User user) {
		
		if(userService.getUserByUserName(user.getUserName()) != null){
			return new ResponseEntity<>("Fail",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
		}

}
