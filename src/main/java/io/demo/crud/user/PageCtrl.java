package io.demo.crud.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("userPageCtrl")
public class PageCtrl {
	
	/**
	 * 登录页面
	 * @return
	 */
	@GetMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	/**
	 * 注册页面
	 * @return
	 */
	@GetMapping("/register")
	public String toReg() {
		return "register";
	}
}
