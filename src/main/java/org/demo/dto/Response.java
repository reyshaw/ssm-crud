package org.demo.dto;

public class Response {
	
	private int code;
	
	private String msg;
	
	private Object data;
	
	public Response() {
	}
	
	public Response(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	// 成功
	public static Response success() {
		return new Response(0, "success", null);
	}
	
	// 失败
	public static Response fail() {
		return new Response(-1, "fail", null);
	}

}
