package io.demo.crud.dept;


public enum DeptError {
	
	DEPT_NOT_EXIST(-1, "部门不存在"),
	NO_DEPARTMENT(-2, "还没有部门");
	
	private Integer code;
	
	private String message;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	DeptError() {
	}
	
	DeptError(Integer code, String message) {
		this.code = code;
		this.message = message;
		
	}


}
