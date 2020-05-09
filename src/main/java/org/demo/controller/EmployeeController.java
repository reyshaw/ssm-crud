package org.demo.controller;

import java.util.ArrayList;
import java.util.List;

//import javax.validation.Valid;

import org.demo.dto.EmployeeRequest;
import org.demo.dto.Response;
import org.demo.entity.Employee;
import org.demo.service.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RequestMapping("/emp")
@RestController
public class EmployeeController {
	
	@Autowired
	private IEmployee empService;
	
	/**
	 * 查询所有员工
	 * @param pageNum 当前页码, pageSize 每页条数
	 * @return
	 */
	@GetMapping
	public Response getAllEmp(@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize", defaultValue="2") Integer pageSize) {
		List<Employee> emps = new ArrayList<Employee>();
		PageHelper.startPage(pageNum, pageSize);
		emps = empService.getAllEmployee();
		PageInfo<Employee> pageInfo = new PageInfo<Employee>(emps, pageSize);
		Response res = new Response();
		res.setCode(0);
		res.setMsg("success!");
		res.setData(pageInfo);
		return res;
	}
	
	/**
	 * 根据主键获取员工对象
	 * @param id 主键
	 * @return @link{Employee}
	 */
	@GetMapping("{id}")
	public Response getEmp(@PathVariable("id") Integer id) {	
		Employee emp = empService.getlEmployeeById(id);
		Response res = new Response();
		res.setCode(0);
		res.setMsg("成功!");
		res.setData(emp);
		return res;
	}
	
	
	/**
	 * 新建员工对象
	 * @param emp
	 * @return
	 */
	@PostMapping
	public Response addEmp(@RequestBody EmployeeRequest empReq) {
		Employee emp = new Employee();
		emp.setEmpId(null);
		emp.setEmpName(empReq.getName());
		emp.setEmpGender(empReq.getGender());
		emp.setEmpEmail(empReq.getEmail());
		emp.setdId(empReq.getDept_id());
		int result = empService.addEmployee(emp);
		Response res = new Response();
		if(result > 0) {
			res.setCode(0);
			res.setMsg("新增成功");
		} else {
			if (result == -1) {
				res.setMsg("该用户已经存在");
			} else {
				res.setMsg("新增失败");
			}
			res.setCode(-1);
		}
		return res;
	}
	
	/**
	 * 更新员工信息
	 * @param empReq
	 * @return
	 */
	@PutMapping("{id}")
	public Response updateEmp(@PathVariable("id") Integer id, @RequestBody EmployeeRequest empReq) {
		Employee emp = new Employee();
		emp.setEmpId(id);
		emp.setEmpName(empReq.getName());
		emp.setEmpGender(empReq.getGender());
		emp.setEmpEmail(empReq.getEmail());
		emp.setdId(empReq.getDept_id());
		int result = empService.updateEmployee(emp);
		Response res = new Response();
		if( result > 0) {
			res.setCode(0);
			res.setMsg("更新成功!");
		} else {
			res.setCode(-1);
			if (result == -1) {
				res.setMsg("该用户不存在");
			} else {
				res.setMsg("更新失败");
			}
		}
		return res;
	}
	
	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public Response deleteEmp(@PathVariable("id") Integer id) {
		Employee emp = empService.getlEmployeeById(id);
		Response res = new Response();
		if(null != emp) {
			int result = empService.deleteEmployee(id);
			if (result > 0) {
				res.setCode(0);
				res.setMsg("删除成功");
			} else {
				res.setCode(-1);
				res.setMsg("该用户不存在");
			}
		} else {
			res.setCode(-1);
			res.setMsg("该用户不存在");
		}
		return res;
	}
}
