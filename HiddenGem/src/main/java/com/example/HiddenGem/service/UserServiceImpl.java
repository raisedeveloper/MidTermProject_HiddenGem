package com.example.HiddenGem.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HiddenGem.dao.UserDao;
import com.example.HiddenGem.entity.User;


@Service
public class UserServiceImpl implements UserService{
	@Autowired private UserDao uDao;

	@Override
	public User getUserByUid(String uid) {
		return uDao.getUser(uid);
	}

	@Override
	public int getUserCount() {
		return uDao.getUserCount();
	}

	@Override
	public List<User> getUserList(int page) {
		int offset = (page - 1) * count_per_page;
        return uDao.getUserList(count_per_page, offset);
	}

	@Override
	public void registerUser(User user) {
//		User u = uDao.getUser(user.getUid());
//		if (u != null)
//			return;
		String hashedPwd = BCrypt.hashpw(user.getPwd(), BCrypt.gensalt());  
		user.setPwd(hashedPwd);
		uDao.insertUser(user);
		
	}

	@Override
	public void updateUser(User user) {
		uDao.updateUser(user);		
	}

	@Override
	public void deleteUser(String uid) {
		uDao.deleteUser(uid);		
	}

	@Override
	public int login(String uid, String pwd) {
		User user = uDao.getUser(uid);
		if (user==null)
			return USER_NOT_EXIST;
		if (BCrypt.checkpw(pwd, user.getPwd())) 
			return correct_login;
		return WRONG_PASSWORD;
	}



}
