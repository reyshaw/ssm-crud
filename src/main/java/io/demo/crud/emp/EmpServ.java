package io.demo.crud.emp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.demo.crud.dept.Department;
import io.demo.crud.vo.request.EmployeeRequest;

@Service
public class EmpServ {
	
	@Autowired
	private IEmpDao empDao;
	
	// 查询全部
	public Map<String, Object> fetchEmps(Integer pageNum, Integer pageSize) {
		// 查询总条数
		int total = (int) empDao.count();
		// 分页
		Pageable pageAble = PageRequest.of(pageNum - 1, pageSize, Sort.by("empId").descending());
		Page<Employee> page = empDao.findAll(pageAble);
		List<Employee> emps = page.getContent();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("list", emps);
		return result;
	}
	
	// 查询单个
	public Employee fetchEmpById(Integer id) {
		Employee emp = empDao.getOne(id);
		return emp;
	}
	
	// 新增
	public Employee createEmp (EmployeeRequest empReq) {
		Employee emp = new Employee();
		emp.setEmpName(empReq.getName());
		emp.setEmpEmail(empReq.getEmail());
		emp.setEmpGender(empReq.getGender());
		emp.setDept(new Department(Integer.parseInt(empReq.getDept_id()), null));
		return empDao.save(emp);
	}
	
	// 更新
	public Employee updateEmp(EmployeeRequest empReq) {
		Employee emp = new Employee();
		emp.setEmpId(empReq.getId());
		emp.setEmpName(empReq.getName());
		emp.setEmpEmail(empReq.getEmail());
		emp.setEmpGender(empReq.getGender());
		emp.setDept(new Department(Integer.parseInt(empReq.getDept_id()), null));
		return empDao.save(emp);
	}
	
	// 删除
	public void deleteEmp(Integer id) {
		empDao.deleteById(id);
	}
	
}
