package io.demo.crud.interceptor;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.demo.crud.annotation.FileRequired;
import io.demo.crud.exception.BusinessException;

public class UploadInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//  如果不是映射到方法
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		// 方法注解级拦截器
		HandlerMethod hm = (HandlerMethod)handler;
		Method method = hm.getMethod();
		
		FileRequired fr = method.getAnnotation(FileRequired.class);
		
		if (null != fr) { // 有注解
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			List<MultipartFile> files = req.getFiles("file");
			if (files.size() > 0) { // 说明接收到了文件
				return true;
			} else {
				throw new BusinessException(-1, "请上传文件");
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println("postHandle");

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("afterCompletion");

	}

}

