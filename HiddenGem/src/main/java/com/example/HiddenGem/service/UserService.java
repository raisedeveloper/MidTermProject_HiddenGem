package com.example.HiddenGem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HiddenGem.entity.User;


@Service
public interface UserService {
	public static final int correct_login = 0;
	public static final int WRONG_PASSWORD = 1;
	public static final int USER_NOT_EXIST= 2;
	public static final int count_per_page = 10;

	User getUserByUid(String uid);
	
	int getUserCount();
	
	List<User> getUserList(int page);
	
	void registerUser(User user); 

	void updateUser(User user);
	
	void deleteUser(String uid);
	
	int login(String uid, String pwd);
}