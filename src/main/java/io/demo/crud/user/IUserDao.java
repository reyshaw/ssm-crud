package io.demo.crud.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Integer> {
	
	// 根据用户名查用户
	User findByUsername(String username);
	
	// 根据用户名和密码查询用户
	User findByUsernameAndPassword(String username, String password);

}
