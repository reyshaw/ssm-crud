package org.demo.dao;

import org.demo.dto.LoginRequest;
import org.demo.entity.User;

public interface UserMapper {
	
	User getUserByNameAndPassword(LoginRequest loginReq);
	
	User getUserByName(String username);
	
	int addUser(User user);

}
