package io.demo.crud.vo.request;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * 登录请求对象
 * @author reyshaw
 *
 */
@Data
public class LoginRequest {
	
	// 用户名
	private String username;
	// 密码
	private String password;
	
	// 对字段进行校验
	public Map<String, String> valiate(LoginRequest req) {
		Map<String, String> map = new HashMap<String, String>();
		if (null == req.getUsername() || "".equals(username)) {
			map.put("username", "字段不能为空");
		}
		if (null == req.getPassword() || "".equals(password)) {
			map.put("password", "字段不能为空");
		}
		return map;
	}
}
