package com.ssafy.ctrlz.service;

import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.ssafy.ctrlz.model.User;

public interface UserService {
	//Jwt User 토큰 생성
	public Object userToken(User user);
	//회원가입 기능 
	public void createAccount(User user);
	//로그인 기능
	public Optional<User> loginAccount(String userEmail, String userPassword);
	//내 프로필 기능
	public User profileAccount(Long userId);
	//프로필 편집 기능
	public void updateAccount(Long userId, String userName, String userIntroduce, MultipartFile userImage, String userEmail,String userPassword);
	//회원 탈퇴 기능
	public Optional<User> deleteAccount(Long userId);
	//이메일,비밀번호 찾기
	Optional<User> findUserByUserEmailAndUserPassword(String userEmail, String userPassword);
	//이메일정보로 유저정보 가져오기
	User getUserByUserEmail(String userEmail);
	//유저 아이디로 유저정보 가져오기
	User getUserByUserId(Long userId);
	//유저 이름으로 유저정보 가져오기
	User getUserByUserName(String userName);
	//유저 Gid 정보 확인하기
	User getUserByUserGid(String userGid);
	//유저 아이디로 유저정보 삭제하기
	Optional<User> deleteUserByUserId(Long userId);

}
