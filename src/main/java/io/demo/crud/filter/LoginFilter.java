package io.demo.crud.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebInitParam;

import io.demo.crud.utils.ArrayUtils;

@WebFilter(filterName = "LoginFilter",
					urlPatterns = "/*", 
					initParams = { @WebInitParam(name = "paths", value = "/user,/regster,/login,/register"),
										@WebInitParam(name = "exts", value = "css,js,jpg,jpeg,png,gif,ico,jsp,map") })
public class LoginFilter implements Filter {
	// 未登录就能访问的资源
	private String[] paths;
	// 静态资源组
	private String[] exts;
	// 授权失败后跳转的地址
	private static final String LOGIN_PATH = "/login";
	
	/**
	 * 无参构造器
	 */
    public LoginFilter() {
        super();
    }
    
    /**
     * 销毁
     */
	public void destroy() {
		paths = null;
		exts = null;
	}
	/**
	 * 判断是否是白名单
	 * 		是 pass
	 * 		否,判断是否登录
	 * 			是,pass
	 * 			否,跳转到登录页面
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = req.getServletPath().toLowerCase();
		String ext = path.substring(path.lastIndexOf(".") + 1);
		if (ArrayUtils.eleExists(paths, path) || ArrayUtils.eleExists(exts, ext)) { // pass
			chain.doFilter(request, response);
		} else { // 判断是否登录
			HttpSession session = req.getSession();
			if (null != session.getAttribute("user")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + LOGIN_PATH);
			}
		}
	}
	
	/**
	 * 获取初始化参数,
	 * 		并将其转为数组
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		paths = fConfig.getInitParameter("paths").split(",");
		exts = fConfig.getInitParameter("exts").split(",");
	}

}
