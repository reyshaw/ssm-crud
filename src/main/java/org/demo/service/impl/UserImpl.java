package org.demo.service.impl;

import org.demo.dao.UserMapper;
import org.demo.dto.LoginRequest;
import org.demo.entity.User;
import org.demo.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements IUser {
	
	@Autowired
	private UserMapper userDao;
	
	public User login(LoginRequest loginReq) {
		User user = userExist(loginReq.getUsername());
		if (null != user) {
			user = userDao.getUserByNameAndPassword(loginReq);
			if (null != user) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public int register(User user) {
		User u = userExist(user.getUsername());
		if(null != u) {
			return -1;
		} else {
			return userDao.addUser(user);
		}
	}

	public User userExist(String username) {
		return userDao.getUserByName(username);
	}

}
