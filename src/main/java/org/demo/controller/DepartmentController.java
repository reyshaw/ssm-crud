package org.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.demo.entity.Department;
import org.demo.service.IDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dept")
@RestController
public class DepartmentController {

	@Autowired
	private IDepartment deptService;
	
	@GetMapping
	public Map<String, Object> getAllDepartment() {
		List<Department> list = new ArrayList<Department>();
		list = deptService.getDept();
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 0);
		res.put("msg", "成功");
		res.put("data", list);
		return res;
	}
	
	
}
