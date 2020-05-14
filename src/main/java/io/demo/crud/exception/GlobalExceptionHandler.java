package io.demo.crud.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import io.demo.crud.vo.response.Response;

/**
 * 全局异常处理类
 * @author reyshaw
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 业务异常
	 * @param ex {@link BesinessException}
	 * @return {@link Response}
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public Response bizException(BusinessException ex) {
		if (null != ex.getData()) {
			return Response.fail(ex.getCode(), ex.getMessage(), ex.getData());
		}
		return Response.fail(ex.getCode(), ex.getMessage());
	}
	
	
	/**
	 * 未捕捉到的异常
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Response unHandledException(Exception ex) {
		ex.printStackTrace();
		return Response.fail(-1111, "系统错误!", ex.getMessage());
	}
}
