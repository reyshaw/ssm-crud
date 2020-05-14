package io.demo.crud.emp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.demo.crud.vo.request.EmployeeRequest;
import io.demo.crud.vo.response.Response;

@RequestMapping("/emp")
@RestController
public class EmpCtrl {
	
	@Autowired
	private EmpServ empServ;
	
	@GetMapping
	public Response getAllEmp(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
		Map<String, Object> emps = empServ.fetchEmps(pageNum, pageSize);
		return Response.success(emps);
	}
	
	@GetMapping("{id}")
	public Response getEmpById(@PathVariable Integer id) {
		Employee emp = empServ.fetchEmpById(id);
		return Response.success(emp);
	}
	
	@PostMapping
	public Response addEmp(@RequestBody EmployeeRequest empReq) {
		Employee newEmp = empServ.createEmp(empReq);
		return Response.success(newEmp);
	}
	
	@PutMapping
	public Response updateEmp(@RequestBody EmployeeRequest empReq) {
		Employee newEmp = empServ.updateEmp(empReq);
		return Response.success(newEmp);
	}
	
	@DeleteMapping("{id}")
	public Response removeEmp(@PathVariable Integer id) {
		empServ.deleteEmp(id);
		return Response.success(null);
	}

}
