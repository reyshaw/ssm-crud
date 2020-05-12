package org.demo.advice;

import org.demo.dto.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常切面类
 * @author reyshaw
 *
 */
@ControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Response handleException(Exception ex) {
		return new Response(-1, ex.getMessage(), null);
	}
}
