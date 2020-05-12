package org.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.demo.constants.ErrorMessage;
import org.demo.dto.LoginRequest;
import org.demo.dto.Response;
import org.demo.entity.User;
import org.demo.service.IUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private IUser userService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest req;
	
	/**
	 * 登录
	 * @param loginReq {username:xx,password} 用户名和密码
	 * @return @link{Response}
	 * @throws Exception 
	 */
	@PostMapping
	public Response login(@RequestBody LoginRequest loginReq) throws Exception {
		User user = userService.login(loginReq);
		Response res = new Response();
		if (null != user) {
			session.setAttribute("user", loginReq.getUsername());
			res.setData(user);
		} else {
			throw new Exception(ErrorMessage.USERNAME_OR_PASSWORD_INCOREECT.getMsg());
			// res.setData(null);
			// res.setMsg(ErrorMessage.USERNAME_OR_PASSWORD_INCOREECT.getMsg());
		}
		return res;
	}
	
	/**
	 * 获取单个用户
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@GetMapping("{name}")
	public User getUser(@PathVariable String name) {
		User user = userService.userExist(name);
		if (null != user) {
			return user;
		} 
		return null;
	}
	
	/**
	 * 注册
	 * @param user
	 * @return {@link Response}
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping(value="register")
	public Response register(User user,  @RequestParam(value="file", required=true) MultipartFile upload) throws IllegalStateException, IOException { // @RequestParam("avatar")报错
		
		final String FILE_PATH = "/uploads/";
		
		Response res = new Response();
		// 判断文件是否为空
		if (upload.isEmpty()) {
			res.setMsg("文件为空");
		}
		// 构建路径
		String path = req.getServletContext().getRealPath(FILE_PATH);
		
		// 生成目录
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 获取文件名并生成文件名
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String fileName = uuid +  "_" + upload.getOriginalFilename();
		// 传送文件
		upload.transferTo(new File(path, fileName));
		
		// 处理其他数据
		User resUser = new User();
		BeanUtils.copyProperties(user, resUser); // 复制对象
		resUser.setAvatar(FILE_PATH + fileName);
		
		int result = userService.register(resUser);
		
		res.setData(null);
		res.setCode(-1);
		if(result > 0) {
			res.setData(resUser);
			res.setCode(0);
			res.setMsg("注册成功");
		} else {
			res.setMsg("注册失败");
		}
		return res;
	}
	
}
