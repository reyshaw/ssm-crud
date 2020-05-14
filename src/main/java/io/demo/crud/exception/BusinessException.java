package io.demo.crud.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends Exception {

	private static final long serialVersionUID = 8738974046585094128L;

	private Integer code;
	
	private String message;
	
	private Object data;
	
	public BusinessException(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
