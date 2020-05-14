package io.demo.crud.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.demo.crud.log.Log;
import io.demo.crud.log.LogServ;
import io.demo.crud.vo.response.Response;

@Aspect
@Component
public class OperationAdvice {
	
	@Autowired
	private LogServ logService;
	
//	@Pointcut(value="@annotation(io.demo.crud.annotation.LogRecord)")
	 @Pointcut(value="execution(* io.demo.crud.user.*.*(..))")
	public void annoPoint() {
	}
	
	/**
	 * 登录日志
	 */
	@AfterReturning(value="annoPoint()")
	public void loginLog(JoinPoint joinPoint) { // 登录日志
		Log log = new Log();
		log.setType("登录");
		 HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = req.getSession();
		String user = (String)session.getAttribute("user");
		if (null != user) {
			log.setOperator(user);
			log.setResult("成功");
		} else {
			log.setOperator("匿名者");
			log.setResult("失败");
		}
		Log rt = logService.addLog(log);
		if (null != rt) {
			System.out.println("登录日志插入成功!");
		} else {
			System.out.println("登录日志插入失败");
		}
		
	}
	
	/**
	 * 增删改查日志
	 * @param jp
	 * @param rvt
	 */
	@AfterReturning(pointcut="execution(* io.demo.crud.emp.EmpCtrl.*(..))", returning="rvt")
	public void crud(JoinPoint jp, Object rvt) {
		Log log = new Log();
		String md = jp.getSignature().getName(); // 获取方法名称
		log.setType(md); // 可以根据不同方法名称做操作
		log.setOperator("admin"); // 可以根据loginLog的方法获取用户名
		Response res = (Response)rvt; // 转换返回结果
		if (res.getCode() == 0) { // 成功
			log.setResult("success");
		} else { // 失败
			log.setResult("fail");
		}
		 logService.addLog(log);
	}
}
