package org.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.demo.dto.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavagationController {
	
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String toRegister() {
		return "register";
	}
	
	@RequestMapping("/list")
	public ModelAndView toList() {
		ModelAndView mv = new ModelAndView("list");
		Map<String, String> map = new HashMap<String,String>();
		map.put("test", "list");
		mv.addObject("data", map);
		return mv;
	}
	
	@RequestMapping("excp")
	@ResponseBody
	public Response excp() {
		return Response.fail();
	}
	
	@RequestMapping("/forward")
	@ResponseBody
	public Response forward(HttpServletRequest req, HttpServletResponse res) {
		Response response = new Response();
		response.setCode(-1);
		response.setData(null);
		String msg = (String)req.getAttribute("msg");
		response.setMsg(msg);
		return response;
	}

}
