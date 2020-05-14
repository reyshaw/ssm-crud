package io.demo.crud.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum UserError {
	
	USER_NOT_EXIST(-1, "用户不存在"),
	USERNAME_PASSWORD_INCORRECT(-2, "用户名或密码错误"),
	PARAM_ERROR(-3, "参数错误"),
	EMPTY_FILE(-4, "文件为空"),
	USER_EXIST(-5, "用户已经存在"),
	ADD_USER_FAIL(-6,"注册失败");
	
	private Integer code;
	
	private String message;
	
}
