package org.demo.service;

import org.demo.dto.LoginRequest;
import org.demo.entity.User;

public interface IUser {
	
	User userExist(String username);
	
	User login(LoginRequest loginReq);
	
	int register(User user);
	
}
