package org.demo.constants;

public enum ErrorMessage {
	
	PARAM_ERROR(1, "参数错误"),
	USER_EXIST(2, "用户已经存在"),
	USERNAME_OR_PASSWORD_INCOREECT(3, "用户名或密码不正确"),
	SQL_ERROR(4, "SQL错误");
	// 错误码
	private Integer code;
	// 错误消息
	private String msg;
	
	ErrorMessage() {		
	}

	ErrorMessage(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
