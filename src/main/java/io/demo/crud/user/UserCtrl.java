package io.demo.crud.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.demo.crud.annotation.FileRequired;
import io.demo.crud.annotation.LogRecord;
import io.demo.crud.exception.BusinessException;
import io.demo.crud.vo.request.LoginRequest;
import io.demo.crud.vo.response.Response;

@RestController
public class UserCtrl {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private HttpServletRequest req;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 登录请求
	 * @param loginReq {@link LoginRequest}
	 * @return {@link Response}
	 * @throws BusinessException 
	 */
	@PostMapping("/user")
	@LogRecord(value = "login")
	public Response login(@RequestBody LoginRequest user) throws BusinessException {
		userServ.getUserByUsernameAndPassword(user);
		session.setAttribute("user", user.getUsername());
		return Response.success(null);
	}
	
	@PostMapping("/register")
	@FileRequired
	public Response register(User user, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException, BusinessException {
		 userServ.addUser(user, file, req);
		 return Response.success(null);
	}
	
}
