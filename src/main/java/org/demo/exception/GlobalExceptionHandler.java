package org.demo.exception;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 全局异常处理类(被ControllerAdvice取代了,未使用)
 * @author reyshaw
 *
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		boolean isAjax = false;
		String xrw = request.getHeader("X-Requested-With");
		if (null  != xrw && xrw.equals("XMLHttpRequest")) {
			isAjax = true;
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", ex.getMessage());
		if (isAjax) { // ajax请求, reponse.getWriter....
			return mv;
		} else { // 非ajax请求
			mv.addObject("msg", ex.getMessage());
			return mv;
		}
	}
}
