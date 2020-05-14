package io.demo.crud.dept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.crud.exception.BusinessException;
import io.demo.crud.vo.response.Response;

@RequestMapping("/dept")
@RestController
public class DeptCtrl {
	
	@Autowired
	private DeptServ deptServ;
	
	@GetMapping
	public Response getDepts() throws BusinessException {
		return Response.success(deptServ.getDepts());
	}
}
