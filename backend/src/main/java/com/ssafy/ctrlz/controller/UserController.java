package com.ssafy.ctrlz.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import com.ssafy.ctrlz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" },allowedHeaders = "*", maxAge = 6000)
@RestController
@Transactional
@Api(tags = "UserController", description = "회원 API")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
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
	
	@PostMapping("/google/register")
	@ApiOperation(value = "구글 계정으로 로그인, 가입하기", notes = "가입된 Gid면 가입하지않고 로그인처리 됩니다.")
	public Object userGoogleRegister(@RequestBody User user) {
		
		User userGoogle = userService.getUserByUserGid(user.getUserGid());
		
		if(userGoogle != null) {
		return userService.userToken(userGoogle);
		}
		else {	
		userService.createGoogleAccount(user);	
	    return userService.userToken(user);
		}
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
			
		return userService.userToken(user);
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
